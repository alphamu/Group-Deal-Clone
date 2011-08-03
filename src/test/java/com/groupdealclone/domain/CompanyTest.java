package com.groupdealclone.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CompanyTest {
	Company company;
	
	@Before
	public void setUp() throws Exception {
		company = new Company();
	}

	@Test
	public void testSetAndGetName() {
		String name = "aName";
		Long id=1L;
		company.setName(name);
		company.setId(id);
		
		assertNotNull(company);
		assertNotNull(company.getName());
		assertNotNull(company.getId());
		
		assertEquals(company.getName(),name);
		assertEquals(company.getId(),id,0);
	}

}
