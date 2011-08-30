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
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

@Repository
@Transactional
public class JdbcCampaignDao implements CampaignDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory	emf;

	@PersistenceContext
	EntityManager			em;

	@Override
	public List<Campaign> getCampaignList(CampaignType campaignType) {
		String query = "FROM Campaign camp WHERE ?1 BETWEEN camp.startDate AND camp.endDate";
		switch (campaignType) {
		case FEATURED_ONLY:
			query += " AND featured=true";
			break;
		case REGULAR_ONLY:
			query += " AND featured=false";
			break;
		}
		List<Campaign> camp = em.createQuery(query, Campaign.class).setParameter(1, new java.util.Date(), TemporalType.DATE).getResultList();

		return camp;
	}

	@Override
	public List<Campaign> getCampaignList() {
		return getCampaignList(CampaignType.ALL);
	}

	@Override
	public List<Campaign> getRegularCampaignList() {
		return getCampaignList(CampaignType.REGULAR_ONLY);
	}

	@Override
	public List<Campaign> getFeaturedCampaignList() {
		return getCampaignList(CampaignType.FEATURED_ONLY);
	}

	@Override
	@Transactional
	public void saveCampaign(Campaign campaign) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			// em.persist(campaign.getCities());
			em.merge(campaign);
			em.getTransaction().commit();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public Campaign getCampaign(Long id) {
		Campaign camp = em.find(Campaign.class, id);
		return camp;
	}

	EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	@Transactional
	public void updateCampaign(Campaign camp) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(camp);
			em.getTransaction().commit();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void removeImage(Long campaignId, Long imageId) {
		EntityManager em = getEntityManager();
		try {
			Campaign camp = em.find(Campaign.class, campaignId);
			if (camp != null) {
				ImageStore imgstore = camp.getImageStore();
				Image image = null;

				if (imgstore.getThumbnailSmall().getId().equals(imageId)) {
					image = imgstore.getThumbnailSmall();
					imgstore.setThumbnailSmall(null);

				} else if (imgstore.getThumbnailMedium().getId().equals(imageId)) {
					image = imgstore.getThumbnailMedium();
					imgstore.setThumbnailMedium(null);

				} else if (imgstore.getThumbnailLarge().getId().equals(imageId)) {
					image = imgstore.getThumbnailLarge();
					imgstore.setThumbnailLarge(null);

				} else if (imgstore.getThumbnailXLarge().getId().equals(imageId)) {
					image = imgstore.getThumbnailXLarge();
					imgstore.setThumbnailXLarge(null);

				} else if (imgstore.getBannerSmall().getId().equals(imageId)) {
					image = imgstore.getBannerSmall();
					imgstore.setBannerSmall(null);
				} else if (imgstore.getBannerMedium().getId().equals(imageId)) {
					image = imgstore.getBannerMedium();
					imgstore.setBannerMedium(null);

				} else if (imgstore.getBannerLarge().getId().equals(imageId)) {
					image = imgstore.getBannerLarge();
					imgstore.setBannerLarge(null);

				} else if (imgstore.getBannerXLarge().getId().equals(imageId)) {
					image = imgstore.getBannerXLarge();
					imgstore.setBannerXLarge(null);

				} else
					return;

				em.getTransaction().begin();
				em.merge(camp);
				em.remove(image);
				em.getTransaction().commit();
			}
		} finally {
			if (em != null)
				em.close();
		}
	}

}
