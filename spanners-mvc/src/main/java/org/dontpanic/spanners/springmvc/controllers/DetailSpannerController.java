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

/**
 * Displays detail of a single spanner
 * User: Stevie
 * Date: 13/10/13
 */
@Controller
public class DetailSpannerController {

    public static final String VIEW_DETAIL_SPANNER = "detailSpanner";
    public static final String MODEL_SPANNER = "spanner";

    @Autowired private SpannersDAO spannersDAO;

    @RequestMapping(value = "/detailSpanner", method = RequestMethod.GET)
    public ModelAndView displayDetail(@RequestParam int id) throws SpannerNotFoundException {

        // Fetch the spanner
        Spanner spanner = spannersDAO.get(id);

        // XRebel demo - cause a NPE when spanner is not found
        System.out.println("Spanner retrieved: " + spanner.toString());

        return new ModelAndView(VIEW_DETAIL_SPANNER, MODEL_SPANNER, spanner);
    }
}
