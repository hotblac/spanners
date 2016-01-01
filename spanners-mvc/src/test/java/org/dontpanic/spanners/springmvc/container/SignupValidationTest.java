package org.dontpanic.spanners.springmvc.container;

import org.dontpanic.spanners.springmvc.config.WebMvcConfig;
import org.dontpanic.spanners.stubs.StubConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.dontpanic.spanners.springmvc.controllers.SignupController.CONTROLLER_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test for validation rules on signup page
 * Created by Stevie on 01/01/2016.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebMvcConfig.class, // MVC application context to be tested
        StubConfig.class // Stubs for any services required for application context to start
})
@RunWith(SpringJUnit4ClassRunner.class)
public class SignupValidationTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        // Set up a mock MVC tester based on the web application context
        mockMvc = webAppContextSetup(wac).build();
    }

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
