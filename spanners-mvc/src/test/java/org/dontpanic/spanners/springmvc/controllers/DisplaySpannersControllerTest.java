package org.dontpanic.spanners.springmvc.controllers;

import static org.dontpanic.spanners.springmvc.controllers.DisplaySpannersController.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

/**
 * Test for page that displays list of spanners
 * User: Stevie
 * Date: 05/10/13
 */
public class DisplaySpannersControllerTest {

    private DisplaySpannersController controller = new DisplaySpannersController();

    /**
     * Test that page shows list of spanners
     */
    @Test
    public void testDisplaySpanners() {

        // Call the controller
        ModelAndView response = controller.displaySpanners();
        assertNotNull("no response", response);

        // Assert that we forward to correct page
        assertEquals("view name", VIEW_DISPLAY_SPANNERS, response.getViewName());
    }

}
