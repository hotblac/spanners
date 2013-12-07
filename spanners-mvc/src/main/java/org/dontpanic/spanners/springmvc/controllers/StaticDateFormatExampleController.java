package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Demonstrates why a static DateFormat object is bad.
 * User: Stevie
 * Date: 07/12/13
 */
@Controller
public class StaticDateFormatExampleController {

    public static final String VIEW_DISPLAY = "staticDateFormatExample";

    @RequestMapping(value = "/staticDateFormatExample", method = RequestMethod.GET)
    public ModelAndView display() {
        return new ModelAndView(VIEW_DISPLAY);
    }
}
