package com.groupdealclone.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.MapBindingResult;

import com.groupdealclone.domain.Deal;
import com.groupdealclone.service.DealManager;
import com.groupdealclone.service.SimpleDealManager;

public class NewDealControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(NewDealControllerTest.class);

	@Test
	public void testShowForm() {
        NewDealController controller = new  NewDealController();
        Map<String,Object> model = new HashMap<String, Object>();
        String view = controller.showForm(model);
        assertNotNull(view);
        assertEquals("new-deal-input", view);
        Deal data = (Deal)model.get("deal");
        assertNotNull(data);
	}
	
	int PRODUCT_COUNT=2;
	String[] DESCRIPTION = new String[]{"Deal1","Deal2"};
	double[] PRICE = new double[]{101.1,102.2};
	int[] MIN_REQUIRED = new int[]{1,2};
	int[] DISCOUNT = new int[]{10,20};
	
	@Test
	public void testProcessForm() {
        NewDealController controller = new  NewDealController();
        DealManager dealManager = new SimpleDealManager();
        dealManager.setDeals(new LinkedHashSet<Deal>());
        controller.setDealManager(dealManager);
        Map<String,Object> model;
        Deal newDeal;
        for(int i=0; i<PRODUCT_COUNT; i++){
        	model = new HashMap<String, Object>();
        	newDeal = new Deal();
        	newDeal.setDescription(DESCRIPTION[i]);
        	newDeal.setPrice(PRICE[i]);
        	newDeal.setMinSaleRequired(MIN_REQUIRED[i]);
        	newDeal.setDiscountPercentage(DISCOUNT[i]);

            @SuppressWarnings("rawtypes")
			String view = controller.processForm(newDeal, new MapBindingResult(new HashMap(), null), model);
            
            assertNotNull(view);
            assertEquals("new-deal-success", view);	
            assertNotNull(model.get("deal"));
            logger.debug("Deal Contains {}", model.get("deal"));
        }

	}

}
