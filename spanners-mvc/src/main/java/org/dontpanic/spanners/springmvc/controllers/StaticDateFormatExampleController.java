package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.forms.DateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demonstrates why a static DateFormat object is bad.
 * User: Stevie
 * Date: 07/12/13
 */
@Controller
public class StaticDateFormatExampleController {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static final String VIEW_DISPLAY = "staticDateFormatExample";
    public static final String MODEL_DATE_FORM = "dateForm";
    public static final String MODEL_PARSED_DATE = "parsedDate";

    /**
     * Display the date input form
     */
    @RequestMapping(value = "/staticDateFormatExample", method = RequestMethod.GET)
    public ModelAndView display() {
        DateForm dateForm = new DateForm();
        return new ModelAndView(VIEW_DISPLAY, MODEL_DATE_FORM, dateForm);
    }


    /**
     * Handle the date input form.
     * For simplicity, no validation is done here.
     */
    @RequestMapping(value = "/staticDateFormatExample", method = RequestMethod.POST)
    public ModelAndView submitForm(@ModelAttribute(MODEL_DATE_FORM) DateForm dateForm) throws ParseException {

        String dateString = dateForm.getDateString();
        Date parsedDate = DATE_FORMAT.parse(dateString);

        return new ModelAndView(VIEW_DISPLAY, MODEL_PARSED_DATE, parsedDate);
    }
}
