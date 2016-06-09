package org.dontpanic.spanners.springmvc.services;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

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

    public Collection<Spanner> findAll() {
        ResponseEntity<PagedResources<Spanner>> response = restTemplate.exchange(serviceUrl, HttpMethod.GET, null,
                                                            new ParameterizedTypeReference<PagedResources<Spanner>>(){});
        PagedResources<Spanner> pages = response.getBody();
        return pages.getContent();

    }

    public Spanner findOne(Long id) {
        assert false: "Method not implemented";
        return null;
    }

    public void delete(Spanner spanner) {
        assert false: "Method not implemented";
    }
}


