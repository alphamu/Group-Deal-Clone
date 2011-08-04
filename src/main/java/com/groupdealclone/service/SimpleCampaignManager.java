package com.groupdealclone.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.groupdealclone.domain.Campaign;

@Service
public class SimpleCampaignManager implements CampaignManager {

	private Map<Long, Campaign> campaigns=new HashMap<Long,Campaign>();
	
	public Campaign getCampaign(Long id) {
		return campaigns.get(id);
	}

	public void setCampaign(Campaign campaign) {
		this.campaigns.put(campaign.getId(), campaign);
	}
	public void setCampaign(Long id, Campaign campaign) {
		this.campaigns.put(id, campaign);
	}
	
	public void setCampaigns(Map<Long,Campaign> campaigns){
		this.campaigns = campaigns;
	}
	
	public Map<Long,Campaign> getCampaigns(){
		return campaigns;
	}
	

}
