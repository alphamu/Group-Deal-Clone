package com.groupdealclone.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.SimpleCampaignManager;
import com.groupdealclone.app.validation.CampaignValidator;

@Controller
public class NewCampaignController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CampaignManager campaignManager;
	
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;

	@RequestMapping(value = "campaign/new", method = RequestMethod.GET)
	public String showForm(Map<String, Object> model) {
		//List<Campaign> campaigns = campaignManager.getCampaigns();
		Campaign campaignForm = new Campaign();
		model.put("campaign", campaignForm);
		return "campaign/new";
	}

	@RequestMapping(value = "campaign/new", method = RequestMethod.POST)
	public String processForm(@Valid Campaign campaignForm, BindingResult result, Map<String,Object> model) {
		new CampaignValidator().validate(campaignForm, result);
		if (result.hasErrors()) {
			return "campaign/new";
		}
		this.campaignManager.setCampaign(campaignForm);
		model.put("campaign", campaignForm);
		model.put("campaigns", this.campaignManager.getCampaigns());
		return "campaign/added";
	}
	
	public void setCampaignManager(CampaignManager dealManager){
		this.campaignManager = dealManager;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, dateEditor);
	}

}
