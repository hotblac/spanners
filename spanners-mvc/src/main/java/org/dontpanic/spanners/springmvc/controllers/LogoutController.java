package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Display logout page
 * User: Stevie
 * Date: 05/05/14
 */
@Controller
public class LogoutController {

    public static final String VIEW_LOGGED_OUT = "logout";

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return VIEW_LOGGED_OUT;
    }
}
