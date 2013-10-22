package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
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
        SpannerForm newSpanner = new SpannerForm();
        return new ModelAndView(VIEW_ADD_SPANNER, MODEL_SPANNER, newSpanner);
    }


    /**
     * Accept a form submission from add spanner page
     */
    @RequestMapping(value = "/addSpanner", method = RequestMethod.POST)
    public ModelAndView addSpanner(@ModelAttribute SpannerForm formData, Principal principal) {

        // Create a new spanner
        Spanner spanner = new Spanner();

        // Owner is current user
        spanner.setOwner(principal.getName());

        // Name and size from form data
        spanner.setName(formData.getName());
        spanner.setSize(formData.getSize());

        // Save new spanner to database
        spannersDAO.create(spanner);

        return new ModelAndView(VIEW_SUCCESS);
    }
}
