package com.groupdealclone.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	@NotEmpty
	@Size(min=1, max=100)
	private String name;
	
	private Set<CompanyAddress> address = new LinkedHashSet<CompanyAddress>();

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
	
	@OneToMany
	public Set<CompanyAddress> getAddress() {
		return address;
	}

	public void setAddress(Set<CompanyAddress> address) {
		this.address = address;
	}

	
}
