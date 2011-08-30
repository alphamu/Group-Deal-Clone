package com.groupdealclone.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class ImageStore {
	
	Long id;
	
//	Set<Image> image;
	
	private Image thumbnailSmall;
	private Image thumbnailMedium;
	private Image thumbnailLarge;
	private Image thumbnailXLarge;

	private Image bannerSmall;
	private Image bannerMedium;
	private Image bannerLarge;
	private Image bannerXLarge;

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	public Set<Image> getImage() {
//		return image;
//	}
//
//	public void setImage(Set<Image> image) {
//		this.image = image;
//	}
	

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getThumbnailSmall() {
		return thumbnailSmall;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getThumbnailMedium() {
		return thumbnailMedium;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getThumbnailLarge() {
		return thumbnailLarge;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getThumbnailXLarge() {
		return thumbnailXLarge;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getBannerSmall() {
		return bannerSmall;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getBannerMedium() {
		return bannerMedium;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getBannerLarge() {
		return bannerLarge;
	}
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Image getBannerXLarge() {
		return bannerXLarge;
	}

	public void setThumbnailSmall(Image thumbnailSmall) {
		this.thumbnailSmall = thumbnailSmall;
	}

	public void setThumbnailMedium(Image thumbnailMedium) {
		this.thumbnailMedium = thumbnailMedium;
	}

	public void setThumbnailLarge(Image thumbnailLarge) {
		this.thumbnailLarge = thumbnailLarge;
	}

	public void setThumbnailXLarge(Image thumbnailXLarge) {
		this.thumbnailXLarge = thumbnailXLarge;
	}

	public void setBannerSmall(Image bannerSmall) {
		this.bannerSmall = bannerSmall;
	}

	public void setBannerMedium(Image bannerMedium) {
		this.bannerMedium = bannerMedium;
	}

	public void setBannerLarge(Image bannerLarge) {
		this.bannerLarge = bannerLarge;
	}

	public void setBannerXLarge(Image bannerXLarge) {
		this.bannerXLarge = bannerXLarge;
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
