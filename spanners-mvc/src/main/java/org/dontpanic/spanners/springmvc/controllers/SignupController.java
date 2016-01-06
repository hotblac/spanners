package org.dontpanic.spanners.springmvc.controllers;

import javax.validation.Valid;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Controller for the signup page. This page creates new user accounts.
 * Created by stevie on 29/12/15.
 */
@Controller
public class SignupController {

    public static final String CONTROLLER_URL = "/signup";
	public static final String VIEW_SUCCESS = "redirect:/";

    /**
     * UserDetailsManager provided by Spring Security allows CRUD operations on user accounts
     */
    @Autowired private UserDetailsManager userDetailsManager;
    /**
     * PasswordEncoder implementation configured in Spring Security
     */
    @Autowired private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = CONTROLLER_URL)
	public SignupForm displayPage() {
		return new SignupForm();
	}
	
	@RequestMapping(value = CONTROLLER_URL, method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {
		if (errors.hasErrors()) {
			return null;
		}

        // Password should be stored hashed, not in plaintext
        String hashedPassword = passwordEncoder.encode(signupForm.getPassword());

        // Roles for new user
        Collection<? extends GrantedAuthority> roles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_VIEWER"),
                new SimpleGrantedAuthority("ROLE_EDITOR")
        );

        // Create the account
		UserDetails userDetails = new User(signupForm.getName(), hashedPassword, roles);
        userDetailsManager.createUser(userDetails);

		return VIEW_SUCCESS;
	}


}
