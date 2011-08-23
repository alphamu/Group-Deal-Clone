package com.groupdealclone.app.dao;

import java.util.List;

import com.groupdealclone.app.domain.Campaign;

public interface CampaignDao {
	public enum CampaignType {
	    FEATURED_ONLY, REGULAR_ONLY, ALL
	}

	public List<Campaign> getCampaignList();
	public List<Campaign> getCampaignList(CampaignType campaignType);
	public List<Campaign> getRegularCampaignList();
    public List<Campaign> getFeaturedCampaignList();

    public void saveCampaign(Campaign camp);
    
    public void updateCampaign(Campaign camp);
    
    public Campaign getCampaign(Long id);
    
    public void removeImage(Long campaignId, Long imageId);
}
