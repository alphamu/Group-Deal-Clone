package com.groupdealclone.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Deal;

@Service
public interface DealManager {

	public Set<Deal> getDeals();

	public void setDeals(Set<Deal> deals);

}
