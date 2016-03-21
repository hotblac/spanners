package org.dontpanic.spanners.rest;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

/**
 * Created by stevie on 18/03/16.
 */
@RestController
@RequestMapping(SpannersRestService.SERVICE_ROOT)
public class SpannersRestService {

    @Autowired private SpannersDao spannersDao;

    public static final String SERVICE_ROOT = "/spanners";
    public static final String PARAM_ID = "id";

    @RequestMapping(method = GET)
    public List<Spanner> getAll() {
        return spannersDao.getAll();
    }

    @RequestMapping(params = PARAM_ID, method = GET)
    public Spanner getSpanner(@RequestParam int id) {
        return spannersDao.get(id);
    }
}
