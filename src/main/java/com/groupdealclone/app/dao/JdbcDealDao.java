package com.groupdealclone.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.groupdealclone.app.domain.Deal;

public class JdbcDealDao implements DealDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Deal getDeal(Long id) {
		return em.find(Deal.class, id);
	}

	@Override
	public List<Deal> getDeals() {
		// TODO Auto-generated method stub
		return null;
	}


}
