package org.dontpanic.spanners.springmvc.controllers;

import org.apache.commons.lang3.time.DateUtils;
import org.dontpanic.spanners.springmvc.forms.DateForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;

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

        // Verify view
        assertEquals("view name", VIEW_DISPLAY, response.getViewName());

        // Verify DateForm is in model map
        assertNotNull("dateForm", response.getModelMap().get(MODEL_DATE_FORM));
    }


    /**
     * Test that the form submits
     */
    @Test
    public void testSubmitForm() throws Exception {

        DateForm form = new DateForm();
        form.setDateString("01/01/2001");
        ModelAndView response = controller.submitForm(form);
        assertNotNull("no response", response);

        // Verify view
        assertEquals("view name", VIEW_DISPLAY, response.getViewName());

        // Verify parsed date is in model
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2001, Calendar.JANUARY, 1);
        Date actualDate = (Date)response.getModelMap().get(MODEL_PARSED_DATE);
        assertNotNull("parsed date", actualDate);
        assertTrue("parsed date", DateUtils.isSameDay(expectedDate.getTime(), actualDate));
    }

}
