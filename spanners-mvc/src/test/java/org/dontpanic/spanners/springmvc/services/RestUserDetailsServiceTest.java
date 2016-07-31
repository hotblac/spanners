package org.dontpanic.spanners.springmvc.services;

import org.dontpanic.spanners.springmvc.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Unit tests for the Rest user details service
 * Created by stevie on 14/07/16.
 */
@RunWith(SpringRunner.class)
@RestClientTest(RestUserDetailsService.class)
public class RestUserDetailsServiceTest {

    private static final String USERNAME = "jbloggs";
    private static final String PASSWORD = "password123";
    private static final boolean ENABLED = true;
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


    @Test
    public void testCreateUser() throws Exception {

        server.expect(requestTo("/")).andExpect(method(POST))
                .andRespond(withStatus(HttpStatus.CREATED));

        UserDetails user = new User(USERNAME, PASSWORD, ENABLED);
        service.createUser(user);

        server.verify();
    }


    @Test
    public void testUpdateUser() throws Exception {

        server.expect(requestTo("/" + USERNAME)).andExpect(method(PUT))
                .andRespond(withStatus(HttpStatus.OK));

        UserDetails user = new User(USERNAME, PASSWORD, ENABLED);
        service.updateUser(user);

        server.verify();
    }


    @Test
    public void testDeleteUser() throws Exception {

        server.expect(requestTo("/" + USERNAME)).andExpect(method(DELETE))
                .andRespond(withStatus(HttpStatus.OK));

        service.deleteUser(USERNAME);

        server.verify();
    }


    @Test
    public void testUserExists() throws Exception {

        server.expect(requestTo("/" + USERNAME)).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/user_jbloggs_GET.txt"));

        boolean userExists = service.userExists(USERNAME);
        assertTrue(userExists);
    }


    @Test
    public void testUserNotExists() throws Exception {

        server.expect(requestTo("/" + USERNAME)).andExpect(method(GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        boolean userExists = service.userExists(USERNAME);
        assertFalse(userExists);
    }


    @Test
    public void changePassword() throws Exception {

        server.expect(requestTo("/" + USERNAME)).andExpect(method(PATCH))
                .andRespond(withStatus(HttpStatus.OK));


        service.changePassword(USERNAME, PASSWORD);

        server.verify();
    }


    private ResponseCreator withHalJsonResponse(String fileName) {
        Resource jsonResponse = new ClassPathResource(fileName);
        return withSuccess(jsonResponse, APPLICATION_HAL_JSON);
    }

}