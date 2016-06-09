package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.services.SpannersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

/**
 * Controller for page that displays all spanners
 * User: Stevie
 * Date: 05/10/13
 */
@Controller
public class DisplaySpannersController {

    public static final String CONTROLLER_URL = "/displaySpanners";
    public static final String VIEW_DISPLAY_SPANNERS = "displaySpanners";
    public static final String MODEL_ATTRIBUTE_SPANNERS = "spanners";

    @Autowired private SpannersService spannersService;

    /**
     * Display all spanners
     */
    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.GET)
    public ModelAndView displaySpanners() {

        // Load the spanners from database
        Collection<Spanner> spanners = spannersService.findAll();

        return new ModelAndView(VIEW_DISPLAY_SPANNERS, MODEL_ATTRIBUTE_SPANNERS, spanners);
    }


    /**
     * Delete a single spanner
     */
    @RequestMapping(value = "/deleteSpanner", method = RequestMethod.GET)
    public ModelAndView deleteSpanner(@RequestParam Long id) throws SpannerNotFoundException {

        // Fetch the spanner to be deleted
        Spanner spanner = spannersService.findOne(id);
        if (spanner == null) {
            // No spanner exists for given id. We can't display the page.
            throw new SpannerNotFoundException(id);
        }
        spannersService.delete(spanner);

        return displaySpanners();
    }

}
