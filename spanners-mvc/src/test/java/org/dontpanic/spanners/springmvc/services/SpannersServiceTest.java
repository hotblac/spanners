package org.dontpanic.spanners.springmvc.services;

import org.dontpanic.spanners.springmvc.config.ServiceConfig;
import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.dontpanic.spanners.springmvc.stubs.SpannerAssert.assertSpanner;
import static org.dontpanic.spanners.springmvc.stubs.SpannerBuilder.aSpanner;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Test for Spanners REST service client
 * Created by stevie on 09/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class SpannersServiceTest {

    private static final String SERVICE_URL = "http://example.com/spanners";
    private static final MediaType APPLICATION_HAL_JSON = MediaType.valueOf("application/hal+json;charset=UTF-8");

    @Autowired
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private SpannersService service;

    @Before
    public void configureService() {
        server = MockRestServiceServer.createServer(restTemplate);
        service = new SpannersService(SERVICE_URL);
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);

    }

    @Test
    public void testFindAll() throws Exception {

        server.expect(requestTo(SERVICE_URL)).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/spannersGET.txt"));

        Collection<Spanner> spanners = service.findAll();
        assertThat(spanners, hasSize(2));
    }


    @Test
    public void testFindOne() throws Exception {

        server.expect(requestTo(SERVICE_URL + "/1")).andExpect(method(GET))
                .andRespond(withHalJsonResponse("/spanner1GET.txt"));

        Spanner spanner = service.findOne(1l);
        assertSpanner("Belinda", 10, "jones", spanner);
    }

    @Test
    public void testDelete() throws Exception {
        server.expect(requestTo(SERVICE_URL + "/1")).andExpect(method(DELETE))
                .andRespond(withStatus(HttpStatus.NO_CONTENT));

        Spanner susan = aSpanner().withId(1l).named("Susan").build();
        service.delete(susan);

        // Check that the server received the message
        server.verify();
    }

    private ResponseCreator withHalJsonResponse(String fileName) {
        Resource jsonResponse = new ClassPathResource(fileName);
        return withSuccess(jsonResponse, APPLICATION_HAL_JSON);
    }

}
