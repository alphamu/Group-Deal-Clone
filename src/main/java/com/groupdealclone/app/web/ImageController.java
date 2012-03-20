package com.groupdealclone.app.web;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.service.ImagesManager;

@Controller
public class ImageController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	ImagesManager imagesManager;
	
	@RequestMapping(value = "images/stored/{imageId}", method = RequestMethod.GET, headers="Accept=plain/text")
	public @ResponseBody byte[] showImage(@PathVariable("imageId") long imageId, HashMap<String,Object> model){	
		Image img=imagesManager.getImage(imageId);
		return img.getImage();	
	}
	
}
