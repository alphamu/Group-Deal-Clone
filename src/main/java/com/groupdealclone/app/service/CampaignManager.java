package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.Campaign;

@Service
public interface CampaignManager {
	
	public Campaign getCampaign(Long id);
	
	public List<Campaign> getCampaigns();
	
	public void saveCampaign(Campaign camp);
	
	public void updateCampaign(Campaign camp);

}
