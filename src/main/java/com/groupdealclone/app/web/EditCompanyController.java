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

import com.groupdealclone.app.domain.Company;
import com.groupdealclone.app.service.CompanyManager;

@Controller
public class EditCompanyController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CompanyManager companyManager;
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;

	@RequestMapping(value = "company/{companyId}/edit", method = RequestMethod.GET)
	public String showForm(@PathVariable("companyId") long companyId, Map<String, Object> model) {
		Company companyForm = this.companyManager.getCompany(new Long(companyId));
		model.put("company", companyForm);
		return "company/edit";
	}

	@RequestMapping(value = "company/{companyId}/edit", method = RequestMethod.POST)
	public String processForm(@Valid Company companyForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "company/edit";
		}
		model.put("company", companyForm);
		//model.put("companys", this.companyManager.getCompany());
		this.companyManager.updateCompany(companyForm);
		return "company/added";
	}
	
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, dateEditor);
	}

}
