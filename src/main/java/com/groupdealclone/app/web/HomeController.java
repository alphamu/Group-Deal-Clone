package com.groupdealclone.app.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.groupdealclone.app.domain.Deal;
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
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		
		logger.info("Welcome home! the client locale is {} ", locale.toString());
		
		return new ModelAndView("homeView", "deals", this.dealManager);
		
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView homeXml(Locale locale, Model model) {
	
		logger.info("Welcome home! the client locale is {} ", locale.toString());
		
		return new ModelAndView("homeView", "deals", this.dealManager);
		
	}
	
	@RequestMapping(value = "/home/{dealId}", method = RequestMethod.GET)
	@ResponseBody
	public Deal getDeal(@PathVariable("dealId") Long id){
		return dealManager.getDeal(id);
	}
	
	public void setDealManager(DealManager dealManager){
		this.dealManager = dealManager;
	}

	
}
