package com.groupdealclone.domain;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CampaignTest {
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
	}
	
	@Test
	public void testSetAndGetCampaign() {
		campaign.setDeal(deal);
		campaign.setCompany(company);
		campaign.setName(CAM_NAME);
		campaign.setStartDate(START_DATE);
		campaign.setEndDate(END_DATE);
		campaign.setId(CAM_ID);
		
		assertNotNull(campaign);
		assertNotNull(campaign.getDeal());
		assertNotNull(campaign.getCompany());
		
		assertEquals(campaign.getDeal(),deal);
		assertEquals(campaign.getCompany(),company);
		
		assertEquals(campaign.getName(),CAM_NAME);
		assertEquals(campaign.getStartDate(),START_DATE);
		assertEquals(campaign.getEndDate(),END_DATE);
		assertEquals(campaign.getId(),CAM_ID,0);
		
		
	}

}
