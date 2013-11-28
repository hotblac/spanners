package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static final String MODEL_ATTRIBUTE_DATE = "formattedDate";

    @Autowired private SpannersDAO spannersDAO;
    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    /**
     * Display all spanners
     */
    @RequestMapping(value = "/displaySpanners", method = RequestMethod.GET)
    public ModelAndView displaySpanners() {

        ModelMap model = new ModelMap();

        // Load the spanners from database
        List<Spanner> spanners = spannersDAO.getAll();
        model.put(MODEL_ATTRIBUTE_SPANNERS, spanners);

        // Format today's date
        Date today = new Date();
        String formattedDate = dateFormat.format(today);
        model.put(MODEL_ATTRIBUTE_DATE, formattedDate);

        return new ModelAndView(VIEW_DISPLAY_SPANNERS, model);
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
