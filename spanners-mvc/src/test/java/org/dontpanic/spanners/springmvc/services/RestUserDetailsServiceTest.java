package org.dontpanic.spanners.springmvc.services;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Unit tests for the Rest user details service
 * Created by stevie on 14/07/16.
 */
public class RestUserDetailsServiceTest {

    private static final String SERVICE_URL = "http://example.com/users";
    private static final String USERNAME = "jbloggs";
    private static final MediaType APPLICATION_HAL_JSON = MediaType.valueOf("application/hal+json;charset=UTF-8");

    private RestTemplate restTemplate = new RestTemplate();
    private MockRestServiceServer server;
    private RestUserDetailsService service = new RestUserDetailsService(SERVICE_URL);

    @Before
    public void configureService() {
        server = MockRestServiceServer.createServer(restTemplate);
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);

    }

    @Test
    public void testLoadUserByUsername() {

        server.expect(requestTo(SERVICE_URL + "/" + USERNAME)).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/user_jbloggs_GET.txt"));

        UserDetails userDetails = service.loadUserByUsername(USERNAME);
        assertNotNull(userDetails);
    }


    private ResponseCreator withHalJsonResponse(String fileName) {
        Resource jsonResponse = new ClassPathResource(fileName);
        return withSuccess(jsonResponse, APPLICATION_HAL_JSON);
    }

}