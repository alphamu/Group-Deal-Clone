package com.groupdealclone.service;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Campaign;

@Service
public class SimpleCampaignManager implements CampaignManager {

	private Campaign campaign;
	
	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign=campaign;
	}


}
