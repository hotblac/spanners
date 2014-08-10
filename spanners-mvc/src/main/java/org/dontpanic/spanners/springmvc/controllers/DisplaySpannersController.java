package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    /**
     * Display all spanners
     */
    @RequestMapping(value = "/displaySpanners", method = RequestMethod.GET)
    public ModelAndView displaySpanners() {

        List<Spanner> allSpanners = new ArrayList<Spanner>();

        // Load the IDs of all spanners from database
        List<Integer> spannerIds = spannersDAO.getAllSpannerIds();

        // For each spanner id...
        for (Integer spannerId : spannerIds) {
            // Load the spanner object from the database
            Spanner spanner = spannersDAO.get(spannerId);
            allSpanners.add(spanner);
        }

        return new ModelAndView(VIEW_DISPLAY_SPANNERS, MODEL_ATTRIBUTE_SPANNERS, allSpanners);
    }


    /**
     * Delete a single spanner
     */
    @RequestMapping(value = "/deleteSpanner", method = RequestMethod.GET)
    public ModelAndView deleteSpanner(@RequestParam int id) throws SpannerNotFoundException {

        // Fetch the spanner to be deleted
        Spanner spanner = spannersDAO.get(id);
        if (spanner == null) {
            // No spanner exists for given id. We can't display the page.
            throw new SpannerNotFoundException(id);
        }
        spannersDAO.delete(spanner);

        return displaySpanners();
    }

}
