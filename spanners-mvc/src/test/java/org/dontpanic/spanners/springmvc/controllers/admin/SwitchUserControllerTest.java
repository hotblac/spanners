package org.dontpanic.spanners.springmvc.controllers.admin;

import org.dontpanic.spanners.springmvc.forms.SwitchUserForm;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.VIEW_SWITCH_USER;
import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.USER_SWITCH_FILTER;
import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.MODEL_SWITCH_USER;
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

        // Assert that the form is available to the page
        SwitchUserForm form = (SwitchUserForm)response.getModelMap().get(MODEL_SWITCH_USER);
        assertNotNull("model", form);
    }


    @Test
    public void testSwitchUser() {

        // Submit the form
        SwitchUserForm form = new SwitchUserForm();
        form.setUserName(NEW_USER);
        String response = controller.switchUser(form);
        assertNotNull("no response", response);

        // Assert that we forward to the switch user filte with URL params
        String expectedForward = USER_SWITCH_FILTER + "?username=" + NEW_USER;
        assertEquals("response", expectedForward, response);
    }
}