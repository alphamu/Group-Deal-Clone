package com.groupdealclone.app.service;

import java.util.List;

import com.groupdealclone.app.domain.Deal;
import com.groupdealclone.app.domain.ShoppingCart;

public interface ShoppingCartManager {
	public ShoppingCart getShoppingCart(String username);
	public List<Deal> getDealsSorted(String username);
	public void add(ShoppingCart cart, Long dealId);
	public void remove(ShoppingCart cart, Long dealId);
	public void add(String username, Long dealId);
	public void remove(String username, Long dealId);
}
