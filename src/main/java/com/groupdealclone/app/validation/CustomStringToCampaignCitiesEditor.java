package com.groupdealclone.app.validation;

import java.beans.PropertyEditorSupport;

import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.service.CityManager;

public class CustomStringToCampaignCitiesEditor extends PropertyEditorSupport {
	
	private CityManager cityManager;
	
	private CustomStringToCampaignCitiesEditor(){
		super();
	}
	
	public CustomStringToCampaignCitiesEditor(CityManager cityManager){
		this();
		this.cityManager = cityManager;
	}
	
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
}
