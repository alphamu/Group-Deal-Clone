package com.groupdealclone.app.web;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class HomeControllerTest {
	
	@Test
	public void test() {
        HomeController controller = new HomeController();
        String viewName = controller.home(Locale.CANADA_FRENCH, null);
        assertEquals("home-tile", viewName);
	}

}
