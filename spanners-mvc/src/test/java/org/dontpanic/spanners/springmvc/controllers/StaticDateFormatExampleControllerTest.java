package org.dontpanic.spanners.springmvc.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.dontpanic.spanners.springmvc.controllers.StaticDateFormatExampleController.*;
import static org.junit.Assert.*;

/**
 * Test for StaticDateFormatExample page.
 * This test does not demonstrate the thread safety issue.
 * It just demonstrates that the page works.
 * User: Stevie
 * Date: 07/12/13
 */
@RunWith(MockitoJUnitRunner.class)
public class StaticDateFormatExampleControllerTest {

    private StaticDateFormatExampleController controller = new StaticDateFormatExampleController();

    /**
     * Test that page displays
     */
    @Test
    public void testDisplay() {
        ModelAndView response = controller.display();
        assertNotNull("no response", response);
        assertEquals("view name", VIEW_DISPLAY, response.getViewName());
    }

}
