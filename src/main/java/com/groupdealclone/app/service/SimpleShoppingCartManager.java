package com.groupdealclone.app.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.groupdealclone.app.dao.ShoppingCartDao;
import com.groupdealclone.app.domain.Account;
import com.groupdealclone.app.domain.Deal;
import com.groupdealclone.app.domain.ShoppingCart;

public class SimpleShoppingCartManager implements ShoppingCartManager {

	@Autowired
	ShoppingCartDao shoppingCartDao;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired DealManager dealManager;
	
	/***
	 * Return new cart for the User if no cart exists
	 */
	@Override
	public ShoppingCart getShoppingCart(String username) {
		ShoppingCart cart = shoppingCartDao.getShoppingCart(username);
		if(cart  == null) {
			Account account = (Account) userDetailsService.loadUserByUsername(username);
			cart = new ShoppingCart();
			cart.setAccount(account);
		}
		return cart;
	}

	@Override
	public void add(ShoppingCart cart, Long dealId) {
		Deal deal = dealManager.getDeal(dealId);
		List<Deal> deals = cart.getDeals();
		deals.add(deal);
		shoppingCartDao.update(cart);
	}

	@Override
	public void remove(ShoppingCart cart, Long dealId) {
		Deal deal = dealManager.getDeal(dealId);
		List<Deal> deals = cart.getDeals();
		deals.remove(deal);
		shoppingCartDao.update(cart);
	}

	@Override
	public void add(String username, Long dealId) {
		ShoppingCart cart = getShoppingCart(username);
		add(cart,dealId);
		
	}

	@Override
	public void remove(String username, Long dealId) {
		ShoppingCart cart = getShoppingCart(username);
		remove(cart,dealId);
	}

	@Override
	public List<Deal> getDealsSorted(String username) {
		ShoppingCart cart = getShoppingCart(username);
		List<Deal> deals = cart.getDeals();
		Collections.sort(deals, new Comparator<Deal>() {
			@Override
			public int compare(Deal o1, Deal o2) {
				return o1.getDescription().compareTo(o2.getDescription());
			}});
		return deals;
	}

}
