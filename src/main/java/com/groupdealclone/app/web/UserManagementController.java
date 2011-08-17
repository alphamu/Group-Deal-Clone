package com.groupdealclone.app.web;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.dao.AccountDao;
import com.groupdealclone.app.domain.Account;
import com.groupdealclone.app.domain.ChangePassword;

@Controller
@RequestMapping("/user")
public class UserManagementController {
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MessageSource appConfig;
	
	@RequestMapping(value = "/chpwd", method = RequestMethod.GET)
	public String showChangePassowrdPage(ModelMap model) {
		model.put("chpwd", new ChangePassword());
		return "chpwd";
	}
	
	@RequestMapping(value = "/chpwd", method = RequestMethod.POST)
	public String doChangePassowrdPage(@Valid ChangePassword chpwd, BindingResult result, ModelMap model, Locale locale) {
		String passwd = chpwd.getCurrentpwd();
		String newpasswd = chpwd.getNewpwd();
		String confirmpwd = chpwd.getConfirmpwd();
		
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		String passwdHash = passwordEncoder.encodePassword(passwd, null);
		String newHash = null;
		Account a = accountDao.getUser(currentUser);
		boolean error = false;
		if(passwdHash.equals(a.getPassword())){
			if(newpasswd.equals(confirmpwd)){
				int minLength = Integer.parseInt(appConfig.getMessage("min.password.size",null,locale));
				if(newpasswd.length() >= minLength ){
					newHash = passwordEncoder.encodePassword(newpasswd, null);
				} else {
					model.put("newpwd", "Minimum length should be "+minLength);
					error=true;
				}
			} else {
				model.put("newpwd", "Passwords do not match");
				model.put("confirmpwd", "Passwords do not match");
				error=true;
			}
		} else {
			model.put("currentpwd", "Current Password Incorrect");
			error=true;
		}
	
		if(error){
			return "chpwd";
		}
		a.setPassword(newHash);
		accountDao.updateUser(a);
		
		return "chpwd-success";
	}

}
