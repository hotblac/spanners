package org.dontpanic.spanners.springmvc.controllers.admin;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.VIEW_SWITCH_USER;
import static org.junit.Assert.*;

/**
 * Test for user switch controller.
 * Created by Stevie on 21/07/2015.
 */
public class SwitchUserControllerTest {

    private static final String NEW_USER = "jones";

    private SwitchUserController controller = new SwitchUserController();

    @Test
    public void testDisplayPage() {

        // Request the page
        ModelAndView response = controller.displayPage();
        assertNotNull("no response", response);

        // Assert that we forward to correct view
        assertEquals("view", VIEW_SWITCH_USER, response.getViewName());
    }
}