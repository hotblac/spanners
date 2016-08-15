package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the signup controller
 * Created by stevie on 11/08/16.
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

        // Verify that the account was created for user
        verify(userDetailsManager).createUser(argThat(hasProperty("username", equalTo(NAME))));
    }


    private static SignupForm populateForm(String name, String password) {
        SignupForm form = new SignupForm();
        form.setName(name);
        form.setPassword(password);
        return form;
    }

}
