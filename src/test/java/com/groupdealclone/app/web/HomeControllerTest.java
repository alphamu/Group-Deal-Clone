package com.groupdealclone.app.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.groupdealclone.app.service.SimpleDealManager;

public class HomeControllerTest {
	
	@Test
	public void test() {
        HomeController controller = new HomeController();
        controller.setDealManager(new SimpleDealManager());
        ModelAndView modelAndView = controller.home(Locale.CANADA_FRENCH, null);
        assertEquals("home", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map<?,?> modelMap = (Map<?,?>) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("serverTime");
        assertNotNull(nowValue);
	}

}
