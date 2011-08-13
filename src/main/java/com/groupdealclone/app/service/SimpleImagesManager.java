package com.groupdealclone.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupdealclone.app.dao.ImagesDao;
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

@Service
public class SimpleImagesManager implements ImagesManager {

	@Autowired
	ImagesDao imagesDao;
	
	@Override
	public void setImages(ImageStore images) {
		imagesDao.saveImages(images);

	}

	@Override
	public ImageStore getImages(Long id) {
		return imagesDao.getImages(id);
	}

	@Override
	public Image getImage(Long id) {
		return imagesDao.getImage(id);
	}

}
