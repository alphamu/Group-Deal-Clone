package com.groupdealclone.app.web;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CategoryManager;
import com.groupdealclone.app.service.CompanyManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	@Autowired
//	private DealManager dealManager;
	@Autowired
	private CampaignManager campaignManager;
	@Autowired
	private CompanyManager companyManager;
	@Autowired
	private CategoryManager categoryManager;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET, headers="Accept=text/html")
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		String location = "Any";
		boolean found = false;
		for(Cookie c : request.getCookies()) {
			if(c.getName().equals("location")) {
				found = true;
				location = c.getValue();
				break;
			}
		}
		if(found == false) {
			response.addCookie(new Cookie("location","Any"));
		}

		model.addAttribute("location", location);
		model.addAttribute("featured", campaignManager.getFeaturedCampaigns());
		model.addAttribute("regular", campaignManager.getRegularCampaigns());
		model.addAttribute("companies", companyManager.getCompany());
		model.addAttribute("categories", categoryManager.getCategories());
		
		
		
		logger.info("Rendering home in HTML {}", locale.toString());
		return "home-tile";	
	}
	
//	@RequestMapping(value = "/home", method = RequestMethod.GET, headers="Accept=application/xml, application/json")
//	public @ResponseBody DealManager homeXmlJson(Locale locale, Model model) {
//	
//		logger.info("Rendering home in XML or JSON {} ", locale.toString());
//		
//		return this.dealManager;
//	}
	
}
