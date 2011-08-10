package com.groupdealclone.app.web;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CityManager;
import com.groupdealclone.app.validation.CampaignValidator;

@Controller
@SessionAttributes(value={"campaignCities"})
public class EditCampaignController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CampaignManager campaignManager;
	@Autowired
	private CityManager cityManager;
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;

	@RequestMapping(value = "campaign/{campaignId}/edit", method = RequestMethod.GET)
	public String showForm(@PathVariable("campaignId") long petId, Map<String, Object> model) {
		Campaign campaignForm = this.campaignManager.getCampaign(new Long(petId));
		CampaignCities cities = new CampaignCities();
		cities.setCities(new HashSet<City>(cityManager.getCity()));
		cities.setId(campaignForm.getCampaignCities().getId());
		model.put("campaignCities", cities);
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
		model.put("campaigns", this.campaignManager.getCampaigns());
		this.campaignManager.updateCampaign(campaignForm);
		return "campaign/added";
	}
	
	public void setCampaignManager(CampaignManager dealManager){
		this.campaignManager = dealManager;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, dateEditor);
	    
	    binder.registerCustomEditor(CampaignCities.class, "campaignCities", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String text) {
	        	Object object = getValue();
	        	String [] ids = text.split(",");
	        	CampaignCities cities = null;
	        	for(String id:ids){
	        		if(cities == null)
	        			cities = new CampaignCities();
	        		City city = cityManager.getCity(new Long(id));
	        		if(city != null)
	        			cities.getCities().add(city);
	        		
	        	}
	        	if(cities != null){
	        		cities.setId(null);
	        		setValue(cities);
	        	}
	        }
	        
	    });
	}

}
