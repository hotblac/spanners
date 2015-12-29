package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Test for SignupController
 * Created by stevie on 29/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupControllerTest {

    private static final String NAME = "smith";
    private static final String PASSWORD = "password";

    @Mock private UserDetailsManager userDetailsManager;
    @InjectMocks private SignupController controller = new SignupController();

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

        // Verify that the account was created
        ArgumentCaptor<UserDetails> userDetailsCaptor = ArgumentCaptor.forClass(UserDetails.class);
        verify(userDetailsManager).createUser(userDetailsCaptor.capture());
        UserDetails userDetails = userDetailsCaptor.getValue();

        // Verify that the UserDetails of the new account are correct
        assertEquals(NAME, userDetails.getUsername());
        assertEquals(PASSWORD, userDetails.getPassword());
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
}
