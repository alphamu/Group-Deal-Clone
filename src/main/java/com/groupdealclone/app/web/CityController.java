package com.groupdealclone.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.service.CityManager;

@Controller
public class CityController {
	// private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);

	@Autowired
	private CityManager	cityManager;
	@Autowired
	SimpleDateFormat	dateFormat;
	@Autowired
	CustomDateEditor	dateEditor;

	@RequestMapping(value = "city/new", method = RequestMethod.GET)
	public String showNewForm(Map<String, Object> model) {
		City cityForm = new City();
		model.put("city", cityForm);
		return "city/new";
	}

	@RequestMapping(value = "city/new", method = RequestMethod.POST)
	public String processNewForm(@Valid City cityForm, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return "city/new";
		}
		this.cityManager.saveCity(cityForm);
		model.put("city", cityForm);
		model.put("citys", this.cityManager.getCity());
		return "city/added";
	}

	@RequestMapping(value = "city/{cityId}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("cityId") long cityId, Map<String, Object> model) {
		City cityForm = this.cityManager.getCity(new Long(cityId));
		model.put("city", cityForm);
		return "city/edit";
	}

	@RequestMapping(value = "city/{cityId}/edit", method = RequestMethod.POST)
	public String processEditForm(@Valid City cityForm, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return "city/edit";
		}
		model.put("city", cityForm);
		this.cityManager.updateCity(cityForm);
		return "city/added";
	}

	@RequestMapping(value = "city/list", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody List<LabelHolderBean> homeXmlJson(@RequestParam(required = true, value = "term") String q, Locale locale, Model model) {
		String query = q;
		if (q.lastIndexOf(',') > 0)
			query = query.substring(q.lastIndexOf(',') + 1).trim();

		if(query.length() < 2)
			return null;
		
		List<City> co = cityManager.getCities(query);
		List<LabelHolderBean> names = new LinkedList<LabelHolderBean>();
		for (City c : co) {
			names.add(new LabelHolderBean(c.getId(), c.getName()));
		}

		return names;

	}
	
	@RequestMapping(value = "/location", method = RequestMethod.GET, headers="Accept=text/html")
	public String setLocation(Locale locale, Model model) {
		model.addAttribute("cities",cityManager.getCity());
		return "location";	
	}
	
	@RequestMapping(value = "/location", method = RequestMethod.POST, headers="Accept=text/html")
	public String setLocation(@RequestParam("city") String cityName, Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = null;
		for(Cookie c: request.getCookies()){
			if(c.getName().equals("location"))
				cookie = c;
		}
		if(cookie == null) {
			cookie = new Cookie("location", cityName);
		} else {
			cookie.setValue(cityName);
		}
		response.addCookie(cookie);
		
		return "redirect:parentreload";	
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

}
