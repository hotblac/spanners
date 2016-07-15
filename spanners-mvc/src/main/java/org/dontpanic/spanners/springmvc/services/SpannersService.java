package org.dontpanic.spanners.springmvc.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Client of the Spanners-API REST service
 * Created by stevie on 08/06/16.
 */
@Service
public class SpannersService {

    private RestTemplate restTemplate;

    private String resourceUrl;
    private String itemUrl;

    @Autowired
    public SpannersService(RestTemplateBuilder builder, @Value("${app.service.url.spanners}") String resourceUrl) {
        this.resourceUrl = resourceUrl.startsWith("http") ?
                resourceUrl : "http://" + resourceUrl;
        this.itemUrl = this.resourceUrl + "/{0}";

        restTemplate = builder.messageConverters(halAwareMessageConverter()).build();
    }


    private HttpMessageConverter halAwareMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        return new MappingJackson2HttpMessageConverter(mapper);
    }


    public Collection<Spanner> findAll() {
        ResponseEntity<PagedResources<Spanner>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, null,
                                                            new ParameterizedTypeReference<PagedResources<Spanner>>(){});
        PagedResources<Spanner> pages = response.getBody();
        return pages.getContent();

    }


    public Spanner findOne(Long id) {
        return restTemplate.getForObject(itemUrl, Spanner.class, id);
    }


    public void delete(Spanner spanner) {
        restTemplate.delete(itemUrl, spanner.getId());
    }


    public void create(Spanner spanner) {
        restTemplate.postForObject(resourceUrl, spanner, Spanner.class);
    }


    public void update(Spanner spanner) {
        restTemplate.put(itemUrl, spanner, spanner.getId());
    }
}


