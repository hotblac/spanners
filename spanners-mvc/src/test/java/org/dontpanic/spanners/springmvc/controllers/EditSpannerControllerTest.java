package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.services.SpannersService;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.dontpanic.spanners.springmvc.controllers.EditSpannerController.*;
import static org.dontpanic.spanners.springmvc.stubs.FormAssert.assertSpannerEquals;
import static org.dontpanic.spanners.springmvc.stubs.SpannersStubs.SPANNER;
import static org.dontpanic.spanners.springmvc.stubs.SpannersStubs.SPANNER_ID;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Test for edit spanner controller
 * User: Stevie
 * Date: 19/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class EditSpannerControllerTest {

    @Mock private SpannersService spannersService;
    @Mock private ApplicationEventPublisher eventPublisher;
    @InjectMocks private EditSpannerController controller = new EditSpannerController();

    @Test
    public void testDisplayPage() throws Exception {

        // Stub behaviours - dao returns spanner detail
        when(spannersService.findOne(SPANNER_ID)).thenReturn(SPANNER);

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
        when(spannersService.findOne(SPANNER_ID)).thenReturn(null);

        // Request the page for the given spanner id - this should generate exception
        try {
            controller.displayPage(SPANNER_ID);
            fail("Expected: SpannerNotFoundException");
        } catch(SpannerNotFoundException notFoundEx) {
            assertEquals("Exception spanner id", SPANNER_ID, notFoundEx.getSpannerId());
        }
    }


    @Test
    public void testUpdateSpannerResponse() {

        // Stub behaviours - dao returns spanner detail
        when(spannersService.findOne(SPANNER_ID)).thenReturn(SPANNER);

        ModelAndView response = formSubmission().submit();
        assertNotNull("no response", response);

        // Assert that we forward to correct view
        assertEquals("view", VIEW_UPDATE_SUCCESS, response.getViewName());
    }


    @Test
    public void testSpannerUpdated() {

        // Stub behaviours - dao returns spanner detail
        when(spannersService.findOne(SPANNER_ID)).thenReturn(SPANNER);

        SpannerForm formData = stubSpannerForm(99l);
        formSubmission().withFormData(formData).submit();

        // Verify that spanner was updated
        ArgumentCaptor<Spanner> spannerArgumentCaptor = ArgumentCaptor.forClass(Spanner.class);
        verify(spannersService).update(spannerArgumentCaptor.capture());
        Spanner updatedSpanner = spannerArgumentCaptor.getValue();
        assertSpannerEquals(formData, updatedSpanner);
    }


    @Test
    public void testValidationFail() {

        ModelAndView response = formSubmission()
                                    .withError(new ObjectError("size", "TOO_BIG"))
                                    .submit();
        assertNotNull("no response", response);

        // Assert that spanner was NOT updated
        verify(spannersService, never()).update(any(Spanner.class));

        // Assert that we go to validation fail view
        assertEquals("view", VIEW_VALIDATION_ERRORS, response.getViewName());
    }


    private FormBuilder formSubmission() {
        return new FormBuilder();
    }


    private class FormBuilder {

        private SpannerForm formData = stubSpannerForm(99l);
        private List<ObjectError> errors = new ArrayList<>();

        public FormBuilder withFormData(SpannerForm form) {
            this.formData = form;
            return this;
        }

        public FormBuilder withError(ObjectError error) {
            errors.add(error);
            return this;
        }

        public ModelAndView submit() {
            BindingResult bindingResult = new BeanPropertyBindingResult(formData, MODEL_SPANNER);
            errors.forEach(bindingResult::addError);
            return controller.updateSpanner(formData, bindingResult);
        }
    }

    public static SpannerForm stubSpannerForm(Long id) {
        SpannerForm formData = new SpannerForm();
        formData.setId(id);
        formData.setName("Spanner number " + id);
        formData.setSize(10 + id.intValue());
        return formData;
    }
}
