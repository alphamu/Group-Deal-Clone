package com.groupdealclone.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.domain.Campaign;
import com.groupdealclone.service.CampaignManager;
import com.groupdealclone.validation.CampaignValidator;

@Controller
public class EditCampaignController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CampaignManager campaignManager;
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;

	@RequestMapping(value = "campaign/{campaignId}/edit", method = RequestMethod.GET)
	public String showForm(@PathVariable("campaignId") long petId, Map<String, Object> model) {
		Campaign campaignForm = this.campaignManager.getCampaign(new Long(petId));
		model.put("campaign", campaignForm);
		return "campaign/edit";
	}

	@RequestMapping(value = "campaign/{campaignId}/edit", method = RequestMethod.POST)
	public String processForm(@Valid Campaign campaignForm, BindingResult result, Map<String,Object> model) {
		new CampaignValidator().validate(campaignForm, result);
		if (result.hasErrors()) {
			return "campaign/edit";
		}
		model.put("campaign", campaignForm);
		this.campaignManager.setCampaign(campaignForm.getId(), campaignForm);
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
