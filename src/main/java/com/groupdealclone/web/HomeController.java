package com.groupdealclone.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.groupdealclone.service.DealManager;
import com.groupdealclone.util.HibernateUtil;

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
		//just to test
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("Welcome home! the client locale is {} ", locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("serverTime", formattedDate);
        myModel.put("deals", this.dealManager.getDeals());
		
		return new ModelAndView("home", "model", myModel);
		
	}
	
	public void setDealManager(DealManager dealManager){
		this.dealManager = dealManager;
	}

	
}
