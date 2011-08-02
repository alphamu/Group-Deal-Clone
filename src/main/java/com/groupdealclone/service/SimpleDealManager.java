package com.groupdealclone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Deal;

@Service
public class SimpleDealManager implements DealManager {

	private List<Deal> deals;
	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

}
