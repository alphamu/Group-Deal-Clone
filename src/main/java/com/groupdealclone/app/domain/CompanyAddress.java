package com.groupdealclone.app.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class CompanyAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String address;
	private String telephone;
	private String fax;
	private String email;
	private City city;
	
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
    @Override
    public boolean equals(Object other)
    {
        if (other == null)  return false;
        if (other == this)  return true;
        if (other.getClass() != getClass()) return false;

        long otherId=((CompanyAddress) other).getId();
        if (otherId!=id)
            return(false);

        // other id == id here
        if (id!=0)
            return(true);

        return(super.equals(other));
    }
	
}
