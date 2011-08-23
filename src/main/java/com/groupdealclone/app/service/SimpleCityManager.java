package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.CityDao;
import com.groupdealclone.app.domain.City;

@Service
public class SimpleCityManager implements CityManager {
	
	@Autowired 
	private CityDao cityDao;

	public City getCity(Long id) {
		return cityDao.getCity(id);
	}

	@Transactional
	public void saveCity(City city) {
		String name = city.getName().trim().toLowerCase();
		city.setName(name);
		cityDao.saveCity(city);
		
	}

	public List<City> getCity() {
		return cityDao.getCities();
	}

	@Override
	@Transactional
	public void updateCity(City city) {
		String name = city.getName().trim().toLowerCase();
		city.setName(name);
		cityDao.updateCity(city);
	}

	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

}
