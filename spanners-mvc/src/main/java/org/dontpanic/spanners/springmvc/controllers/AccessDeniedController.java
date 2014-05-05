package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Display 403 access denied page
 * User: Stevie
 * Date: 05/05/14
 */
@Controller
public class AccessDeniedController {

    public static final String VIEW_403 = "403";

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return VIEW_403;
    }
}
