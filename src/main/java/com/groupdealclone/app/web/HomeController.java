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

import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CategoryManager;
import com.groupdealclone.app.service.CompanyManager;
import com.groupdealclone.app.service.DealManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	@RequestMapping(value = "/home", method = RequestMethod.GET, headers="Accept=text/html")
	public String home(Locale locale, Model model) {		
		
		model.addAttribute("featured", campaignManager.getFeaturedCampaigns());
		model.addAttribute("regular", campaignManager.getRegularCampaigns());
		model.addAttribute("company", companyManager.getCompany());
		model.addAttribute("categories", categoryManager.getCategories());
		
		logger.info("Rendering home in HTML {}", locale.toString());
		return "home-tile";	
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody DealManager homeXmlJson(Locale locale, Model model) {
	
		logger.info("Rendering home in XML or JSON {} ", locale.toString());
		
		return this.dealManager;
	}
	
}
