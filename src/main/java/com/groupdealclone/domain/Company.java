package com.groupdealclone.domain;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Company {
	private long id;
	
	@NotEmpty
	@Size(min=1, max=100)
	private String name;
	
//	Set<Campaign> campaigns = new LinkedHashSet<Campaign>();
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@OneToMany(fetch=FetchType.EAGER)
//	public Set<Campaign> getCampaigns() {
//		return campaigns;
//	}
//
//	public void setCampaigns(Set<Campaign> campaigns) {
//		this.campaigns = campaigns;
//	}

	
}
