package com.groupdealclone.app.validation;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.groupdealclone.app.domain.Image;

public class CustomByteArrayToImageEditor extends PropertyEditorSupport {
	@Override
	public void setValue(Object value) {
		if (value instanceof MultipartFile) {
			Image image = new Image();
			MultipartFile multipartFile = (MultipartFile) value;
			try {
				byte[] bytes = multipartFile.getBytes();
				if (bytes.length > 0) {
					image.setImage(bytes);
					super.setValue(image);
				} else {
					super.setValue(null);
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Cannot read contents of multipart file", ex);
			}
		} else {
			super.setValue(null);
		}
	}
}
