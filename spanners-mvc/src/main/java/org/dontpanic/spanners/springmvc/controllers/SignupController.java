package org.dontpanic.spanners.springmvc.controllers;

import org.apache.log4j.Logger;
import org.dontpanic.spanners.springmvc.domain.User;
import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Controller for the signup page. This page creates new user accounts.
 * Created by stevie on 29/12/15.
 */
@Controller
public class SignupController {

    private Logger log = Logger.getLogger(SignupController.class);

    public static final String CONTROLLER_URL = "/signup";
	public static final String VIEW_SUCCESS = "redirect:/";

    private static final boolean ENABLED = true;

    /**
     * UserDetailsManager provided by Spring Security allows CRUD operations on user accounts
     */
    @Autowired private UserDetailsManager userDetailsManager;

    @Autowired private PasswordEncoder passwordEncoder;

	@RequestMapping(value = CONTROLLER_URL)
	public SignupForm displayPage() {
		return new SignupForm();
	}

    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {

        if (errors.hasErrors()) {
            log.warn("Oh no! Signup failed as there are validation errors.");
            return null;
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(signupForm.getPassword());

        // Create the account
        UserDetails userDetails = new User(signupForm.getName(), hashedPassword, ENABLED);
        userDetailsManager.createUser(userDetails);

        log.info("Success! Created new user " + userDetails.getUsername());

        return VIEW_SUCCESS;
    }


}