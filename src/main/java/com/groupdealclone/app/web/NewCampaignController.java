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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.Images;
import com.groupdealclone.app.service.CampaignManager;
import com.groupdealclone.app.service.CityManager;
import com.groupdealclone.app.validation.CampaignValidator;

@SessionAttributes(value={"campaignCities"})
@Controller
public class NewCampaignController {
	private static final Logger logger = LoggerFactory.getLogger(NewCampaignController.class);
	
	@Autowired
	private CampaignManager campaignManager;
	@Autowired
	private CityManager cityManager;
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;


	@RequestMapping(value = "campaign/new", method = RequestMethod.GET)
	public String showForm(Map<String, Object> model) {
		//List<Campaign> campaigns = campaignManager.getCampaigns();
		Campaign campaignForm = new Campaign();
		CampaignCities cities = new CampaignCities();
		cities.setCities(new HashSet<City>(cityManager.getCity()));
		//campaignForm.setCities(cities);
		model.put("campaignCities", cities);
		model.put("campaign", campaignForm);
		return "campaign/new";
	}

	@RequestMapping(value = "campaign/new", method = RequestMethod.POST)
	public String processForm(@Valid Campaign campaignForm,  BindingResult result, Map<String,Object> model) {
		new CampaignValidator().validate(campaignForm, result);
		if (result.hasErrors()) {
			return "campaign/new";
		}
		this.campaignManager.saveCampaign(campaignForm);
		model.put("campaign", campaignForm);
		model.put("campaigns", this.campaignManager.getCampaigns());
		return "campaign/added";
	}
	
	public void setCampaignManager(CampaignManager campaignManager){
		this.campaignManager = campaignManager;
	}
	
	public void setCityManager(CityManager cityManager){
		this.cityManager = cityManager;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, dateEditor);
	    
		binder.registerCustomEditor(Images.class, "images",
				new PropertyEditorSupport() {
					@Override
					public void setValue(Object value) {
						if (value instanceof List) {
							List<Image> images = new LinkedList<Image>();
							for (Object val : ((List<?>) value)) {
								Image image = new Image();
								if (val instanceof MultipartFile) {
									MultipartFile multipartFile = (MultipartFile) val;
									try {
										image.setImage(multipartFile.getBytes());
										images.add(image);
									} catch (IOException ex) {
										throw new IllegalArgumentException(
												"Cannot read contents of multipart file",
												ex);
									}
								} else if (val instanceof byte[]) {
									image.setImage((byte[]) val);
									images.add(image);
								} else {
									image.setImage(val != null ? val.toString()
											.getBytes() : null);
									images.add(image);
								}
							}
							Images img = new Images();
							img.setImages(images.size() > 0 ? images : null);
							super.setValue(img);
						} // if instanceof List
						else if(value instanceof Images) {
							logger.debug("test");
						}
						else{
							logger.debug("test");
						}
					}
				});

	    
	    binder.registerCustomEditor(CampaignCities.class, "campaignCities", new PropertyEditorSupport() {
	        @Override
	        public void setAsText(String text) {
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
