package org.dontpanic.spanners.users.data;

import org.dontpanic.spanners.users.Application;
import org.dontpanic.spanners.users.config.RestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Tests on the REST API exposed by this application.
 * Created by stevie on 27/08/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, RestConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class RestApiTest {

    private static final String USERNAME = "jbloggs";
    private static final String PASSWORD = "password123";
    private static final Boolean ENABLED = true;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;


    /**
     * By default, Spring Data REST does not expose database ids.
     * The user name is used as the id and must be exposed by user lookup
     */
    @Test
    public void testFindOneContainsUsername() throws Exception {

        // Setup: add a user to the repository
        User savedUser = new User(USERNAME, PASSWORD, ENABLED);
        repository.save(savedUser);

        // Test: user retrieved by rest API contains username
        mockMvc.perform(get("/users/{0}", USERNAME))
                .andExpect(jsonPath("$.username", equalTo(USERNAME)));


    }
}
