package com.groupdealclone.service;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.groupdealclone.domain.Campaign;
import com.groupdealclone.domain.Company;
import com.groupdealclone.domain.Deal;

public class SimpleCampaignManagerTest {
	SimpleCampaignManager campaignManager;
	Company company;
	Campaign campaign;
	Deal deal;
	
	String CO_NAME="aCompany";
	Long CO_ID = new Long(1);
	
	String CAM_NAME="aCampaign";
	Long CAM_ID = new Long(2);
	Long SEVEN_DAYS = 604800000L;
	Date START_DATE = new Date(System.currentTimeMillis());
	Date END_DATE = new Date(System.currentTimeMillis()+SEVEN_DAYS);
	
	String DEAL_DESC = "aDescription";
	double DEAL_PRICE = 10.10;
	int DEAL_DISCOUNT = 10;
	int DEAL_MIN = 3;
	Long DEAL_ID = new Long(3);

	@Before
	public void setUp() throws Exception {
		company = new Company();
		company.setName(CO_NAME);
		company.setId(CO_ID);
		
		deal = new Deal();
		deal.setDescription(DEAL_DESC);
		deal.setPrice(DEAL_PRICE);
		deal.setDiscountPercentage(DEAL_DISCOUNT);
		deal.setMinSaleRequired(DEAL_MIN);
		deal.setId(DEAL_ID);
		
		campaign = new Campaign();
		campaign.setDeal(deal);
		campaign.setCompany(company);
		campaign.setName(CAM_NAME);
		campaign.setStartDate(START_DATE);
		campaign.setEndDate(END_DATE);
		campaign.setId(CAM_ID);
		
		campaignManager = new SimpleCampaignManager();
	}

	@Test
	public void testSetAndGetCampaign() {
		campaignManager.setCampaign(campaign);
		assertNotNull(campaignManager.getCampaign(new Long(2)));
		assertEquals(campaignManager.getCampaign(CAM_ID),campaign);
		Campaign tmp = campaignManager.getCampaign(CAM_ID);
		assertNotNull(tmp.getCompany());
		assertNotNull(tmp.getDeal());
		assertEquals(tmp.getCompany(), company);
		assertEquals(tmp.getDeal(), deal);
	}

}
