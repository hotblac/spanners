package org.dontpanic.spanners.springmvc.error;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stevie on 15/06/2015.
 */
public class UnauthorizedErrorController {

    public static final String CONTROLLER_URL = "403";
    public static final String VIEW_UNAUTHORIZED = "403";

    @RequestMapping(value = CONTROLLER_URL, method = RequestMethod.GET)
    public String unauthorizedError() {
        return VIEW_UNAUTHORIZED;
    }

}
