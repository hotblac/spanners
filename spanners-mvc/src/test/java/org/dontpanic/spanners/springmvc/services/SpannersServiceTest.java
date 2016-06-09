package org.dontpanic.spanners.springmvc.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Test for Spanners REST service client
 * Created by stevie on 09/06/16.
 */
public class SpannersServiceTest {

    private static final String SERVICE_URL = "http://example.com/";
    private static final MediaType APPLICATION_HAL_JSON = MediaType.valueOf("application/hal+json;charset=UTF-8");

    private RestTemplate restTemplate = restTemplate();
    private MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
    private SpannersService service = new SpannersService(SERVICE_URL);

    @Before
    public void configureService() {
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

    private RestTemplate restTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
        converter.setObjectMapper(mapper);
        return new RestTemplate(Arrays.asList(converter));
    }
}
