package org.dontpanic.spanners.springmvc.controllers.admin;

import org.dontpanic.spanners.springmvc.forms.SwitchUserForm;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.VIEW_SWITCH_USER;
import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.VIEW_USER_SWITCH_SUCCESS;
import static org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController.MODEL_SWITCH_USER;
import static org.junit.Assert.*;

/**
 * Test for user switch controller.
 * Created by Stevie on 21/07/2015.
 */
public class SwitchUserControllerTest {

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
        ModelAndView response = controller.switchUser(form);
        assertNotNull("no response", response);

        // Assert that we forward to the correct view
        assertEquals("view", VIEW_USER_SWITCH_SUCCESS, response.getViewName());
    }
}