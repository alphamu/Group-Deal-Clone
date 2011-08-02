package com.groupdealclone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Deal;

@Service
public interface DealManager {

	public List<Deal> getDeals();

	public void setDeals(List<Deal> deals);

}
