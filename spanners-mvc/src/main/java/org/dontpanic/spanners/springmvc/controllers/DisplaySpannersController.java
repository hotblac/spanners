package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller for page that displays all spanners
 * User: Stevie
 * Date: 05/10/13
 */
@Controller
public class DisplaySpannersController {

    public static final String VIEW_DISPLAY_SPANNERS = "displaySpanners";
    public static final String MODEL_ATTRIBUTE_SPANNERS = "spanners";

    @Autowired private SpannersDAO spannersDAO;

    @RequestMapping(value = "/displaySpanners", method = RequestMethod.GET)
    public ModelAndView displaySpanners() {

        // Load the spanners from database
        List<Spanner> spanners = spannersDAO.getAll();

        return new ModelAndView(VIEW_DISPLAY_SPANNERS, MODEL_ATTRIBUTE_SPANNERS, spanners);
    }

}
