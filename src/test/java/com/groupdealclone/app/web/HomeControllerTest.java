package com.groupdealclone.app.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class HomeControllerTest {
	
	@Test
	public void test() {
        HomeController controller = new HomeController();
        ModelAndView modelAndView = controller.home(Locale.CANADA_FRENCH, null);
        assertEquals("home-tile", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        
	}

}
