package com.groupdealclone.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CampaignCities {
	private Long id;
	private Set<City> cities = new HashSet<City>();
	
	@Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany
	public Set<City> getCities() {
		return cities;
	}
	
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
}
