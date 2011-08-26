package com.groupdealclone.app.validation;

import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.service.CityManager;

public class CustomCityPropertyEditor extends PropertyEditorSupport {

	CityManager	cityManager;

	public CustomCityPropertyEditor(CityManager cityMan) {
		this.cityManager = cityMan;
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof CampaignCities) {
			Set<City> l = (Set<City>) ((CampaignCities) value).getCities();
			if (l != null && l.size() > 0) {
				StringBuffer sb = null;
				for (City c : l) {
					if (sb != null) {
						sb.append("," + c.getName());
					} else {
						sb = new StringBuffer(c.getName());
					}
				}
				super.setValue(sb.toString());
			} else
				super.setValue("");
		} else
			super.setValue("");
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
			List<City> cats = cityManager.getCities(namesIn);
			CampaignCities cities = new CampaignCities();
			cities.setCities(new HashSet<City>(cats));
			super.setValue(cities);
		}
	}
}
