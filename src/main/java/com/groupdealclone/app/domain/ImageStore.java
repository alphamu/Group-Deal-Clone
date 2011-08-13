package com.groupdealclone.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

@Entity
public class ImageStore {
	
	Long id;
	List<Image> image;

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}
	
    @Override
    public boolean equals(Object other)
    {
        if (other == null)  return false;
        if (other == this)  return true;
        if (other.getClass() != getClass()) return false;

        long otherId=((ImageStore) other).getId();
        if (otherId!=id)
            return(false);

        // other id == id here
        if (id!=0)
            return(true);

        return(super.equals(other));
    }
}
