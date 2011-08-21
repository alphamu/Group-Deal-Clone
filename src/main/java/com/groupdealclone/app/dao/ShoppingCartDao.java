package com.groupdealclone.app.dao;

import com.groupdealclone.app.domain.ShoppingCart;

public interface ShoppingCartDao {
	public ShoppingCart getShoppingCart(String username);
	public void update(ShoppingCart cart);
}
