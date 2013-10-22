package org.dontpanic.spanners.springmvc.controllers;

import static org.dontpanic.spanners.springmvc.controllers.DetailSpannerController.MODEL_SPANNER;
import static org.dontpanic.spanners.stubs.SpannersStubs.*;
import static org.dontpanic.spanners.springmvc.controllers.EditSpannerController.*;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for edit spanner controller
 * User: Stevie
 * Date: 19/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class EditSpannerControllerTest {

    @Mock private SpannersDAO spannersDAO;
    @InjectMocks private EditSpannerController controller = new EditSpannerController();

    @Test
    public void testDisplayPage() throws Exception {

        // Stub behaviours - dao returns spanner detail
        when(spannersDAO.get(SPANNER_ID)).thenReturn(SPANNER);

        ModelAndView response = controller.displayPage(SPANNER_ID);
        assertNotNull("no response", response);

        // Assert that we forward to correct view
        assertEquals("view", VIEW_EDIT_SPANNER, response.getViewName());

        // Assert that spanner is in model
        SpannerForm spannerFormData =  (SpannerForm)response.getModelMap().get(MODEL_SPANNER);
        assertSpannerEquals(SPANNER, spannerFormData);
    }


    @Test
    public void testSpannerNotFound() {

        // Stub behaviours = spanner not found
        when(spannersDAO.get(SPANNER_ID)).thenReturn(null);

        // Request the page for the given spanner id - this should generate exception
        try {
            controller.displayPage(SPANNER_ID);
            fail("Expected: SpannerNotFoundException");
        } catch(SpannerNotFoundException notFoundEx) {
            assertEquals("Exception spanner id", SPANNER_ID, notFoundEx.getSpannerId());
        }
    }


    @Test
    public void testUpdateSpanner() {

        // Stub behaviours - dao returns spanner detail
        when(spannersDAO.get(SPANNER_ID)).thenReturn(SPANNER);

        // Submit the form
        SpannerForm formData = stubSpannerForm(99);
        ModelAndView response = controller.updateSpanner(formData);
        assertNotNull("no response", response);

        // Assert that we forward to correct view
        assertEquals("view", VIEW_UPDATE_SUCCESS, response.getViewName());

        // Verify that spanner was updated
        ArgumentCaptor<Spanner> spannerArgumentCaptor = ArgumentCaptor.forClass(Spanner.class);
        verify(spannersDAO).update(spannerArgumentCaptor.capture());
        Spanner updatedSpanner = spannerArgumentCaptor.getValue();
        assertSpannerEquals(formData, updatedSpanner);
    }
}
