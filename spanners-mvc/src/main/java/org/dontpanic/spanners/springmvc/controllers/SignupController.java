package org.dontpanic.spanners.springmvc.controllers;

import javax.validation.Valid;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

	public static final String VIEW_SUCCESS = "redirect:/";
	
	@RequestMapping(value = "signup")
	public SignupForm displayPage() {
		return new SignupForm();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {
		if (errors.hasErrors()) {
			return null;
		}

		
		return VIEW_SUCCESS;
	}

}
