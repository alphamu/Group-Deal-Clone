package com.groupdealclone.app.service;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupdealclone.app.dao.DealDao;
import com.groupdealclone.app.domain.Deal;

@Service
@XmlRootElement(name = "deals")
public class SimpleDealManager implements DealManager {

	@Autowired
	DealDao dealDao;

	public SimpleDealManager() {}

//	@XmlElement(name = "deal")
//	public Set<Deal> getDeals() {
//		return deals;
//	}
//
//	@Override
//	public void setDeals(Set<Deal> deals) {
//		this.deals = deals;
//
//	}

	@Override
	public Deal getDeal(Long id) {
		return dealDao.getDeal(id);
	}

}
