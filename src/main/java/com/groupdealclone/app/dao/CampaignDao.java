package com.groupdealclone.app.dao;

import java.util.List;

import com.groupdealclone.app.domain.Campaign;

public interface CampaignDao {
	
    public List<Campaign> getCampaignList();

    public void saveCampaign(Campaign camp);
    
    public void updateCampaign(Campaign camp);
    
    public Campaign getCampaign(Long id);
    
    public void removeImage(Long campaignId, Long imageId);
}
