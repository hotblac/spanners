package org.dontpanic.spanners.springmvc.container;

import org.dontpanic.spanners.springmvc.config.WebMvcConfig;
import org.dontpanic.spanners.stubs.StubConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by stevie on 02/04/16.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebMvcConfig.class, // MVC application context to be tested
        StubConfig.class // Stubs for any services required for application context to start
})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractContainerTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        // Set up a mock MVC tester based on the web application context
        mockMvc = webAppContextSetup(wac).build();
    }
}
