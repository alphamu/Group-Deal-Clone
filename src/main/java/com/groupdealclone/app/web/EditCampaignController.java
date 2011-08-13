package com.groupdealclone.app.web;

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
import com.groupdealclone.app.domain.ImageStore;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CityManager;
import com.groupdealclone.app.validation.CampaignValidator;
import com.groupdealclone.app.validation.CustomByteArrayToImageStoreEditor;
import com.groupdealclone.app.validation.CustomStringToCampaignCitiesEditor;

@Controller
@SessionAttributes(value = { "campaignCities" })
public class EditCampaignController {
	// private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);

	@Autowired
	private CampaignManager	campaignManager;
	@Autowired
	private CityManager		cityManager;
	@Autowired
	SimpleDateFormat		dateFormat;
	@Autowired
	CustomDateEditor		dateEditor;

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
	public String processForm(@PathVariable("campaignId") long campaignId, @Valid Campaign campaignForm, BindingResult result, Map<String, Object> model) {
		new CampaignValidator().validate(campaignForm, result);

		// we want to make sure at least one picture is already attached or being uploaded.
		if (campaignForm.getImageStore() == null) {
			// no files uploaded, make sure there are some already there at least.
			Campaign camp = campaignManager.getCampaign(campaignId);
			if (camp.getImageStore() == null || camp.getImageStore().getImage() == null || camp.getImageStore().getImage().size() == 0) {
				result.rejectValue("imageStore", "validation.required");
			}
		}

		if (result.hasErrors()) {
			return "campaign/edit";
		}

		model.put("campaign", campaignForm);
		model.put("campaigns", this.campaignManager.getCampaigns());
		this.campaignManager.updateCampaign(campaignForm);
		return "campaign/added";
	}

	@RequestMapping(value = "campaign/{campaignId}/i/r/{imageId}", method = RequestMethod.GET)
	public String removeImage(@PathVariable("campaignId") long campaignId, @PathVariable("imageId") long imageId, @Valid Campaign campaignForm,
			BindingResult result, Map<String, Object> model) {
		campaignManager.removeImage(campaignId, imageId);
		return showForm(campaignId, model);
	}

	public void setCampaignManager(CampaignManager dealManager) {
		this.campaignManager = dealManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, dateEditor);

		binder.registerCustomEditor(CampaignCities.class, "campaignCities", new CustomStringToCampaignCitiesEditor(cityManager));

		binder.registerCustomEditor(ImageStore.class, "imageStore", new CustomByteArrayToImageStoreEditor());

	}

}
