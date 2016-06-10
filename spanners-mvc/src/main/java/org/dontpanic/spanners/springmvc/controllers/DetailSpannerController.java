package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.services.SpannersService;
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

    @Autowired private SpannersService spannersService;

    @RequestMapping(value = "/detailSpanner", method = RequestMethod.GET)
    public ModelAndView displayDetail(@RequestParam Long id) throws SpannerNotFoundException {

        // Fetch the spanner
        Spanner spanner = spannersService.findOne(id);
        if (spanner == null) {
            // No spanner exists for given id. We can't display the page.
            throw new SpannerNotFoundException(id);
        }

        return new ModelAndView(VIEW_DETAIL_SPANNER, MODEL_SPANNER, spanner);
    }
}
