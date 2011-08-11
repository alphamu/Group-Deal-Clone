package com.groupdealclone.app.dao;

import java.util.ArrayList;
import java.util.List;

import com.groupdealclone.app.domain.City;

public class InMemoryCityDao implements CityDao {

	List<City> cities;
	
	@Override
	public List<City> getCities() {
		return cities;
	}

	@Override
	public City getCity(Long id) {
		for(City c: cities){
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}

	@Override
	public void saveCity(City city) {
		if(cities == null)
			cities = new ArrayList<City>();
		cities.add(city);
	}

	@Override
	public void updateCity(City city) {
		if(cities == null)
			cities = new ArrayList<City>();
		if(cities.contains(city)) {
			cities.remove(city);
			cities.add(city);
		}
	}

}
