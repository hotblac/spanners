package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.dontpanic.spanners.springmvc.rules.SystemOutResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the signup controller
 * Created by stevie on 11/08/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SignupControllerTest {

    private static final String NAME = "smith";
    private static final String PASSWORD = "password";
    private static final String HASHED_PASSWORD = "XXXhashedpasswordXXX";

    @Mock private UserDetailsManager userDetailsManager;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private SignupController controller = new SignupController();

    @Rule public SystemOutResource sysOut = new SystemOutResource();

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



    /**
     * Verify that hashed password is saved to userDetailsManager
     */
    @Test
    public void testPasswordHash() {

        given(passwordEncoder.encode(PASSWORD)).willReturn(HASHED_PASSWORD);

        SignupForm form = populateForm(NAME, PASSWORD);
        Errors noErrors = new DirectFieldBindingResult(form, "form");

        controller.signup(form, noErrors);

        // Verify that the hashed password was passed to the userDetailsManager
        verify(userDetailsManager).createUser(argThat(hasProperty("password", equalTo(HASHED_PASSWORD))));
    }


    @Test
    public void testValidationFailIsLogged() {
        SignupForm invalidForm = populateForm(null, null);
        Errors errors = new DirectFieldBindingResult(invalidForm, "form");
        errors.rejectValue("name", "Invalid name");
        errors.rejectValue("password", "Invalid password");

        controller.signup(invalidForm, errors);

        assertThat(sysOut.asString(), containsString("Oh no!"));
    }

    @Test
    public void testSuccessIsLogged() {
        SignupForm form = populateForm(NAME, PASSWORD);
        Errors noErrors = new DirectFieldBindingResult(form, "form");

        controller.signup(form, noErrors);

        assertThat(sysOut.asString(), containsString("Success!"));
    }


    private static SignupForm populateForm(String name, String password) {
        SignupForm form = new SignupForm();
        form.setName(name);
        form.setPassword(password);
        return form;
    }

}
