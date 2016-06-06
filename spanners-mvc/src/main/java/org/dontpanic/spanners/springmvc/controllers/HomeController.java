package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	public static final String CONTROLLER_URL = "/";
    public static final String VIEW_NOT_SIGNED_IN = "index";

    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.GET)
	public String index() {
		return VIEW_NOT_SIGNED_IN;
	}
}
