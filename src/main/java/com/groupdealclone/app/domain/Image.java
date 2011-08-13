package com.groupdealclone.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;

@Entity
public class Image {
	private Long id;
	private byte[] image;

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length=16777215)
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (other.getClass() != getClass())
			return false;

		long otherId = ((Image) other).getId();
		if (otherId != id)
			return (false);

		// other id == id here
		if (id != 0)
			return (true);

		return (super.equals(other));
	}

}
