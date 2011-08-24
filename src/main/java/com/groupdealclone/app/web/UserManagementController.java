package com.groupdealclone.app.web;

import java.util.Collection;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
@Configuration
@RequestMapping("/user")
public class UserManagementController {
	private static final Logger	logger	= LoggerFactory.getLogger(UserManagementController.class);
	
	private @Value("#{config.minPasswordSize}") int minPasswordLength;
	
	@Autowired
	UserDetailsService			userDetailsService;

	@Autowired
	PasswordEncoder				passwordEncoder;

	@Autowired
	MessageSource				appConfig;

	@Autowired
	private JavaMailSenderImpl	mailSender;

	@Autowired
	MailMessage					passwordResetMessage;

	@Autowired
	MailMessage					newAccountMessage;
	
	 @Autowired
	 protected AuthenticationManager authenticationManager;

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
		try {
			Account a = (Account) userDetailsService.loadUserByUsername(username);
			model.put("account", a);
			return "user/edit";
		} catch (Exception e) {
			model.put("username", username);
			return "user/not-found";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String doEditUserPage(@Valid Account account, BindingResult result, ModelMap model) {
		new EditUserValidator().validate(account, result);
		if (result.hasErrors()) {
			return "user/edit";
		}

		String password = account.getPassword();
		if (password == null || password.length() == 0) {
			Account a = (Account) ((AccountService) userDetailsService).loadUserById(account.getId());
			if (a != null) {
				password = a.getPassword();
				account.setPassword(passwordEncoder.encodePassword(password, null));
			}
		}
		((AccountService) userDetailsService).updateAccount(account);
		model.put("username", account.getUsername());
		return "user/edit-success";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String doNewUserPage(@Valid Account account, BindingResult result, ModelMap model, Locale locale) {
		new NewUserValidator().validate(account, result);
		if (result.hasErrors()) {
			return "user/new";
		}
		String password = account.getPassword();
		account.setPassword(passwordEncoder.encodePassword(password, null));
		((AccountService) userDetailsService).saveAccount(account);

		// send email
		Object[] vals = { account.getFullname(), account.getUsername(), password, account.getUsername() + ":" + account.getActivationCode() };
		String text = appConfig.getMessage("new.account.message", vals, locale);
		newAccountMessage.setTo(account.getUsername());
		newAccountMessage.setText(text);

		try {
			this.mailSender.send((SimpleMailMessage) newAccountMessage);
		} catch (MailException ex) {
			// simply log it and go on...
			logger.error("JavaMail error {}", ex);
			model.put("error", "Internal error, please try again later");
			return "user/forgot";
		}

		model.put("username", account.getUsername());
		return "user/new-success";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String doNewAccountConfirm(@RequestParam(value = "activate", required = true) String activate, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.put("success", false);
		String[] split = activate.split(":");
		Account a = (Account) userDetailsService.loadUserByUsername(split[0]);
		if(a.getActivationCode() == null) {
			return "page-not-found";
		}
		try {
			if (a.getActivationCode().equals(split[1])) {
				a.setEnabled(true);
				a.setActivationCode(null);
				((AccountService) userDetailsService).updateAccount(a);
				model.put("success", true);
			}
		} catch (Exception e) {
			logger.error("Something went wrong while trying to activate user account {}", a.getUsername());
			logger.error("Error {}", e);
			model.put("success", false);
			model.put("message", "Something went wrong, please try again later");
		}
		
		authenticateUserAndSetSession(a,request);
		
		return "user/activation";
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
				int minLength = minPasswordLength;
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
		((AccountService) userDetailsService).updateAccount(a);
		model.put("username", a.getUsername());

		return "chpwd-success";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String resetPassword(ModelMap model) {
		model.put("resetPassword", new ResetPassword());
		return "user/forgot";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String resetPassword(@Valid ResetPassword resetPassword, BindingResult result, ModelMap model, Locale locale) {
		if (resetPassword.getUsername().length() < 4) {
			model.put("error", "Email Address too short");
			return "user/forgot";
		}
		final String username = resetPassword.getUsername();
		Account a = null;
		try {
			a = (Account) userDetailsService.loadUserByUsername(username);
		} catch (Exception e) {
			logger.error("Account does not exist {}", username);
			model.put("error", "No account with email address '" + username + "' found.");
			return "user/forgot";
		}
		if (a == null) {
			logger.error("Account does not exist {}", username);
			model.put("error", "No account with email address '" + username + "' found.");
			return "user/forgot";
		}

		String newPassword = getRandomString(minPasswordLength);
		try {
			String newHash = passwordEncoder.encodePassword(newPassword, null);
			a.setPassword(newHash);
			((AccountService) userDetailsService).updateAccount(a);
		} catch (Exception e) {
			logger.error("Error while trying to change password {}", e);
			model.put("error", "Internal error, please try again later");
			return "user/forgot";
		}

		Object[] vals = { a.getFullname(), newPassword };
		String text = appConfig.getMessage("password.reset.message", vals, locale);
		passwordResetMessage.setTo(a.getUsername());
		passwordResetMessage.setText(text);

		try {
			this.mailSender.send((SimpleMailMessage) passwordResetMessage);
		} catch (MailException ex) {
			// simply log it and go on...
			logger.error("JavaMail error {}", ex);
			model.put("error", "Internal error, please try again later");
			return "user/forgot";
		}

		model.put("message", "A new password has been generated and sent to your email address.");
		return "user/reset-success";
	}

	private static final String	charset	= "!@#$%^&*0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String getRandomString(int length) {
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	private void authenticateUserAndSetSession(Account user, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

}

/***
 * ChangePassword Bean
 * 
 * @author Ali
 */
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

/***
 * ResetPassword Bean
 * 
 */
class ResetPassword {
	private String	username;

	public ResetPassword() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
