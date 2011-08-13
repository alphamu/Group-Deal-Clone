package com.groupdealclone.app.service;

import com.groupdealclone.app.dao.ImagesDao;
import com.groupdealclone.app.domain.Images;

public class SimpleImagesManager implements ImagesManager {

	ImagesDao imagesDao;
	
	@Override
	public void setImages(Images images) {
		imagesDao.saveImages(images);

	}

	@Override
	public Images getImages(Long id) {
		return imagesDao.getImages(id);
	}

}
