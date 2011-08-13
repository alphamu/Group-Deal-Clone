package com.groupdealclone.app.service;

import com.groupdealclone.app.domain.Images;

public interface ImagesManager {
	public void setImages(Images images);
	public Images getImages(Long id);
}
