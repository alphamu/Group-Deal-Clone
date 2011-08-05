package com.groupdealclone.app.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.Deal;

@Service
public interface DealManager {
	
	public Set<Deal> getDeals();
	
	public Deal getDeal(Long id);

	public void setDeals(Set<Deal> deals);

}
