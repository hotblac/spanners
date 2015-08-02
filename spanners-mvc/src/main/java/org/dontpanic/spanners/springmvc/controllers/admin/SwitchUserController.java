package org.dontpanic.spanners.springmvc.controllers.admin;

import org.dontpanic.spanners.springmvc.forms.SwitchUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that allows super users to switch user profiles
 * Created by Stevie on 21/07/2015.
 */
@Controller
public class SwitchUserController {

    public static final String CONTROLLER_URL = "/admin/switchUser";
    public static final String VIEW_SWITCH_USER = "admin/switchUser";
    public static final String USER_SWITCH_FILTER = "redirect:/admin/impersonate";
    public static final String MODEL_SWITCH_USER = "switchUserForm";

    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.GET)
    public ModelAndView displayPage() {
        SwitchUserForm form = new SwitchUserForm();
        return new ModelAndView(VIEW_SWITCH_USER, MODEL_SWITCH_USER, form);
    }

    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.POST)
    public String switchUser(SwitchUserForm form) {
        return USER_SWITCH_FILTER + "?username=" + form.getUserName();
    }

}
