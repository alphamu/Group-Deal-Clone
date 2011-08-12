package com.groupdealclone.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.groupdealclone.app.dao.InMemoryCompanyDao;
import com.groupdealclone.app.domain.Company;

public class SimpleCompanyManagerTest {

	String [] companies = {"P&G", "GSK", "McDonald" };
	
	SimpleCompanyManager companyManager;
	
	@Before
	public void setUp() throws Exception {
		companyManager = new SimpleCompanyManager();
		companyManager.setCompanyDao(new InMemoryCompanyDao());
	}

	@Test
	public void test() {
		List<Company> companyList = new ArrayList<Company>();
		for(int i=0; i<companies.length; i++){
			Company company = new Company();
			company.setId(new Long(i));
			company.setName(companies[i]);
			//companyDao.saveCompany(company);
			companyManager.saveCompany(company);
			companyList.add(company);
		}
		
		assertNotNull(companyManager);
		assertNotNull(companyManager.getCompany(0L));
		assertNotNull(companyManager.getCompany(1L));
		assertNotNull(companyManager.getCompany(2L));
		
		assertEquals(companyManager.getCompany(0L), companyList.get(0));
		assertEquals(companyManager.getCompany(1L), companyList.get(1));
		assertEquals(companyManager.getCompany(2L), companyList.get(2));
		
		assertEquals(companyManager.getCompany(0L).getName(), companyList.get(0).getName());
		assertEquals(companyManager.getCompany(1L).getName(), companyList.get(1).getName());
		assertEquals(companyManager.getCompany(2L).getName(), companyList.get(2).getName());
	}

}
