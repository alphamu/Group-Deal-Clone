package com.groupdealclone.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.Category;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CategoryManager;
import com.groupdealclone.app.service.CityManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShowController{
	
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
//	@Autowired
//	private DealManager dealManager;
	@Autowired
	private CampaignManager campaignManager;
//	@Autowired
//	private CompanyManager companyManager;
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private CityManager cityManager;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/show/company/{companyId}", method = RequestMethod.GET, headers="Accept=text/html")
	public String showCompany(@CookieValue("location") String location, @PathVariable("companyId") long theId, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(location == null || location.trim().length() == 0)
			location = "Any";

		logger.info("Rendering home in HTML {}", locale.toString());
		
		List<Campaign>campaigns = campaignManager.getCampaigns();
		List<City> cities = cityManager.getCities(location);
		City currentCity = null;
		if(cities.size() > 0)
			currentCity = cities.get(0);
		
		List<Campaign> regular = new ArrayList<Campaign>();
		List<Campaign> featured = new ArrayList<Campaign>();
		
		for(Campaign c : campaigns) {
			if(c.getCompany().getId() == theId) {
				boolean add = false;
				Set<City> citiesSet = c.getCampaignCities().getCities();
				if(currentCity == null)
					add = true;
				else if(citiesSet.contains(currentCity))
					add = true;
				
				if(add) {
					if(c.isFeatured()) {
						featured.add(c);
					} else {
						regular.add(c);
					}
				}
			}
		}
		model.addAttribute("featured", featured);
		model.addAttribute("regular", regular);
		model.addAttribute("location",location);
		return "show-tile";	
	}
	
	@RequestMapping(value = "/show/category/{categoryId}", method = RequestMethod.GET, headers="Accept=text/html")
	public String showCategory(@CookieValue("location") String location, @PathVariable("categoryId") long theId, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(location == null || location.trim().length() == 0)
			location = "Any";

		logger.info("Rendering home in HTML {}", locale.toString());
		
		List<Campaign>campaigns = campaignManager.getCampaigns();
		List<City> cities = cityManager.getCities(location);
		City currentCity = null;
		if(cities.size() > 0)
			currentCity = cities.get(0);
		
		Category cat = categoryManager.getCategory(theId);
		
		List<Campaign> regular = new ArrayList<Campaign>();
		List<Campaign> featured = new ArrayList<Campaign>();
		
		for(Campaign c : campaigns) {
			if(cat != null && c.getCampaignCategories().getCategories().contains(cat)) {
				boolean add = false;
				Set<City> citiesSet = c.getCampaignCities().getCities();
				if(currentCity == null)
					add = true;
				else if(citiesSet.contains(currentCity))
					add = true;
				
				if(add) {
					if(c.isFeatured()) {
						featured.add(c);
					} else {
						regular.add(c);
					}
				}
			}
		}
		model.addAttribute("featured", featured);
		model.addAttribute("regular", regular);
		model.addAttribute("location",location);
		return "show-tile";	
	}
	
	@RequestMapping(value = "/show/deal/{dealId}", method = RequestMethod.GET, headers="Accept=text/html")
	public String showDeal(@CookieValue("location") String location, @PathVariable("dealId") long theId, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		if(location == null || location.trim().length() == 0)
			location = "Any";

		logger.info("Rendering home in HTML {}", locale.toString());
		
		List<Campaign>campaigns = campaignManager.getCampaigns();
		List<City> cities = cityManager.getCities(location);
		City currentCity = null;
		if(cities.size() > 0)
			currentCity = cities.get(0);
		
		List<Campaign> regular = new ArrayList<Campaign>();
		List<Campaign> featured = new ArrayList<Campaign>();
		
		for(Campaign c : campaigns) {
			if(c.getDeal().getId() == theId) {
				boolean add = false;
				Set<City> citiesSet = c.getCampaignCities().getCities();
				if(currentCity == null)
					add = true;
				else if(citiesSet.contains(currentCity))
					add = true;
				
				if(add) {
					if(c.isFeatured()) {
						featured.add(c);
					} else {
						regular.add(c);
					}
				}
			}
		}
		model.addAttribute("featured", featured);
		model.addAttribute("regular", regular);
		model.addAttribute("location",location);
		return "show-tile";	

	}
}
