package com.groupdealclone.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.domain.Campaign;
import com.groupdealclone.service.CampaignManager;
import com.groupdealclone.validation.CampaignValidator;

@Controller
public class NewCampaignController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	
	private CampaignManager campaignManager;

	@RequestMapping(value = "new-campaign-input", method = RequestMethod.GET)
	public String showForm(Map<String, Object> model) {
		Campaign campaignForm = new Campaign();
		model.put("campaign", campaignForm);
		return "new-campaign-input";
	}

	@RequestMapping(value = "new-campaign-input", method = RequestMethod.POST)
	public String processForm(@Valid Campaign campaignForm, BindingResult result, Map<String,Object> model) {
		new CampaignValidator().validate(campaignForm, result);
		if (result.hasErrors()) {
			return "new-campaign-input";
		}
		model.put("campaign", campaignForm);
		//this.campaignManager.getCampaign();
		return "new-campaign-success";
	}
	
	public void setCampaignManager(CampaignManager dealManager){
		this.campaignManager = dealManager;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
