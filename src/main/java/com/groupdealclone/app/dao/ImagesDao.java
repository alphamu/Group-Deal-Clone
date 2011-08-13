package com.groupdealclone.app.dao;

import com.groupdealclone.app.domain.Campaign;
import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.Images;

public interface ImagesDao {
	public Images getImages(Long id);
	
	public void saveImages(Images images);
	
	public void updateImages(Images images);
	
	public Image getImage(Campaign campaign, Long imageId);
	
	public void setImage(Image image);
	
}
