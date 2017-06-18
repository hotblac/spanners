package org.dontpanic.spanners.springmvc.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
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

    public SpannersService(RestTemplateBuilder builder,
                           @Value("${app.service.url.spanners}") String rootUri) {
        restTemplate = builder.messageConverters(halAwareMessageConverter())
                              .rootUri(rootUri).build();
    }


    private HttpMessageConverter halAwareMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        return new MappingJackson2HttpMessageConverter(mapper);
    }


    public Collection<Spanner> findAll() {
        ResponseEntity<PagedResources<Spanner>> response = restTemplate.exchange("/", HttpMethod.GET, null,
                                                            new ParameterizedTypeReference<PagedResources<Spanner>>(){});
        PagedResources<Spanner> pages = response.getBody();
        return pages.getContent();

    }


    public Spanner findOne(Long id) {
        return restTemplate.getForObject("/{0}", Spanner.class, id);
    }


    @PreAuthorize("hasPermission(#spanner, 'owner')")
    public void delete(Spanner spanner) {
        restTemplate.delete("/{0}", spanner.getId());
    }


    public void create(Spanner spanner) {
        restTemplate.postForObject("/", spanner, Spanner.class);
    }


    @PreAuthorize("hasPermission(#spanner, 'owner')")
    public void update(Spanner spanner) {
        restTemplate.put("/{0}", spanner, spanner.getId());
    }
}


