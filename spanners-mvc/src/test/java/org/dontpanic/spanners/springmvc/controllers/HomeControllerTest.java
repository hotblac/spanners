package org.dontpanic.spanners.springmvc.controllers;

import org.junit.Test;

import static org.dontpanic.spanners.springmvc.controllers.HomeController.VIEW_NOT_SIGNED_IN;
import static org.junit.Assert.assertEquals;

/**
 * Test for home page
 * User: Stevie
 * Date: 05/10/13
 */
public class HomeControllerTest {

    private HomeController controller = new HomeController();

    @Test
    public void testHomePage() {

        // Call the controller
        String response = controller.index();
        assertEquals("view name", VIEW_NOT_SIGNED_IN, response);
    }
}
