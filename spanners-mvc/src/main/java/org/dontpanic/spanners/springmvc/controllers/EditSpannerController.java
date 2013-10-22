package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for changing properties of an existing spanner
 * User: Stevie
 * Date: 19/10/13
 */
@Controller
public class EditSpannerController {

    public static final String VIEW_EDIT_SPANNER = "modals/editSpanner";
    public static final String VIEW_UPDATE_SUCCESS = "redirect:/displaySpanners";

    public static final String MODEL_SPANNER = "spanner";

    @Autowired private SpannersDAO spannersDAO;


    /**
     * Display the edit spanner page
     */
    @RequestMapping(value = "/editSpanner", method = RequestMethod.GET)
    public ModelAndView displayPage(@RequestParam int id) throws SpannerNotFoundException {

        // Fetch the spanner
        Spanner spanner = spannersDAO.get(id);
        if (spanner == null) {
            // No spanner exists for given id. We can't display the page.
            throw new SpannerNotFoundException(id);
        }

        SpannerForm initializedForm = new SpannerForm(spanner);
        return new ModelAndView(VIEW_EDIT_SPANNER, MODEL_SPANNER, initializedForm);
    }


    /**
     * Accept form submission from edit spanner page
     */
    @RequestMapping(value = "/editSpanner", method = RequestMethod.POST)
    public ModelAndView updateSpanner(@ModelAttribute SpannerForm formData) {

        // Retrieve the existing spanner
        Spanner spanner = spannersDAO.get(formData.getId());

        // Update with given form data
        spanner.setName(formData.getName());
        spanner.setSize(formData.getSize());

        spannersDAO.update(spanner);
        return new ModelAndView(VIEW_UPDATE_SUCCESS);
    }



}
