package com.groupdealclone.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Deal implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotEmpty
	@Size(min=1, max=250)
	private String description;
	
	@Min(value=1)
    private double price;
	
	@Min(value=1)
    private int minSaleRequired;
    
    @Max(value=100)
    @Min(value=5)
    private int discountPercentage;
    
    public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public int getMinSaleRequired() {
		return minSaleRequired;
	}

	public void setMinSaleRequired(int minSaleRequired) {
		this.minSaleRequired = minSaleRequired;
	}

	public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setId(Long id){
    	this.id=id;
    }
    
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
    
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + "; ");
        buffer.append("Price: " + price + "; ");
        buffer.append("Min Sale Required: " + minSaleRequired + "; ");
        buffer.append("Discount %: " + discountPercentage + "; ");
        return buffer.toString();
    }
}
