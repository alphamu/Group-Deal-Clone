package com.groupdealclone.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.Images;

public class JdbcImagesDao implements ImagesDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Images getImages(Long id) {
		Images images = em.find(Images.class,id);
		return images;
	}

	@Override
	public void saveImages(Images images) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		//em.persist(campaign.getCities());
		em.merge(images);
		em.getTransaction().commit();
	}
	@Override
	public void updateImages(Images images) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(images);
		em.getTransaction().commit();
	}

	@Override
	public Image getImage(Campaign campaign, Long campaignId) {
		return null;
	}

	@Override
	public void setImage(Image image) {

	}
	
	EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
