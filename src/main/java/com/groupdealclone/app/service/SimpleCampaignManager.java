package com.groupdealclone.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.CampaignDao;
import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

@Service
public class SimpleCampaignManager implements CampaignManager {

	@Autowired
	private CampaignDao	campaignDao;

	public Campaign getCampaign(Long id) {
		return campaignDao.getCampaign(id);
	}

	@Transactional
	public void saveCampaign(Campaign camp) {
		campaignDao.saveCampaign(camp);
	}

	public List<Campaign> getCampaigns() {
		return campaignDao.getCampaignList();
	}

	// @Transactional
	// public <T> void save(T t) {
	// // begin transaction
	// EntityManager em = getEntityManager();
	// em.getTransaction().begin();
	// if (!em.contains(t)) {
	// // persist object - add to entity manager
	// em.persist(t);
	// // flush em - save to DB
	// em.flush();
	// }
	// // commit transaction at all
	// em.getTransaction().commit();
	// }

	@Override
	public void updateCampaign(Campaign camp) {
		Campaign oldCamp = campaignDao.getCampaign(camp.getId());
		
		CampaignCities campCities = oldCamp.getCampaignCities();
		if (campCities != null) {
			Set<City> oldCities = campCities.getCities();
			oldCities.clear();
			oldCities.addAll(camp.getCampaignCities().getCities());
			camp.setCampaignCities(campCities);
		}
		
		ImageStore imgStore = oldCamp.getImageStore();
		if(imgStore != null) {
			List<Image> oldImgList=imgStore.getImage();
			//oldImgList.clear();
			if(oldImgList == null)
				oldImgList = new LinkedList<Image>();
			ImageStore newImageStore = camp.getImageStore();
			if(newImageStore != null){
				List<Image> newImgList = newImageStore.getImage();
				if(newImgList != null)
					oldImgList.addAll(newImgList);
			}
			camp.setImageStore(imgStore);
		}
		
		campaignDao.updateCampaign(camp);
	}

	public void removeImage(Long campaignId, Long imageId) {
		campaignDao.removeImage(campaignId, imageId);
	}

	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

}
