package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.dontpanic.spanners.springmvc.controllers.AddSpannerController.*;
import static org.dontpanic.spanners.stubs.SpannersStubs.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test for add spanner controller
 * User: Stevie
 * Date: 20/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class AddSpannerControllerTest {

    @Mock private SpannersDAO spannersDAO;
    @InjectMocks private AddSpannerController controller = new AddSpannerController();


    @Test
    public void testPageDisplay() {

        // Request the page
        ModelAndView response = controller.displayPage();
        assertNotNull("no response", response);

        // Assert that correct page is displayed
        assertEquals("view", VIEW_ADD_SPANNER, response.getViewName());
    }


    @Test
    public void testAddSpanner() {

        // Create the form submission data
        Spanner newSpanner =  stubSpanner(0);

        // Submit the form
        ModelAndView response = controller.addSpanner(newSpanner);
        assertNotNull("no response", response);

        // Verify that spanner was created
        ArgumentCaptor<Spanner> spannerArgumentCaptor = ArgumentCaptor.forClass(Spanner.class);
        verify(spannersDAO).create(spannerArgumentCaptor.capture());
        assertSpannerEquals(newSpanner, spannerArgumentCaptor.getValue());

        // Assert that we forward to correct page
        assertEquals("view", VIEW_SUCCESS, response.getViewName());

    }

}
