package org.dontpanic.spanners.springmvc.container;

import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.security.Principal;

import static org.dontpanic.spanners.springmvc.controllers.AddSpannerController.*;
import static org.dontpanic.spanners.springmvc.forms.SpannerForm.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test for validation rules on the add spanner form
 * User: Stevie
 * Date: 27/10/13
 */
public class AddSpannerValidationTest extends AbstractContainerTest {

    private static final Principal CURRENT_USER = new TestingAuthenticationToken("jsmith", "password");

    /**
     * Assert that GET request opens the page
     */
    @Test
    public void testGetPage() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL)) // Logged in user requests page
               .andExpect(status().isOk()) // HTTP 200 returned
               .andExpect(view().name(VIEW_ADD_SPANNER)) // Controller forwards to correct view
               .andExpect(model().attributeExists(MODEL_SPANNER)); // Spanner model attribute is set
    }

    /**
     * Assert that a valid form submission triggers no validation errors
     */
    @Test
    public void testValidateSuccess() throws Exception {
        mockMvc.perform(postForm("Bertha", "16"))
                .andExpect(view().name(VIEW_SUCCESS))
                .andExpect(model().hasNoErrors());
    }


    /**
     * Assert that validation error is triggered if name field is left blank
     */
    @Test
    public void testEmptyNameValidation() throws Exception {
        mockMvc.perform(postForm("", "16"))
                   .andExpect(view().name(VIEW_VALIDATION_FAIL))
                   .andExpect(model().attributeHasFieldErrors(MODEL_SPANNER, FIELD_NAME));
    }


    /**
     * Assert that validation error is triggered if size field is less than 1
     */
    @Test
    public void testMinSizeValidation() throws Exception {
        mockMvc.perform(postForm("Bertha", "0"))
                   .andExpect(view().name(VIEW_VALIDATION_FAIL))
                   .andExpect(model().attributeHasFieldErrors(MODEL_SPANNER, FIELD_SIZE));
    }


    /**
     * Assert that validation error is triggered if size field is greater than 99
     */
    @Test
    public void testMaxSizeValidation() throws Exception {
        mockMvc.perform(postForm("Bertha", "100"))
                   .andExpect(view().name(VIEW_VALIDATION_FAIL))
                   .andExpect(model().attributeHasFieldErrors(MODEL_SPANNER, FIELD_SIZE));
    }


    /**
     * Assert that validation error is triggered if size field is blank
     */
    @Test
    public void testEmptySizeValidation() throws Exception {
        mockMvc.perform(postForm("Bertha", ""))
                   .andExpect(view().name(VIEW_VALIDATION_FAIL))
                   .andExpect(model().attributeHasFieldErrors(MODEL_SPANNER, FIELD_SIZE));
    }


    /**
     * Assert that validation error is triggered if size field is not a number
     */
    @Test
    public void testNanSizeValidation() throws Exception {
        mockMvc.perform(postForm("Bertha", "NOT A NUMBER!"))
                   .andExpect(view().name(VIEW_VALIDATION_FAIL))
                   .andExpect(model().attributeHasFieldErrors(MODEL_SPANNER, FIELD_SIZE));
    }


    /**
     * Post the add spanner form
     */
    private MockHttpServletRequestBuilder postForm(String name, String size) {
   		return post(CONTROLLER_URL) // Post to URL mapped to controller
                .principal(CURRENT_USER) // User is logged in
                .param("name", name) // Spanner name
                .param("size", size); // Spanner size
   	}
}
