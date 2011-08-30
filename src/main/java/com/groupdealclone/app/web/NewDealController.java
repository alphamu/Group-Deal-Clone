package com.groupdealclone.app.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.domain.Deal;
import com.groupdealclone.app.service.DealManager;

@Controller
public class NewDealController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
//	@Autowired
//	private DealManager dealManager;

	@RequestMapping(value = "new-deal-input", method = RequestMethod.GET)
	public String showForm(Map<String, Object> model) {
		Deal dealForm = new Deal();
		model.put("deal", dealForm);
		return "new-deal-input";
	}

	@RequestMapping(value = "new-deal-input", method = RequestMethod.POST)
	public String processForm(@Valid Deal dealForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "new-deal-input";
		}
		model.put("deal", dealForm);
		//this.dealManager.getDeals().add(dealForm);
		return "new-deal-success";
	}
	
	public void setDealManager(DealManager dealManager){
//		this.dealManager = dealManager;
	}

}
