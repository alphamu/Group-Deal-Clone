package com.groupdealclone.app.web;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.Category;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.domain.ImageStore;
import com.groupdealclone.app.exception.CompanyNotFoundException;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CategoryManager;
import com.groupdealclone.app.service.CityManager;
import com.groupdealclone.app.validation.CampaignValidator;
import com.groupdealclone.app.validation.CustomByteArrayToImageStoreEditor;
import com.groupdealclone.app.validation.CustomStringToCampaignCitiesEditor;

@SessionAttributes(value = { "campaignCities" })
@Controller
public class NewCampaignController {
	
	private static final Logger	logger	= LoggerFactory.getLogger(NewCampaignController.class);

	@Autowired
	private CampaignManager		campaignManager;
	@Autowired
	private CityManager			cityManager;
	@Autowired
	private CategoryManager		categoryManager;
	@Autowired
	SimpleDateFormat			dateFormat;
	@Autowired
	CustomDateEditor			dateEditor;

	@RequestMapping(value = "campaign/new", method = RequestMethod.GET)
	public String showForm(Map<String, Object> model) {
		Campaign campaignForm = new Campaign();
		CampaignCities cities = new CampaignCities();
		cities.setCities(new HashSet<City>(cityManager.getCity()));
		model.put("campaignCities", cities);
		model.put("campaign", campaignForm);
		return "campaign/new";
	}

	@RequestMapping(value = "campaign/new", method = RequestMethod.POST)
	public String processForm(@Valid Campaign campaignForm, BindingResult result, Map<String, Object> model) {
		new CampaignValidator().validate(campaignForm, result);
		if (campaignForm.getImageStore() == null || campaignForm.getImageStore().getImage() == null || campaignForm.getImageStore().getImage().size() == 0) {
			result.rejectValue("imageStore", "validation.required");
		}
		if (result.hasErrors()) {
			return "campaign/new";
		}
		try {
			this.campaignManager.saveCampaign(campaignForm);
		} catch (CompanyNotFoundException e) {
			logger.error("Error while trying to add new campaign {}", e);
			result.rejectValue("company.name", "company.not.found", e.getMessage());
			return "campaign/new";
		}
		model.put("campaign", campaignForm);
		model.put("campaigns", this.campaignManager.getCampaigns());
		return "campaign/added";
	}

	public void setCampaignManager(CampaignManager campaignManager) {
		this.campaignManager = campaignManager;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, dateEditor);

		binder.registerCustomEditor(ImageStore.class, "imageStore", new CustomByteArrayToImageStoreEditor());

		binder.registerCustomEditor(CampaignCities.class, "campaignCities", new CustomStringToCampaignCitiesEditor(cityManager));

		binder.registerCustomEditor(List.class, "categories", new PropertyEditorSupport() {
			@Override
			public void setValue(Object value) {
				if (value instanceof List) {
					@SuppressWarnings("unchecked")
					List<Category> l = (List<Category>) value;
					StringBuffer sb = null;
					for (Category c : l) {
						if (sb != null) {
							sb.append("," + c.getName());
						} else {
							sb = new StringBuffer(c.getName());
						}
					}
					super.setValue(sb.toString());
				}
			}

			@Override
			public void setAsText(String value) {
				if (value != null) {
					String val = value.trim();
					if (val.length() == 0 || val.equals(",")) {
						super.setValue(null);
						return;
					}

					String[] namesIn = val.split(",");
					for (int i = 0; i < namesIn.length; i++) {
						namesIn[i] = namesIn[i].trim();
					}
					List<Category> cats = categoryManager.getCategories(namesIn);
					super.setValue(cats);
				}
			}

		});

	}
}
