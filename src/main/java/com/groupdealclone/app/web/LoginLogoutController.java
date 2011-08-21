package com.groupdealclone.app.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves the login or denied page depending on the URI template Controller from:
 * http://krams915.blogspot.com/2010/12/spring-security-mvc-integration_18.html
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {

	protected static Logger	logger	= Logger.getLogger(LoginLogoutController.class);

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, ModelMap model) {
		logger.debug("Received request to show login page");

		if (error == true) {
			// Assign an error message
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		
		return "loginpage";
	}

	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a regular user tries to access an admin only
	 * page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		return "deniedpage";
	}
}
