package com.groupdealclone.app.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 * Controller from: http://krams915.blogspot.com/2010/12/spring-security-mvc-integration_18.html
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController {

	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {
		logger.debug("Received request to show login page");

		// Add an error message to the model if login is unsuccessful
		// The 'error' parameter is set to true based on the when the
		// authentication has failed.
		// We declared this under the authentication-failure-url attribute
		// inside the spring-security.xml
		/*
		 * See below: <form-login login-page="/krams/auth/login"
		 * authentication-failure-url="/krams/auth/login?error=true"
		 * default-target-url="/krams/main/common"/>
		 */
		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}

		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return "loginpage";
	}

	/**
	 * Handles and retrieves the denied JSP page. This is shown whenever a
	 * regular user tries to access an admin only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		logger.debug("Received request to show denied page");

		// This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "deniedpage";
	}
}
