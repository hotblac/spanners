package org.dontpanic.spanners.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for page that displays all spanners
 * User: Stevie
 * Date: 05/10/13
 */
@Controller
@RequestMapping
public class DisplaySpannersController {

    public static final String VIEW_DISPLAY_SPANNERS = "displaySpanners";

    @RequestMapping(value = "/displaySpanners", method = RequestMethod.GET)
    public ModelAndView displaySpanners() {
        return new ModelAndView(VIEW_DISPLAY_SPANNERS);
    }

}
