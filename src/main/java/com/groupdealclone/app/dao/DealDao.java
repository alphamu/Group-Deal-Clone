package com.groupdealclone.app.dao;

import java.util.List;

import com.groupdealclone.app.domain.Deal;

public interface DealDao {
	public Deal getDeal(Long id);
	
	public List<Deal> getDeals();
	
}
