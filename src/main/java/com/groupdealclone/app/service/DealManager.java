package com.groupdealclone.app.service;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.Deal;

@Service
@XmlRootElement
public interface DealManager {
	
//	public Set<Deal> getDeals();
	
	public Deal getDeal(Long id);

//	public void setDeals(Set<Deal> deals);

}
