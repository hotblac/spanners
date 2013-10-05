package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    public static final String VIEW_NOT_SIGNED_IN = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return VIEW_NOT_SIGNED_IN;
	}
}
