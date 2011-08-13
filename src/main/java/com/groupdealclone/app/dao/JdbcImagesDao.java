package com.groupdealclone.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

@Transactional
@Repository
public class JdbcImagesDao implements ImagesDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;

	@PersistenceContext
	EntityManager em;
	
	@Override
	public ImageStore getImages(Long id) {
		ImageStore images = em.find(ImageStore.class,id);
		return images;
	}

	@Override
	public void saveImages(ImageStore images) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		//em.persist(campaign.getCities());
		em.merge(images);
		em.getTransaction().commit();
	}
	@Override
	public void updateImages(ImageStore images) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(images);
		em.getTransaction().commit();
	}

	@Override
	public Image getImage(Long imageId) {
		Image image = em.find(Image.class,imageId);
		return image;
	}

	@Override
	public void setImage(Image image) {

	}
	
	EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
