package org.dontpanic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

/**
 * Retrieve user details from a REST service
 * Created by stevie on 14/07/16.
 */
public class RestUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    private String resourceUrl;
    private String itemUrl;

    @Autowired
    public RestUserDetailsService(@Value("${app.service.url.spanners}") String resourceUrl) {
        this.resourceUrl = resourceUrl.startsWith("http") ?
                resourceUrl : "http://" + resourceUrl;
        this.itemUrl = this.resourceUrl + "/{0}";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return restTemplate.getForObject(itemUrl, User.class, username);
    }
}
