package org.dontpanic.spanners.springmvc.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Unit tests for the Rest user details service
 * Created by stevie on 14/07/16.
 */
@RunWith(SpringRunner.class)
@RestClientTest(RestUserDetailsService.class)
public class RestUserDetailsServiceTest {

    private static final String USERNAME = "jbloggs";
    private static final MediaType APPLICATION_HAL_JSON = MediaType.valueOf("application/hal+json;charset=UTF-8");

    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private RestUserDetailsService service;


    @Test
    public void testLoadUserByUsername() {

        server.expect(requestTo("/" + USERNAME)).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/user_jbloggs_GET.txt"));

        UserDetails userDetails = service.loadUserByUsername(USERNAME);
        assertNotNull(userDetails);
    }


    private ResponseCreator withHalJsonResponse(String fileName) {
        Resource jsonResponse = new ClassPathResource(fileName);
        return withSuccess(jsonResponse, APPLICATION_HAL_JSON);
    }

}