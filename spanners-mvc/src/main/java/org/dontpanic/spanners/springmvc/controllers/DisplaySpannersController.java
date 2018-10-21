package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.services.SpannersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Controller for page that displays all spanners
 * User: Stevie
 * Date: 05/10/13
 */
@Controller
public class DisplaySpannersController {

    static final String CONTROLLER_URL = "/displaySpanners";
    static final String VIEW_DISPLAY_SPANNERS = "displaySpanners";
    static final String MODEL_ATTRIBUTE_SPANNERS = "spanners";
    static final String MODEL_ATTRIBUTE_GREETING = "greeting";

    static final String GREETING_MORNING = "Good morning";
    static final String GREETING_AFTERNOON = "Good afternoon";
    static final String GREETING_EVENING = "Good Evening";

    private Supplier<LocalTime> currentTime = LocalTime::now;
    @Autowired private SpannersService spannersService;

    /**
     * Display all spanners
     */
    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.GET)
    public String displaySpanners(ModelMap model) {

        // Load the spanners from database
        Collection<Spanner> spanners = spannersService.findAll();

        // Use greeting appropriate to time of day
        String greeting = timeOfDayGreeting();

        model.addAttribute(MODEL_ATTRIBUTE_SPANNERS, spanners);
        model.addAttribute(MODEL_ATTRIBUTE_GREETING, greeting);
        return VIEW_DISPLAY_SPANNERS;
    }


    /**
     * Delete a single spanner
     */
    @RequestMapping(value = "/deleteSpanner", method = RequestMethod.GET)
    public String deleteSpanner(@RequestParam Long id, ModelMap model) throws SpannerNotFoundException {

        // Fetch the spanner to be deleted
        Spanner spanner = spannersService.findOne(id);
        if (spanner == null) {
            // No spanner exists for given id. We can't display the page.
            throw new SpannerNotFoundException(id);
        }
        spannersService.delete(spanner);

        return displaySpanners(model);
    }

    private String timeOfDayGreeting() {
        LocalTime now = currentTime.get();
        if (now.isBefore(LocalTime.NOON)) {
            return GREETING_MORNING;
        } else if (now.isBefore(LocalTime.of(18, 00))) {
            return GREETING_AFTERNOON;
        } else {
            return GREETING_EVENING;
        }
    }

}
