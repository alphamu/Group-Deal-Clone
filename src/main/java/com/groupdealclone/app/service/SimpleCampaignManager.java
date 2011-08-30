package com.groupdealclone.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.CampaignDao;
import com.groupdealclone.app.dao.CampaignDao.CampaignType;
import com.groupdealclone.app.dao.CompanyDao;
import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.CampaignCities;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.domain.Company;
import com.groupdealclone.app.exception.CompanyNotFoundException;

@Service
public class SimpleCampaignManager implements CampaignManager {

	@Autowired
	private CampaignDao	campaignDao;
	@Autowired
	private CompanyDao	companyDao;

	public Campaign getCampaign(Long id) {
		return campaignDao.getCampaign(id);
	}

	@Transactional
	public void saveCampaign(Campaign camp) throws CompanyNotFoundException {
		//check if the company whose name has been input exists and if it does update campaign. else throw error.
		Company inputCo = camp.getCompany();
		String inputCoName = inputCo.getName().trim().toLowerCase();
		Company coFound = null;
		if(inputCo.getId() != null && inputCo.getId() > 0 && inputCo.getName().length() > 0) {
			coFound = inputCo;
			
		} else if(inputCoName.length() > 0) { //company id will always be null, name should be min 1 char, this check is to ensure it's not a whitespace.
			coFound = companyDao.getCompany(inputCoName);
			
		}
		if(coFound == null)
			throw new CompanyNotFoundException("Company '"+inputCo.getName()+"' not found. Add a new company first.");
		else {
			camp.setCompany(coFound);
		}
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
	public void updateCampaign(Campaign camp) throws CompanyNotFoundException {
		Campaign oldCamp = campaignDao.getCampaign(camp.getId());
		
		CampaignCities campCities = oldCamp.getCampaignCities();
		if (campCities != null) {
			Set<City> oldCities = campCities.getCities();
			oldCities.clear();
			oldCities.addAll(camp.getCampaignCities().getCities());
			camp.setCampaignCities(campCities);
		}
		
		//update the images by adding the list of new images to the old ones.
//		ImageStore imgStore = oldCamp.getImageStore();
//		if(imgStore != null) {
//			Set<Image> oldImgList=imgStore.getImage();
//			//oldImgList.clear();
//			if(oldImgList == null)
//				oldImgList = new HashSet<Image>();
//			ImageStore newImageStore = camp.getImageStore();
//			if(newImageStore != null){
//				Set<Image> newImgList = newImageStore.getImage();
//				if(newImgList != null)
//					oldImgList.addAll(newImgList);
//			}
//			camp.setImageStore(imgStore);
//		}
		
		//check if company exists. replace input company object with one from the DB
		//this should prevent people from changing the company name when editing a campaign.
		Company inputCo = camp.getCompany();
		String inputCoName = inputCo.getName().trim().toLowerCase();
		Company coFound = null;
		
		if(inputCo.getId() != null && inputCo.getId() > 0 && inputCo.getName().length() > 0) {
			coFound = inputCo;
			
		} else if(inputCoName.length() > 0) { //this should never be null (for validation)
			coFound = companyDao.getCompany(inputCoName);
			
		}		
		if(coFound == null)
			throw new CompanyNotFoundException("Company '"+inputCo.getName()+"' not found. Add a new company first.");
		else {
			camp.setCompany(coFound);
		}
		
		campaignDao.updateCampaign(camp);
	}

	public void removeImage(Long campaignId, Long imageId) {
		campaignDao.removeImage(campaignId, imageId);
	}

	public void setCampaignDao(CampaignDao campaignDao) {
		this.campaignDao = campaignDao;
	}

	@Override
	public List<Campaign> getFeaturedCampaigns() {		
		return campaignDao.getFeaturedCampaignList();
	}

	@Override
	public List<Campaign> getRegularCampaigns() {
		return campaignDao.getRegularCampaignList();
	}

	@Override
	public List<Campaign> getCampaigns(CampaignType campaignType) {
		return campaignDao.getCampaignList(campaignType);
	}

}
