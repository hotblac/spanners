package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test for SignupController
 * Created by stevie on 29/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupControllerTest {

    private static final String NAME = "smith";
    private static final String PASSWORD = "password";
    private static final String HASHED_PASSWORD = "XXXhashedpasswordXXX";

    @Mock private UserDetailsManager userDetailsManager;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private SignupController controller = new SignupController();


    @Before
    public void onSetup() {
        // Default stub behaviour
        when(passwordEncoder.encode(anyString())).thenReturn(HASHED_PASSWORD);
    }


    @Test
    public void testSuccessForward() {

        SignupForm form = populateForm(NAME, PASSWORD);
        Errors noErrors = new DirectFieldBindingResult(form, "form");

        String response = controller.signup(form, noErrors);

        assertEquals(SignupController.VIEW_SUCCESS, response);
    }


    @Test
    public void testAccountCreation() {

        SignupForm form = populateForm(NAME, PASSWORD);
        Errors noErrors = new DirectFieldBindingResult(form, "form");

        controller.signup(form, noErrors);

        // Verify that the account was created for user
        verify(userDetailsManager).createUser(argThat(hasUserDetailsProperty("username", equalTo(NAME))));
    }


    /**
     * Verify that hashed password is saved to userDetailsManager
     */
    @Test
    public void testPasswordHash() {

        SignupForm form = populateForm(NAME, PASSWORD);
        Errors noErrors = new DirectFieldBindingResult(form, "form");

        controller.signup(form, noErrors);

        // Verify that the hashed password was passed to the userDetailsManager
        verify(userDetailsManager).createUser(argThat(hasUserDetailsProperty("password", equalTo(HASHED_PASSWORD))));
    }


    /**
     * Verified that new accounts are created with correct authorities
     */
    @Test
    public void testDefaultAuthorities() {

        SignupForm form = populateForm(NAME, PASSWORD);
        Errors noErrors = new DirectFieldBindingResult(form, "form");

        controller.signup(form, noErrors);

        ArgumentCaptor<UserDetails> userDetailsCaptor = ArgumentCaptor.forClass(UserDetails.class);
        verify(userDetailsManager).createUser(userDetailsCaptor.capture());
        UserDetails userDetails = userDetailsCaptor.getValue();

        // Verify that default roles exist
        assertThat(userDetails.getAuthorities(), containsInAnyOrder(
                hasProperty("authority", equalTo("ROLE_VIEWER")),
                hasProperty("authority", equalTo("ROLE_EDITOR"))
        ));
    }


    private static SignupForm populateForm(String name, String password) {
        SignupForm form = new SignupForm();
        form.setName(name);
        form.setPassword(password);
        return form;
    }

    private static org.hamcrest.Matcher<UserDetails> hasUserDetailsProperty(String propertyName, org.hamcrest.Matcher<?> valueMatcher) {
        return org.hamcrest.beans.HasPropertyWithValue.hasProperty(propertyName, valueMatcher);
    }
}
