package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.City;

@Service
public interface CityManager {
	
	public City getCity(Long id);
	
	public List<City> getCity();
	
	public void saveCity(City camp);
	
	public void updateCity(City camp);


}
