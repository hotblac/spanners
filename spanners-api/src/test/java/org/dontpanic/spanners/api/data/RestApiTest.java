package org.dontpanic.spanners.api.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.dontpanic.spanners.api.stubs.SpannerBuilder.aTestSpanner;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests on the REST API exposed by this application.
 * Created by stevie on 10/06/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class RestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpannerRepository repository;


    /**
     * By default, Spring Data REST does not expose database ids.
     * The ids are currently used by Spring MVC and must be exposed.
     */
    @Test
    public void testFindAllContainsDatabaseIds() throws Exception {

        // Setup: add a spanner to the repository
        Spanner spanner1 = aTestSpanner().named("Bertha").build();
        repository.save(spanner1);

        // Test: retrieve all spanners from repository
        mockMvc.perform(get("/spanners"))
                .andExpect(jsonPath("$._embedded.spanners[0].id", not(isEmptyString())));
    }
}
