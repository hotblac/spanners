package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Controller for creating a new spanner
 * User: Stevie
 * Date: 20/10/13
 */
@Controller
public class AddSpannerController {

    public static final String VIEW_ADD_SPANNER = "modals/editSpanner";
    public static final String VIEW_SUCCESS = "redirect:/displaySpanners";

    public static final String MODEL_SPANNER = "spanner";

    @Autowired private SpannersDAO spannersDAO;

    /**
     * Display that add spanner page
     */
    @RequestMapping(value = "/addSpanner", method = RequestMethod.GET)
    public ModelAndView displayPage() {
        Spanner newSpanner = new Spanner();
        return new ModelAndView(VIEW_ADD_SPANNER, MODEL_SPANNER, newSpanner);
    }


    /**
     * Accept a form submission from add spanner page
     */
    @RequestMapping(value = "/addSpanner", method = RequestMethod.POST)
    public ModelAndView addSpanner(@ModelAttribute Spanner spanner, Principal principal) {
        spanner.setOwner(principal.getName());
        spannersDAO.create(spanner);
        return new ModelAndView(VIEW_SUCCESS);
    }
}
