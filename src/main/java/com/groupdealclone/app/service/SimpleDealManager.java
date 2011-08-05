package com.groupdealclone.app.service;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.Deal;

@Service
@XmlRootElement
@XmlType
public class SimpleDealManager implements DealManager {

	private Set<Deal> deals;
	
	public SimpleDealManager() {}

	@XmlElement(name="deal")
	public Set<Deal> getDeals() {
		return deals;
	}

	@Override
	public void setDeals(Set<Deal> deals) {
		this.deals=deals;
		
	}

	@Override
	public Deal getDeal(Long id) {
		for(Deal d:deals){
			if(d.getId().equals(id))
				return d;
		}
		return null;
	}

}
