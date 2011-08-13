package com.groupdealclone.app.dao;

import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

public interface ImagesDao {
	public ImageStore getImages(Long id);
	
	public void saveImages(ImageStore images);
	
	public void updateImages(ImageStore images);
	
	public Image getImage(Long imageId);
	
	public void setImage(Image image);
	
}
