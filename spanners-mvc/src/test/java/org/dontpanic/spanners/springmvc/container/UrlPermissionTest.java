package org.dontpanic.spanners.springmvc.container;

import org.dontpanic.spanners.springmvc.config.SecurityConfig;
import org.dontpanic.spanners.springmvc.config.WebMvcConfig;
import org.dontpanic.spanners.springmvc.controllers.DisplaySpannersController;
import org.dontpanic.spanners.springmvc.controllers.admin.SwitchUserController;
import org.dontpanic.spanners.springmvc.signin.SigninController;
import org.dontpanic.spanners.stubs.StubConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Tests to verify that urls are secured based on user roles
 * Created by Stevie on 21/07/2015.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {
                    WebMvcConfig.class, // MVC application context to be tested
                    SecurityConfig.class, // Spring security configuration containing access rules to be tested
                    StubConfig.class // Stubs for any services required for application context to start
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UrlPermissionTest {

    // Note that the WithMockUser annotation prepends 'ROLE_' to these role names
    private static final String ROLE_VIEWER = "VIEWER";
    private static final String ROLE_EDITOR = "EDITOR";
    private static final String ROLE_ADMIN = "ADMIN";

    @Autowired protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        // Set up a mock MVC tester based on the web application context and spring security context
        mockMvc = webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    public void testSigninIsAvailableToAnonymous() throws Exception {
        mockMvc.perform(get(SigninController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles=ROLE_VIEWER)
    public void testDisplaySpannersPageIsAvailableToLoggedInUser() throws Exception {
        mockMvc.perform(get(DisplaySpannersController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void testDisplaySpannersPageIsNotAvailableToAnonymousUser() throws Exception {
        mockMvc.perform(get(DisplaySpannersController.CONTROLLER_URL))
                .andExpect(status().isFound()) // expected response is redirect...
                .andExpect(redirectedUrlPattern("**/signin")); // ... to login page
    }

    @Test
    @WithMockUser(roles=ROLE_ADMIN)
    public void testAdminPathIsAvailableToAdminRole() throws Exception {
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles=ROLE_VIEWER)
    public void testAdminPathIsNotAvailableToViewer() throws Exception {
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles=ROLE_EDITOR)
    public void testAdminPathIsNotAvailableToEditor() throws Exception{
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isForbidden());
    }
}
