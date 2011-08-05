package com.groupdealclone.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Campaign;

@Service
public interface CampaignManager {
	
	public Campaign getCampaign(Long id);
	
	public Map<Long,Campaign> getCampaigns();
	
	public void setCampaign(Campaign campaign);
	
	public void setCampaign(Long id, Campaign campaign);
	
	public void setCampaigns(Map<Long, Campaign> campaigns);
	
	

}
