package org.dontpanic.spanners.springmvc.controllers;

import static org.dontpanic.spanners.springmvc.controllers.DisplaySpannersController.*;
import static org.dontpanic.spanners.stubs.SpannersStubs.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Test for page that displays list of spanners
 * User: Stevie
 * Date: 05/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplaySpannersControllerTest {

    @Mock private SpannersDAO spannersDAO;
    @InjectMocks private DisplaySpannersController controller = new DisplaySpannersController();

    /**
     * Test that page shows list of spanners
     */
    @Test
    public void testDisplaySpanners() {

        // Stub behaviours - list of spanners
        when(spannersDAO.getAll()).thenReturn(SPANNERS);

        // Call the controller
        ModelAndView response = controller.displaySpanners();
        assertNotNull("no response", response);

        // Assert that we forward to correct page
        assertEquals("view name", VIEW_DISPLAY_SPANNERS, response.getViewName());

        // Assert that spanners are in model
        @SuppressWarnings("unchecked")
        List<Spanner> spannersInModel = (List<Spanner>)response.getModelMap().get(MODEL_ATTRIBUTE_SPANNERS);
        assertNotNull(MODEL_ATTRIBUTE_SPANNERS + " is null", spannersInModel);
        assertEquals(SPANNERS, spannersInModel);

    }

}
