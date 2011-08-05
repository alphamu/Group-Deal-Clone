package com.groupdealclone.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@javax.persistence.Table(uniqueConstraints={@UniqueConstraint(columnNames={"name","company_id"}), @UniqueConstraint(columnNames={"id"})})
public class Campaign implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty
	@Size(min=1, max=100)
	private String name;
	
	private Date startDate;
	
	private Date endDate;
	
	@Valid
	private Deal deal;
	
	@Valid
	private Company company;
	
	private Set<City> validCities;
	
	private boolean featured;
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	public Deal getDeal() {
		return deal;
	}
	
	public void setDeal(Deal deal) {
		this.deal = deal;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public boolean isFeatured() {
		return featured;
	}
	
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	@OneToMany
	public Set<City> getValidCities() {
		return validCities;
	}

	public void setValidCities(Set<City> validCities) {
		this.validCities = validCities;
	}
	
	

}
