package com.groupdealclone.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.groupdealclone.domain.Deal;
import com.groupdealclone.service.SimpleDealManager;

public class SimpleDealManagerTest {

	private SimpleDealManager dealManager;
	
    private static int PRODUCT_COUNT = 2;
    
    private static double CHAIR_PRICE = 20.50;
    private static String CHAIR_DESCRIPTION = "Chair";
    private static int CHAIR_MINSALE = 10;
    private static int CHAIR_DISCOUNT = 20;
    
    private static String TABLE_DESCRIPTION = "Table";
    private static double TABLE_PRICE = 150.10;  
    private static int TABLE_MINSALE = 3;
    private static int TABLE_DISCOUNT = 10;
	
	@Before
	public void setUp(){
		dealManager = new SimpleDealManager();
		List<Deal> deals = new ArrayList<Deal>();
		Deal deal = new Deal();
		deal.setDescription(CHAIR_DESCRIPTION);
		deal.setPrice(CHAIR_PRICE);
		deal.setMinSaleRequired(CHAIR_MINSALE);
		deal.setDiscountPercentage(CHAIR_DISCOUNT);
		deals.add(deal);
		
		deal = new Deal();
		deal.setDescription(TABLE_DESCRIPTION);
		deal.setPrice(TABLE_PRICE);
		deal.setMinSaleRequired(TABLE_MINSALE);
		deal.setDiscountPercentage(TABLE_DISCOUNT);
		deals.add(deal);
		
		dealManager.setDeals(deals);
	}
	
	@Test
	public void testGetProducts() {
		List<Deal> deals = dealManager.getDeals();
		assertNotNull(deals);   
		assertEquals(PRODUCT_COUNT, dealManager.getDeals().size());
		
        Deal deal = deals.get(0);
        assertEquals(CHAIR_DESCRIPTION, deal.getDescription());
        assertEquals(CHAIR_PRICE, deal.getPrice(),0);
        assertEquals(CHAIR_MINSALE, deal.getMinSaleRequired());
        assertEquals(CHAIR_DISCOUNT, deal.getDiscountPercentage());
        
        deal = deals.get(1);
        assertEquals(TABLE_DESCRIPTION, deal.getDescription());
        assertEquals(TABLE_PRICE, deal.getPrice(),0);    
        assertEquals(TABLE_MINSALE, deal.getMinSaleRequired());    
        assertEquals(TABLE_DISCOUNT, deal.getDiscountPercentage());    
		
	}

}
