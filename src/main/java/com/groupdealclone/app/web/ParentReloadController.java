package com.groupdealclone.app.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ParentReloadController{
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ParentReloadController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/parentreload", method = RequestMethod.GET, headers="Accept=text/html")
	public String home(Locale locale, Model model) {		
		return "parentreload";	
	}

	
}
