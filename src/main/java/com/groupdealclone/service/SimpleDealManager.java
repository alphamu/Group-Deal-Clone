package com.groupdealclone.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Deal;

@Service
public class SimpleDealManager implements DealManager {

	private Set<Deal> deals;

	public Set<Deal> getDeals() {
		return deals;
	}

	@Override
	public void setDeals(Set<Deal> deals) {
		this.deals = deals;
	}

}
