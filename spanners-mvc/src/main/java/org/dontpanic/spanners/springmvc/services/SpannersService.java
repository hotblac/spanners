package org.dontpanic.spanners.springmvc.services;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Client of the Spanners-API REST service
 * Created by stevie on 08/06/16.
 */
@Service
public class SpannersService {

    @Autowired
    private RestTemplate restTemplate;

    private String serviceUrl;

    @Autowired
    public SpannersService(@Value("${app.service.url.spanners}") String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
                serviceUrl : "http://" + serviceUrl;
    }

    public List<Spanner> findAll() {
        ResponseEntity<Spanner[]> response = restTemplate.getForEntity(serviceUrl, Spanner[].class);
        Spanner[] spanners = response.getBody();
        return Arrays.asList(spanners);
    }

    public Spanner findOne(Long id) {
        assert false: "Method not implemented";
        return null;
    }

    public void delete(Spanner spanner) {
        assert false: "Method not implemented";
    }
}


