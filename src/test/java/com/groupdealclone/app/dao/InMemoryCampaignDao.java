package com.groupdealclone.app.dao;

import java.util.ArrayList;
import java.util.List;

import com.groupdealclone.app.domain.Campaign;

public class InMemoryCampaignDao implements CampaignDao {

	List<Campaign>	campaigns;

	public InMemoryCampaignDao(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public InMemoryCampaignDao(Campaign campaign) {
		this.campaigns = new ArrayList<Campaign>();
		this.campaigns.add(campaign);
	}

	@Override
	public List<Campaign> getCampaignList() {
		return campaigns;
	}

	@Override
	public void saveCampaign(Campaign camp) {
		if (campaigns == null)
			campaigns = new ArrayList<Campaign>();
		campaigns.add(camp);
	}

	@Override
	public void updateCampaign(Campaign camp) {
		if (campaigns == null)
			campaigns = new ArrayList<Campaign>();
		if (campaigns.contains(camp))
			campaigns.remove(camp);
		campaigns.add(camp);
	}

	@Override
	public Campaign getCampaign(Long id) {
		for (Campaign c : campaigns) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}

	@Override
	public void removeImage(Long campaignId, Long imageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Campaign> getCampaignList(CampaignType campaignType) {
		List<Campaign> list = new ArrayList<Campaign>();
		switch (campaignType) {
		case ALL:
			return campaigns;
		case FEATURED_ONLY:
			for (Campaign c : campaigns) {
				if (c.isFeatured())
					list.add(c);
			}
		case REGULAR_ONLY:
			for (Campaign c : campaigns) {
				if (!c.isFeatured())
					list.add(c);
			}
		}
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	@Override
	public List<Campaign> getRegularCampaignList() {
		return getCampaignList(CampaignType.REGULAR_ONLY);
	}

	@Override
	public List<Campaign> getFeaturedCampaignList() {
		return getCampaignList(CampaignType.FEATURED_ONLY);
	}

}
