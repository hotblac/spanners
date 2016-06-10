package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.services.SpannersService;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.dontpanic.spanners.springmvc.controllers.AddSpannerController.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test for add spanner controller
 * User: Stevie
 * Date: 20/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class AddSpannerControllerTest {

    @Mock private SpannersService spannersService;
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
        SpannerForm formData =  new SpannerForm();
        formData.setName("Keeley");
        formData.setSize(12);

        // Stub logged in user
        Principal currentUser = new TestingAuthenticationToken("user", "pass");

        // Submit the form
        BindingResult noErrors = new BeanPropertyBindingResult(formData, MODEL_SPANNER);
        ModelAndView response = controller.addSpanner(formData, noErrors, currentUser);
        assertNotNull("no response", response);

        // Verify that spanner was created
        ArgumentCaptor<Spanner> spannerArgumentCaptor = ArgumentCaptor.forClass(Spanner.class);
        verify(spannersService).create(spannerArgumentCaptor.capture());

        // Verify that spanner has has name and size set from the form submission
        Spanner createdSpanner = spannerArgumentCaptor.getValue();
        assertEquals("name", formData.getName(), createdSpanner.getName());
        assertEquals("size", formData.getSize(), createdSpanner.getSize());

        // Verify that spanner owner is current logged in user
        assertEquals("owner", currentUser.getName(), createdSpanner.getOwner());

        // Assert that we forward to correct page
        assertEquals("view", VIEW_SUCCESS, response.getViewName());

    }


    @Test
    public void testValidationFail() {

        // Create the form submission data
        SpannerForm formData =  new SpannerForm();
        formData.setName("Keeley");
        formData.setSize(12);

        // Validation failure
        BindingResult validationFail = new BeanPropertyBindingResult(formData, MODEL_SPANNER);
        validationFail.addError(new ObjectError("size", "TOO_BIG"));

        // Stub logged in user
        Principal currentUser = new TestingAuthenticationToken("user", "pass");

        // Submit form
        ModelAndView response = controller.addSpanner(formData, validationFail, currentUser);
        assertNotNull("no response", response);

        // Assert that spanner was NOT updated
        verify(spannersService, never()).update(any(Spanner.class));

        // Assert that we go to validation fail view
        assertEquals("view", VIEW_VALIDATION_FAIL, response.getViewName());
    }

}
