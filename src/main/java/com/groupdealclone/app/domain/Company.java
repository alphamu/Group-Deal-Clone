package com.groupdealclone.app.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@JsonIgnoreProperties({"address"})
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty
	@Size(min=1, max=100)
	private String name;
	
	private Set<CompanyAddress> address = new LinkedHashSet<CompanyAddress>();

	@JsonProperty("value")
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("label")
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

    @Override
    public boolean equals(Object other)
    {
        if (other == null)  return false;
        if (other == this)  return true;
        if (other.getClass() != getClass()) return false;

        long otherId=((Company) other).getId();
        if (otherId!=id)
            return(false);

        // other id == id here
        if (id!=0)
            return(true);

        return(super.equals(other));
    }
    
	@Override
	public int hashCode() {
		return (Long.toString(id)+name).hashCode();
	}
}
