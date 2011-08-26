package com.groupdealclone.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

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
	
	@ManyToMany(fetch=FetchType.EAGER)
	public Set<City> getCities() {
		return cities;
	}
	
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	
	@Transient
	public String toString(){
		StringBuffer sb = null;
		for(City city:cities){
			if(sb != null) {
				sb.append(",");
			} else {
				sb = new StringBuffer();
			}
			sb.append(city.getId());
		}
		if(sb == null)
			return null;
		return sb.toString();
		
	}
	
    @Override
    public boolean equals(Object other)
    {
        if (other == null)  return false;
        if (other == this)  return true;
        if (other.getClass() != getClass()) return false;

        long otherId=((CampaignCities) other).getId();
        if (otherId!=id)
            return(false);

        // other id == id here
        if (id!=0)
            return(true);

        return(super.equals(other));
    }
}
