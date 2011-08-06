package com.groupdealclone.app.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.groupdealclone.app.service.DealManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private DealManager dealManager;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET, headers="Accept=text/html")
	public ModelAndView home(Locale locale, Model model) {	
		logger.info("Rendering home in HTML {}", locale.toString());
		return new ModelAndView("home-tile", "deals", this.dealManager);	
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody DealManager homeXmlJson(Locale locale, Model model) {
	
		logger.info("Rendering home in XML or JSON {} ", locale.toString());
		
		return this.dealManager;
	}
	
}
