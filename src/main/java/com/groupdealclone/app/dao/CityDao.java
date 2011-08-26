package com.groupdealclone.app.dao;

import java.util.List;

import com.groupdealclone.app.domain.City;

public interface CityDao {
	public List<City> getCities();
	
	public List<City> getCities(String ... cityIn);
	
	public List<City> getCities(String cityLike);
	
	public City getCity(Long id);
	
	public void saveCity(City city);
	
	public void updateCity(City city);
}
