package com.groupdealclone.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Campaign;

@Repository
@Transactional
public class JdbcCampaignDao implements CampaignDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Campaign> getCampaignList() {
		List<Campaign> camp = em
				.createQuery(
						"FROM Campaign camp WHERE ?1 BETWEEN camp.startDate AND camp.endDate",
						Campaign.class)
				.setParameter(1, new java.util.Date(), TemporalType.DATE)
				.getResultList();

		return camp;
	}

	@Override
	@Transactional
	public void saveCampaign(Campaign campaign) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(campaign);
		em.getTransaction().commit();
	}

	@Override
	public Campaign getCampaign(Long id) {
		Campaign camp = em.find(Campaign.class,id);
		return camp;
	}

	EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	@Transactional
	public void updateCampaign(Campaign camp) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(camp);
		em.getTransaction().commit();
		
	}

}
