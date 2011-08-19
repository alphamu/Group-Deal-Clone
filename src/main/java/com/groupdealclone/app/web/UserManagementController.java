package com.groupdealclone.app.web;

import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.groupdealclone.app.domain.Account;
import com.groupdealclone.app.service.AccountService;
import com.groupdealclone.app.validation.EditUserValidator;
import com.groupdealclone.app.validation.NewUserValidator;

@Controller
@RequestMapping("/user")
public class UserManagementController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder	passwordEncoder;

	@Autowired
	MessageSource	appConfig;

	@RequestMapping(value = "/chpwd", method = RequestMethod.GET)
	public String showChangePassowrdPage(ModelMap model) {
		model.put("chpwd", new ChangePassword());
		return "chpwd";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showNewUserPage(ModelMap model) {
		model.put("account", new Account());
		Collection<GrantedAuthority> auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority ga : auths) {
			if (ga.getAuthority().equals("ROLE_ADMIN")) {
				model.put("isadmin", true);
			}
		}
		return "user/new";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String showEditUserPage(@RequestParam(value = "username", required = false) String username, ModelMap model) {
		try{
		Account a = (Account) userDetailsService.loadUserByUsername(username);
		model.put("account", a);
		return "user/edit";
		}catch (Exception e){
			model.put("username", username);
			return "user/not-found";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String doEditUserPage(@Valid Account account, BindingResult result, ModelMap model){
		new EditUserValidator().validate(account, result);
		if(result.hasErrors()) {
			return "user/edit";
		}
		
		String password = account.getPassword();
		if(password == null || password.length() == 0) {
			Account a = (Account) ((AccountService)userDetailsService).loadUserById(account.getId());
			if(a != null) {
				password = a.getPassword();
				account.setPassword(passwordEncoder.encodePassword(password, null));
			}
		}
		((AccountService)userDetailsService).updateAccount(account);
		model.put("username", account.getUsername());
		return "user/edit-success";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String doNewUserPage(@Valid Account account, BindingResult result, ModelMap model){
		new NewUserValidator().validate(account, result);
		if(result.hasErrors()) {
			return "user/new";
		}
		String password = account.getPassword();
		account.setPassword(passwordEncoder.encodePassword(password, null));
		((AccountService)userDetailsService).saveAccount(account);
		model.put("username", account.getUsername());
		return "user/new-success";
	}

	@RequestMapping(value = "/chpwd", method = RequestMethod.POST)
	public String doChangePasswordPage(@Valid ChangePassword chpwd, BindingResult result, ModelMap model, Locale locale) {
		String passwd = chpwd.getCurrentpwd();
		String newpasswd = chpwd.getNewpwd();
		String confirmpwd = chpwd.getConfirmpwd();

		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String passwdHash = passwordEncoder.encodePassword(passwd, null);
		String newHash = null;
		Account a = (Account) userDetailsService.loadUserByUsername(username);
		boolean error = false;
		if (passwdHash.equals(a.getPassword())) {
			if (newpasswd.equals(confirmpwd)) {
				int minLength = Integer.parseInt(appConfig.getMessage("min.password.size", null, locale));
				if (newpasswd.length() >= minLength) {
					newHash = passwordEncoder.encodePassword(newpasswd, null);
				} else {
					model.put("newpwd", "Minimum length should be " + minLength);
					error = true;
				}
			} else {
				model.put("newpwd", "Passwords do not match");
				model.put("confirmpwd", "Passwords do not match");
				error = true;
			}
		} else {
			model.put("currentpwd", "Current Password Incorrect");
			error = true;
		}

		if (error) {
			return "chpwd";
		}
		a.setPassword(newHash);
		((AccountService)userDetailsService).updateAccount(a);
		model.put("username", a.getUsername());
		
		return "chpwd-success";
	}

	class ChangePassword {

		private String	currentpwd;

		private String	newpwd;

		private String	confirmpwd;

		public String getCurrentpwd() {
			return currentpwd;
		}

		public void setCurrentpwd(String currentpwd) {
			this.currentpwd = currentpwd;
		}

		public String getNewpwd() {
			return newpwd;
		}

		public void setNewpwd(String newpwd) {
			this.newpwd = newpwd;
		}

		public String getConfirmpwd() {
			return confirmpwd;
		}

		public void setConfirmpwd(String confirmpwd) {
			this.confirmpwd = confirmpwd;
		}
	}

}
