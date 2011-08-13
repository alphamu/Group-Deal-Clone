package com.groupdealclone.app.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CityManager;
import com.groupdealclone.app.validation.CampaignValidator;

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

		binder.registerCustomEditor(CampaignCities.class, "campaignCities", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				String[] ids = text.split(",");
				CampaignCities cities = null;
				for (String id : ids) {
					if (cities == null)
						cities = new CampaignCities();
					City city = cityManager.getCity(new Long(id));
					if (city != null)
						cities.getCities().add(city);

				}
				if (cities != null) {
					cities.setId(null);
					setValue(cities);
				}
			}

		});

		binder.registerCustomEditor(ImageStore.class, "imageStore", new PropertyEditorSupport() {
			@Override
			public void setValue(Object value) {
				if (value instanceof List) {
					List<Image> images = new LinkedList<Image>();
					for (Object val : ((List<?>) value)) {
						Image image = new Image();
						if (val instanceof MultipartFile) {
							MultipartFile multipartFile = (MultipartFile) val;
							try {
								byte[] bytes = multipartFile.getBytes();
								if (bytes.length > 0) {
									image.setImage(bytes);
									images.add(image);
								}
							} catch (IOException ex) {
								throw new IllegalArgumentException("Cannot read contents of multipart file", ex);
							}
						} else if (val instanceof byte[]) {
							if (((byte[]) val).length > 0) {
								image.setImage((byte[]) val);
								images.add(image);
							}
						} else {
							if (val != null) {
								byte[] bytes = val.toString().getBytes();
								if (bytes.length > 0) {
									image.setImage(bytes);
									images.add(image);
								}
							}
						}
					}
					if (images.size() > 0) {
						ImageStore img = new ImageStore();
						img.setImage(images);
						super.setValue(img);
					} else {
						super.setValue(null);
					}
				} // if instance of List
				else if (value instanceof MultipartFile) {
					List<Image> images = new LinkedList<Image>();
					Image image = new Image();
					MultipartFile multipartFile = (MultipartFile) value;
					try {
						byte[] bytes = multipartFile.getBytes();
						if (bytes.length > 0) {
							image.setImage(bytes);
							images.add(image);
						}
					} catch (IOException ex) {
						throw new IllegalArgumentException("Cannot read contents of multipart file", ex);
					}
					if (images.size() > 0) {
						ImageStore imageStore = new ImageStore();
						imageStore.setImage(images);
						super.setValue(imageStore);
					} else {
						super.setValue(null);
					}
				}
			}
		});

	}

}
