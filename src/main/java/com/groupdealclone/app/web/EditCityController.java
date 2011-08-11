package com.groupdealclone.app.web;

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

import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.service.CityManager;

@Controller
public class EditCityController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CityManager cityManager;
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;

	@RequestMapping(value = "city/{cityId}/edit", method = RequestMethod.GET)
	public String showForm(@PathVariable("cityId") long cityId, Map<String, Object> model) {
		City cityForm = this.cityManager.getCity(new Long(cityId));
		model.put("city", cityForm);
		return "city/edit";
	}

	@RequestMapping(value = "city/{cityId}/edit", method = RequestMethod.POST)
	public String processForm(@Valid City cityForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "city/edit";
		}
		model.put("city", cityForm);
		//model.put("citys", this.cityManager.getCity());
		this.cityManager.updateCity(cityForm);
		return "city/added";
	}
	
	public void setCityManager(CityManager dealManager){
		this.cityManager = dealManager;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, dateEditor);
	}

}
