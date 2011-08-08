package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.CampaignDao;
import com.groupdealclone.app.domain.Campaign;

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

	public void setCampaign(Long id, Campaign campaign) {

	}

	public void setCampaigns(List<Campaign> campaigns) {
		
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
		campaignDao.updateCampaign(camp);
	}

	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

}
