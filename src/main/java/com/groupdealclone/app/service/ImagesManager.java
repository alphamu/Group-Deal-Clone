package com.groupdealclone.app.service;

import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

public interface ImagesManager {
	public void setImages(ImageStore images);
	public ImageStore getImages(Long id);
	public Image getImage(Long id);
}
