package com.groupdealclone.app.web;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.MapBindingResult;

import com.groupdealclone.app.dao.CampaignDao;
import com.groupdealclone.app.dao.CityDao;
import com.groupdealclone.app.dao.InMemoryCampaignDao;
import com.groupdealclone.app.dao.InMemoryCityDao;
import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.City;
import com.groupdealclone.app.domain.Company;
import com.groupdealclone.app.domain.Deal;
import com.groupdealclone.app.service.SimpleCampaignManager;
import com.groupdealclone.app.service.SimpleCityManager;

public class NewCampaignControllerTest {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(NewCampaignControllerTest.class);
	
	Company company;
	Campaign campaign;
	Deal deal;
	City city;
	
	NewCampaignController controller = new NewCampaignController();
	
	SimpleCityManager cityManager;
	SimpleCampaignManager campaignManager;
	
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
		deal.setPrice(new BigDecimal(DEAL_PRICE));
		deal.setDiscountPercentage(DEAL_DISCOUNT);
		deal.setMinSaleRequired(DEAL_MIN);
		deal.setId(DEAL_ID);
		
		city = new City();
		
		campaign = new Campaign();
		campaign.setCompany(company);
		
		cityManager = new SimpleCityManager();
		CityDao cityDao = new InMemoryCityDao();
		cityManager.setCityDao(cityDao);
		
		campaignManager = new SimpleCampaignManager();
		CampaignDao campaignDao = new InMemoryCampaignDao(campaign);
		campaignManager.setCampaignDao(campaignDao);
		
		controller.setCityManager(cityManager);
		
		city.setName("Karachi");
		cityManager.saveCity(city);
		controller.setCampaignManager(campaignManager);
	}
	
	@Test
	public void testShowForm() {
		HashMap<String, Object> model = new HashMap<String,Object>();
		String view = controller.showForm(model);
		assertEquals(view, "campaign/new");
	}
	
	@Test
	public void testProcessForm() {
		HashMap<String, Object> model = new HashMap<String,Object>();
		campaign.setStartDate(new java.util.Date());
		campaign.setEndDate(new java.util.Date());
		String view = controller.processForm(campaign, new MapBindingResult(new HashMap(), null), model);
		assertEquals(view, "campaign/added");
	}

}
