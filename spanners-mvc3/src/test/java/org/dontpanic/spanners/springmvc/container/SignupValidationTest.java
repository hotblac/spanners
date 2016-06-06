package org.dontpanic.spanners.springmvc.container;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.dontpanic.spanners.springmvc.controllers.SignupController.CONTROLLER_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Test for validation rules on signup page
 * Created by Stevie on 01/01/2016.
 */
public class SignupValidationTest extends AbstractContainerTest {

    @Test
    public void testValidationSuccess() throws Exception {
        mockMvc.perform(postForm("stevie", "password"))
                .andExpect(model().hasNoErrors());
    }


    @Test
    public void testNameIsBlankValidation() throws Exception {
        mockMvc.perform(postForm("", "password"))
                .andExpect(model().attributeHasFieldErrors("signupForm", "name"));
    }


    @Test
    public void testPasswordIsBlankValidation() throws Exception {
        mockMvc.perform(postForm("stevie", ""))
                .andExpect(model().attributeHasFieldErrors("signupForm", "password"));
    }



    /**
     * Post the add spanner form
     */
    private MockHttpServletRequestBuilder postForm(String name, String size) {
        return post(CONTROLLER_URL) // Post to URL mapped to controller
                .param("name", name) // Spanner name
                .param("password", size); // Spanner size
    }
}
