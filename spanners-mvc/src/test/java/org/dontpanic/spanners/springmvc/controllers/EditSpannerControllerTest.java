package org.dontpanic.spanners.springmvc.controllers;

import static org.dontpanic.spanners.stubs.FormStubs.*;
import static org.dontpanic.spanners.stubs.SpannersStubs.*;
import static org.dontpanic.spanners.springmvc.controllers.EditSpannerController.*;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDao;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for edit spanner controller
 * User: Stevie
 * Date: 19/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class EditSpannerControllerTest {

    @Mock private SpannersDao spannersDao;
    @InjectMocks private EditSpannerController controller = new EditSpannerController();

    @Test
    public void testDisplayPage() throws Exception {

        // Stub behaviours - dao returns spanner detail
        when(spannersDao.get(SPANNER_ID)).thenReturn(SPANNER);

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
        when(spannersDao.get(SPANNER_ID)).thenReturn(null);

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
        when(spannersDao.get(SPANNER_ID)).thenReturn(SPANNER);

        // Submit the form
        SpannerForm formData = stubSpannerForm(99);
        BindingResult noErrors = new BeanPropertyBindingResult(formData, MODEL_SPANNER);
        ModelAndView response = controller.updateSpanner(formData, noErrors);
        assertNotNull("no response", response);

        // Assert that we forward to correct view
        assertEquals("view", VIEW_UPDATE_SUCCESS, response.getViewName());

        // Verify that spanner was updated
        ArgumentCaptor<Spanner> spannerArgumentCaptor = ArgumentCaptor.forClass(Spanner.class);
        verify(spannersDao).update(spannerArgumentCaptor.capture());
        Spanner updatedSpanner = spannerArgumentCaptor.getValue();
        assertSpannerEquals(formData, updatedSpanner);
    }


    @Test
    public void testValidationFail() {

        // Submit the form
        SpannerForm formData = stubSpannerForm(99);
        BindingResult validationFail = new BeanPropertyBindingResult(formData, MODEL_SPANNER);
        validationFail.addError(new ObjectError("size", "TOO_BIG"));

        ModelAndView response = controller.updateSpanner(formData, validationFail);
        assertNotNull("no response", response);

        // Assert that spanner was NOT updated
        verify(spannersDao, never()).update(any(Spanner.class));

        // Assert that we go to validation fail view
        assertEquals("view", VIEW_VALIDATION_ERRORS, response.getViewName());
    }
}
