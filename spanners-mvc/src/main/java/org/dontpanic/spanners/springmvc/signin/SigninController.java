package org.dontpanic.spanners.springmvc.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {

	public static final String CONTROLLER_URL = "/signin";

	@RequestMapping(value = CONTROLLER_URL)
	public void signin() {}
}
