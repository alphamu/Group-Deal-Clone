package com.groupdealclone.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.groupdealclone.app.dao.InMemoryCityDao;
import com.groupdealclone.app.domain.City;

public class SimpleCityManagerTest {

	String [] cities = {"Karachi", "Lahore", "Islamabad" };
	
	SimpleCityManager cityManager;
	
	@Before
	public void setUp() throws Exception {
		cityManager = new SimpleCityManager();
		cityManager.setCityDao(new InMemoryCityDao());
	}

	@Test
	public void test() {
		List<City> cityList = new ArrayList<City>();
		for(int i=0; i<cities.length; i++){
			City city = new City();
			city.setId(new Long(i));
			city.setName(cities[i]);
			//cityDao.saveCity(city);
			cityManager.saveCity(city);
			cityList.add(city);
		}
		
		assertNotNull(cityManager);
		assertNotNull(cityManager.getCity(0L));
		assertNotNull(cityManager.getCity(1L));
		assertNotNull(cityManager.getCity(2L));
		
		assertEquals(cityManager.getCity(0L), cityList.get(0));
		assertEquals(cityManager.getCity(1L), cityList.get(1));
		assertEquals(cityManager.getCity(2L), cityList.get(2));
		
		assertEquals(cityManager.getCity(0L).getName(), cityList.get(0).getName());
		assertEquals(cityManager.getCity(1L).getName(), cityList.get(1).getName());
		assertEquals(cityManager.getCity(2L).getName(), cityList.get(2).getName());
	}

}
