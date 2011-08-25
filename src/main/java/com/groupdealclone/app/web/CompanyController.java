package com.groupdealclone.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.groupdealclone.app.domain.Company;
import com.groupdealclone.app.service.CompanyManager;

@Controller
public class CompanyController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CompanyManager companyManager;
	@Autowired
	SimpleDateFormat dateFormat;
	@Autowired
	CustomDateEditor dateEditor;

	@RequestMapping(value = "company/new", method = RequestMethod.GET)
	public String showNewForm(Map<String, Object> model) {
		Company companyForm = new Company();
		model.put("company", companyForm);
		return "company/new";
	}

	@RequestMapping(value = "company/new", method = RequestMethod.POST)
	public String processNewForm(@Valid Company companyForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "company/new";
		}
		this.companyManager.saveCompany(companyForm);
		model.put("company", companyForm);
		model.put("companys", this.companyManager.getCompany());
		return "company/added";
	}
	
	@RequestMapping(value = "company/{companyId}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("companyId") long companyId, Map<String, Object> model) {
		Company companyForm = this.companyManager.getCompany(new Long(companyId));
		model.put("company", companyForm);
		return "company/edit";
	}

	@RequestMapping(value = "company/{companyId}/edit", method = RequestMethod.POST)
	public String processEditForm(@Valid Company companyForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "company/edit";
		}
		model.put("company", companyForm);
		//model.put("companys", this.companyManager.getCompany());
		this.companyManager.updateCompany(companyForm);
		return "company/added";
	}
	
	@RequestMapping(value = "company/list", method = RequestMethod.GET, headers="Accept=application/xml, application/json")
	public @ResponseBody List<LabelHolderBean> homeXmlJson(@RequestParam(required=true, value="term") String q, Locale locale, Model model) {
		List<Company> co =  companyManager.getCompanies(q);
		List<LabelHolderBean> names = new LinkedList<LabelHolderBean>();
		for(Company c: co) {
			names.add(new LabelHolderBean(c.getId(), c.getName()));
		}
		
		return names;
		
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

