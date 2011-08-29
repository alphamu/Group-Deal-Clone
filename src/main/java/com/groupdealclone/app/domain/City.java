package com.groupdealclone.app.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class City implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@NotEmpty
	@Size(min = 1, max = 100)
	private String				name;
	private Long				id;

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
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

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (other.getClass() != getClass())
			return false;

		long otherId = ((City) other).getId();
		if (otherId != id)
			return (false);

		// other id == id here
		if (id != 0)
			return (true);

		return (super.equals(other));
	}

	@Override
	public int hashCode() {
		return (Long.toString(id)+name).hashCode();
	}
}
