package org.dontpanic.spanners.springmvc.controllers;

import static org.dontpanic.spanners.springmvc.controllers.HomeController.*;
import static org.junit.Assert.*;

import org.junit.Test;

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
