package com.groupdealclone.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.CampaignDao;
import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;

@Service
public class SimpleCampaignManager implements CampaignManager {
	
	@Autowired 
	private CampaignDao campaignDao;

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

//	@Transactional
//	public <T> void save(T t) {
//		// begin transaction
//		EntityManager em = getEntityManager();
//		em.getTransaction().begin();
//		if (!em.contains(t)) {
//			// persist object - add to entity manager
//			em.persist(t);
//			// flush em - save to DB
//			em.flush();
//		}
//		// commit transaction at all
//		em.getTransaction().commit();
//	}

	@Override
	public void updateCampaign(Campaign camp) {
		Campaign oldCamp = campaignDao.getCampaign(camp.getId());
		CampaignCities campCities = oldCamp.getCampaignCities();
		Set<City> oldCities = campCities.getCities();
		oldCities.clear();
		oldCities.addAll(camp.getCampaignCities().getCities());
		camp.setCampaignCities(campCities);
		campaignDao.updateCampaign(camp);
	}

	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

}
