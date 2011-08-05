package com.groupdealclone.app.web;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * Notice that I had to create com.groupdealcome.app.web.MyJaxb2Marshaller. 
 * That's to get around a problem that OXM has when working with Hibernate proxied objects,
 * which IMHO is a fundamental test case for a Java REST system). Essentially, Spring OXM/JAXB 
 * only checks for the @XMLRootElement only on the class itself, and not on the parent classes 
 * (like the JAX-RS implementation other Spring subsystems). The hibernate proxy process 
 * subclasses your base object (in my case Person) and doesn't add the @XMLRootElement 
 * annotation to the subclass. My workaround:
 * 
 * REST Setup tutorial http://java.dzone.com/articles/spring-30-rest-example
 */

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class MyJaxb2Marshaller extends Jaxb2Marshaller {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return super.supports(clazz) ? true : AnnotationUtils.findAnnotation(
				clazz, XmlRootElement.class) != null;
	}
}
