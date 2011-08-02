package com.groupdealclone.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DealTest {

	private Deal deal;

	@Before
	public void setUp() throws Exception {
		deal = new Deal();
	}

	@Test
	public void testSetAndGetDescription() {
		String testDescription = "aDescription";
		assertNull(deal.getDescription());
		deal.setDescription(testDescription);
		assertEquals(testDescription, deal.getDescription());
	}
	
	@Test
    public void testSetAndGetPrice() {
        double testPrice = 100.00;
        assertEquals(0, 0, 0);    
        deal.setPrice(testPrice);
        assertEquals(testPrice, deal.getPrice(), 0);
    }

	@Test
    public void testSetAndGetMinSaleRequired() {
        int minSaleRequired = 2;
        assertEquals(0, 0, 0);    
        deal.setMinSaleRequired(minSaleRequired);
        assertEquals(minSaleRequired, deal.getMinSaleRequired(), 0);
    }
	
	@Test
    public void testSetAndGetDiscountPercentage() {
        int discount = 30;
        assertEquals(0, 0, 0);    
        deal.setDiscountPercentage(discount);
        assertEquals(discount, deal.getDiscountPercentage(), 0);
    }	

}
