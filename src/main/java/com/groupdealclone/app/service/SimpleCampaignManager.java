package com.groupdealclone.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.Company;
import com.groupdealclone.app.domain.Deal;

@Service
public class SimpleCampaignManager implements CampaignManager {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;
	
	@PersistenceContext EntityManager em;

	public Campaign getCampaign(Long id) {
		return null;
	}

	@Transactional
	public void setCampaign(Campaign campaign) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Deal d = campaign.getDeal();
		Company c = campaign.getCompany();
		em.persist(d);
		em.persist(c);
		em.persist(campaign);
		em.getTransaction().commit();
		
	}

	public void setCampaign(Long id, Campaign campaign) {

	}

	public void setCampaigns(List<Campaign> campaigns) {

	}

	public List<Campaign> getCampaigns() {
		List<Campaign> cats = em.createQuery("FROM Campaign camp WHERE ?1 BETWEEN camp.startDate AND camp.endDate", Campaign.class)
		 .setParameter(1, new java.util.Date(), TemporalType.DATE)
				.getResultList();

		return cats;
	}

	@Transactional
	public <T> void save(T t) {
		// begin transaction
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		if (!em.contains(t)) {
			// persist object - add to entity manager
			em.persist(t);
			// flush em - save to DB
			em.flush();
		}
		// commit transaction at all
		em.getTransaction().commit();
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
