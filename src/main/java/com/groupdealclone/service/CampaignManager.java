package com.groupdealclone.service;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Campaign;

@Service
public interface CampaignManager {
	
	public Campaign getCampaign(Long id);
	
	public void setCampaign(Campaign campaign);

}
