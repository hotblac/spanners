package org.dontpanic.spanners.springmvc.services;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;

import java.util.Collection;

import static org.dontpanic.spanners.springmvc.stubs.SpannerAssert.assertSpanner;
import static org.dontpanic.spanners.springmvc.stubs.SpannerBuilder.aSpanner;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Test for Spanners REST service client
 * Created by stevie on 09/06/16.
 */
@RunWith(SpringRunner.class)
@RestClientTest(SpannersService.class)
public class SpannersServiceTest {

    private static final MediaType APPLICATION_HAL_JSON = MediaType.valueOf("application/hal+json;charset=UTF-8");

    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private SpannersService service;

    @Test
    public void testFindAll() throws Exception {

        server.expect(requestTo("/")).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/spannersGET.txt"));

        Collection<Spanner> spanners = service.findAll();
        assertThat(spanners, hasSize(2));
    }


    @Test
    public void testFindOne() throws Exception {

        server.expect(requestTo("/1")).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/spanner1GET.txt"));

        Spanner spanner = service.findOne(1L);
        assertSpanner("Belinda", 10, "jones", spanner);
    }

    @Test
    public void testDelete() throws Exception {
        server.expect(requestTo("/1")).andExpect(method(DELETE))
                .andRespond(withStatus(HttpStatus.NO_CONTENT));

        Spanner susan = aSpanner().withId(1L).named("Susan").build();
        service.delete(susan);

        // Check that the server received the message
        server.verify();
    }

    @Test
    public void testCreate() throws Exception {
        server.expect(requestTo("/")).andExpect(method(POST))
                .andRespond(withStatus(HttpStatus.CREATED));

        Spanner newSpanner = aSpanner().withId(null).build();
        service.create(newSpanner);

        // Check that the server received the message
        server.verify();
    }

    @Test
    public void testUpdate() throws Exception {
        server.expect(requestTo("/1")).andExpect(method(PUT))
                .andRespond(withStatus(HttpStatus.OK));

        Spanner update = aSpanner().withId(1L).build();
        service.update(update);

        // Check that the server received the message
        server.verify();
    }

    private ResponseCreator withHalJsonResponse(String fileName) {
        Resource jsonResponse = new ClassPathResource(fileName);
        return withSuccess(jsonResponse, APPLICATION_HAL_JSON);
    }

}
