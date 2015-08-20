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
    private static final String ROLE_PREVIOUS_ADMINISTRATOR = "PREVIOUS_ADMINISTRATOR";

    @Autowired protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        // Set up a mock MVC tester based on the web application context and spring security context
        mockMvc = webAppContextSetup(wac).apply(springSecurity()).build();
    }


    /**
     * Verify that unauthenticated users can access sign in page
     */
    @Test
    public void testSigninIsAvailableToAnonymous() throws Exception {
        mockMvc.perform(get(SigninController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }


    /**
     * Verify that VIEWERs can access display spanners page
     */
    @Test
    @WithMockUser(roles=ROLE_VIEWER)
    public void testDisplaySpannersPageIsAvailableToLoggedInUser() throws Exception {
        mockMvc.perform(get(DisplaySpannersController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }


    /**
     * Verify that unauthenticated users cannot access display spanners page
     */
    @Test
    public void testDisplaySpannersPageIsNotAvailableToAnonymousUser() throws Exception {
        mockMvc.perform(get(DisplaySpannersController.CONTROLLER_URL))
                .andExpect(status().isFound()) // expected response is redirect...
                .andExpect(redirectedUrlPattern("**/signin")); // ... to login page
    }


    /**
     * Verify that ADMIN users can access the switch user page
     */
    @Test
    @WithMockUser(roles=ROLE_ADMIN)
    public void testAdminPathIsAvailableToAdminRole() throws Exception {
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }


    /**
     * Verify that VIEWERs cannot access the switch user page
     */
    @Test
    @WithMockUser(roles=ROLE_VIEWER)
    public void testAdminPathIsNotAvailableToViewer() throws Exception {
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isForbidden());
    }


    /**
     * Verify that EDITORs cannot access the switch user page
     */
    @Test
    @WithMockUser(roles=ROLE_EDITOR)
    public void testAdminPathIsNotAvailableToEditor() throws Exception{
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isForbidden());
    }


    /**
     * Verify that an ADMIN user can still access the switch user page
     * even after they've switched to a non-admin (VIEWER) user
     */
    @Test
    @WithMockUser(roles={ROLE_PREVIOUS_ADMINISTRATOR, // User logged in as ADMIN...
                         ROLE_EDITOR}) //...but is currently viewing as an EDITOR
    public void testAdminPathIsAvailableToAdminUserSwitchedToViewer() throws Exception {
        mockMvc.perform(get(SwitchUserController.CONTROLLER_URL))
                .andExpect(status().isOk());
    }
}
