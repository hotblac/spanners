package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the signup page. This page creates new user accounts.
 * Created by stevie on 29/12/15.
 */
@Controller
public class SignupController {

    public static final String CONTROLLER_URL = "/signup";

	@RequestMapping(value = CONTROLLER_URL)
	public SignupForm displayPage() {
		return new SignupForm();
	}


}