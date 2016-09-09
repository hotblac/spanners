package org.dontpanic.spanners.springmvc.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dontpanic.spanners.springmvc.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Retrieve user details from a REST service
 * Created by stevie on 14/07/16.
 */
@Service
public class RestUserDetailsService implements UserDetailsManager {

    private RestTemplate restTemplate;

    public RestUserDetailsService(RestTemplateBuilder builder,
                                  @Value("${app.service.url.users}") String rootUri) {

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

    @Override
    public void createUser(UserDetails userDetails) {
        restTemplate.postForLocation("/", userDetails);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        restTemplate.put("/{0}", userDetails, userDetails.getUsername());
    }

    @Override
    public void deleteUser(String username) {
        restTemplate.delete("/{0}", username);
    }

    @Override
    public void changePassword(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpEntity<User> requestEntity = new HttpEntity<>(user);

        restTemplate.exchange("/{0}", HttpMethod.PATCH, requestEntity, Void.class, username);
    }

    @Override
    public boolean userExists(String username) {

        try {
            ResponseEntity<User> responseEntity = restTemplate.getForEntity("/{0}", User.class, username);
            return HttpStatus.OK.equals(responseEntity.getStatusCode());
        } catch (HttpClientErrorException e) {
            // On HTTP 404 NOT FOUND or any HTTP error, assume user does not exist
            return false;
        }
    }
}
