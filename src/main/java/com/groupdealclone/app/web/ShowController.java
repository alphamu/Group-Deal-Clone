package com.groupdealclone.app.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CategoryManager;
import com.groupdealclone.app.service.CompanyManager;
import com.groupdealclone.app.service.DealManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShowController{
	
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	@Autowired
	private DealManager dealManager;
	@Autowired
	private CampaignManager campaignManager;
	@Autowired
	private CompanyManager companyManager;
	@Autowired
	private CategoryManager categoryManager;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/show/company/{companyId}", method = RequestMethod.GET, headers="Accept=text/html")
	public String showCompany(@CookieValue("location") String location, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(location == null || location.trim().length() == 0)
			location = "Not Set";

		logger.info("Rendering home in HTML {}", locale.toString());
		return "home-tile";	
	}
	
	@RequestMapping(value = "/show/categoru/{companyId}", method = RequestMethod.GET, headers="Accept=text/html")
	public String showCategory(@CookieValue("location") String location, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(location == null || location.trim().length() == 0)
			location = "Not Set";
		return "home-tile";
	}
	
	@RequestMapping(value = "/show/company/{companyId}", method = RequestMethod.GET, headers="Accept=text/html")
	public String showDeal(@CookieValue("location") String location, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(location == null || location.trim().length() == 0)
			location = "Not Set";
		return "home-tile";
	}
}
