package org.dontpanic.spanners.springmvc.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dontpanic.spanners.springmvc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Retrieve user details from a REST service
 * Created by stevie on 14/07/16.
 */
@Service
public class RestUserDetailsService implements UserDetailsService {

    private RestTemplate restTemplate;

    public RestUserDetailsService(RestTemplateBuilder builder, @Value("${app.service.url.users}") String rootUri) {

        restTemplate = builder.messageConverters(halAwareMessageConverter())
                .rootUri(rootUri).build();
    }

    private HttpMessageConverter halAwareMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        return new MappingJackson2HttpMessageConverter(mapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return restTemplate.getForObject("/{0}", User.class, username);
    }
}
