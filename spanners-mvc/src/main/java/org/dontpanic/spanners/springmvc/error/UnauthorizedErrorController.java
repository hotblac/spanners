package org.dontpanic.spanners.springmvc.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Stevie on 15/06/2015.
 */
@Controller
public class UnauthorizedErrorController {

    public static final String CONTROLLER_URL = "403";
    public static final String VIEW_UNAUTHORIZED = "403";

    @RequestMapping(CONTROLLER_URL)
    public String unauthorizedError() {
        return VIEW_UNAUTHORIZED;
    }

}
