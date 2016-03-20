package org.dontpanic.spanners.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stevie on 18/03/16.
 */
@RestController
@RequestMapping(SpannersRestService.SERVICE_ROOT)
public class SpannersRestService {

    public static final String SERVICE_ROOT = "/spanners";
    public static final String PARAM_ID = "id";

    @RequestMapping(method = GET)
    public List<String> getAll() {
        return Arrays.asList("TODO");
    }

    @RequestMapping(params = PARAM_ID, method = GET)
    public String getSpanner(@RequestParam int id) {
        return "TODO";
    }
}
