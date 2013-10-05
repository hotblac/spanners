package org.dontpanic.spanners.springmvc.controllers;

import static org.dontpanic.spanners.springmvc.controllers.DisplaySpannersController.*;
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

import java.util.ArrayList;
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
        List<Spanner> spannersFromDb = stubSpanners();
        when(spannersDAO.getAll()).thenReturn(spannersFromDb);

        // Call the controller
        ModelAndView response = controller.displaySpanners();
        assertNotNull("no response", response);

        // Assert that we forward to correct page
        assertEquals("view name", VIEW_DISPLAY_SPANNERS, response.getViewName());

        // Assert that spanners are in model
        @SuppressWarnings("unchecked")
        List<Spanner> spannersInModel = (List<Spanner>)response.getModelMap().get(MODEL_ATTRIBUTE_SPANNERS);
        assertNotNull(MODEL_ATTRIBUTE_SPANNERS + " is null", spannersInModel);
        assertEquals(spannersFromDb, spannersInModel);

    }


    private List<Spanner> stubSpanners() {
        List<Spanner> spanners = new ArrayList<Spanner>();
        for (int i=1; i<=5; i++) {
            spanners.add(stubSpanner(i));
        }
        return spanners;
    }

    private Spanner stubSpanner(int id) {
        Spanner spanner = new Spanner();
        spanner.setId(id);
        spanner.setName("Spanner number " + id);
        spanner.setOwner("Owner");
        spanner.setSize(10 + id);
        return spanner;
    }

}
