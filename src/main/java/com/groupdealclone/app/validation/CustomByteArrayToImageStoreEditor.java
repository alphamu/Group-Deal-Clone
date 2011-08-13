package com.groupdealclone.app.validation;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.groupdealclone.app.domain.Image;
import com.groupdealclone.app.domain.ImageStore;

public class CustomByteArrayToImageStoreEditor extends PropertyEditorSupport {
	@Override
	public void setValue(Object value) {
		if (value instanceof List) {
			List<Image> images = new LinkedList<Image>();
			for (Object val : ((List<?>) value)) {
				Image image = new Image();
				if (val instanceof MultipartFile) {
					MultipartFile multipartFile = (MultipartFile) val;
					try {
						byte[] bytes = multipartFile.getBytes();
						if (bytes.length > 0) {
							image.setImage(bytes);
							images.add(image);
						}
					} catch (IOException ex) {
						throw new IllegalArgumentException("Cannot read contents of multipart file", ex);
					}
				} else if (val instanceof byte[]) {
					if (((byte[]) val).length > 0) {
						image.setImage((byte[]) val);
						images.add(image);
					}
				} else {
					if (val != null) {
						byte[] bytes = val.toString().getBytes();
						if (bytes.length > 0) {
							image.setImage(bytes);
							images.add(image);
						}
					}
				}
			}
			if (images.size() > 0) {
				ImageStore img = new ImageStore();
				img.setImage(images);
				super.setValue(img);
			} else {
				super.setValue(null);
			}
		} // if instanceof List
		else if (value instanceof MultipartFile) {
			List<Image> images = new LinkedList<Image>();
			Image image = new Image();
			MultipartFile multipartFile = (MultipartFile) value;
			try {
				byte[] bytes = multipartFile.getBytes();
				if (bytes.length > 0) {
					image.setImage(bytes);
					images.add(image);
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Cannot read contents of multipart file", ex);
			}
			if (images.size() > 0) {
				ImageStore imageStore = new ImageStore();
				imageStore.setImage(images);
				super.setValue(imageStore);
			} else {
				super.setValue(null);
			}
		}
	}
}
