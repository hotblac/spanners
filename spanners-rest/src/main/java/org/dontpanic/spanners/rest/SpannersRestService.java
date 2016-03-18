package org.dontpanic.spanners.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by stevie on 18/03/16.
 */
@RestController
public class SpannersRestService {

    @RequestMapping("/spanners")
    public String getSpanner(@RequestParam int id) {
        return "TODO";
    }
}
