package org.dontpanic.spanners.springmvc.services;

import org.dontpanic.spanners.springmvc.config.ServiceConfig;
import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Test for Spanners REST service client
 * Created by stevie on 09/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class SpannersServiceTest {

    private static final String SERVICE_URL = "http://example.com/";
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

        Resource response = new ClassPathResource("/spannersGET.txt");

        server.expect(requestTo(SERVICE_URL)).andExpect(method(GET))
                .andRespond(withSuccess(response, APPLICATION_HAL_JSON));

        Collection<Spanner> spanners = service.findAll();
        assertThat(spanners, hasSize(2));
    }

}
