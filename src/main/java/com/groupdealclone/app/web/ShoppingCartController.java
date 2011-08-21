package com.groupdealclone.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.domain.Deal;
import com.groupdealclone.app.service.ShoppingCartManager;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartManager shoppingCartManager;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showShoppingCart(ModelMap model) {
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Deal> deals = shoppingCartManager.getDealsSorted(username);
		model.put("deals", deals);
		
		return "shoppingcart";
	}
	
	@RequestMapping(value = "/add/{dealId}", method = RequestMethod.GET)
	public String addDeal(@PathVariable("dealId") long dealId, ModelMap model){
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		shoppingCartManager.add(username, dealId);
		//showShoppingCart(model);
		return "redirect:/shoppingcart/show";
	}
	
	@RequestMapping(value = "/remove/{dealId}", method = RequestMethod.GET)
	public String removeDeal(@PathVariable("dealId") long dealId, ModelMap model){
		final String username = SecurityContextHolder.getContext().getAuthentication().getName();
		shoppingCartManager.remove(username, dealId);		
		//showShoppingCart(model);
		return "redirect:/shoppingcart/show";
	}

}
