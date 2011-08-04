package com.groupdealclone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GenerationType;

@Entity
public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Long id;
	
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

}
