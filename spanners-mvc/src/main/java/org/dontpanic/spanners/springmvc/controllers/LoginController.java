package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	public static final String CONTROLLER_URL = "/login";

	@RequestMapping(value = CONTROLLER_URL)
	public void login() {}
}
