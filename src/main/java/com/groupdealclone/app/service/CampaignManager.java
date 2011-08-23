package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.exception.CompanyNotFoundException;

@Service
public interface CampaignManager {
	
	public Campaign getCampaign(Long id);
	
	public List<Campaign> getCampaigns();
	
	public void saveCampaign(Campaign camp) throws CompanyNotFoundException;
	
	public void updateCampaign(Campaign camp) throws CompanyNotFoundException;
	
	public void removeImage(Long campaignId, Long id);

}
