package org.dontpanic.spanners.rest;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.dontpanic.spanners.rest.SpannersRestService.*;
import static org.dontpanic.spanners.stubs.SpannerBuilder.*;
/**
 * Created by stevie on 20/03/16.
 */
@SpringApplicationConfiguration(classes = {Application.class, SpannersRestServiceTest.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SpannersRestServiceTest {

    @Mock
    private SpannersDao spannersDao;
    @Autowired @InjectMocks
    private SpannersRestService restService;

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    /**
     * Make the mock DAO available to the test Spring application context
     */
    @Bean
    public SpannersDao getMockSpannersDao() {
        return spannersDao;
    }

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(wac).build();

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetAll() throws Exception {

        Spanner stubSpanner0 = aTestSpanner().withId(100).build();
        Spanner stubSpanner1 = aTestSpanner().withId(101).build();
        List<Spanner> stubSpanners = Arrays.asList(stubSpanner0, stubSpanner1);
        when(spannersDao.getAll()).thenReturn(stubSpanners);

        mockMvc.perform(get(SERVICE_ROOT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(stubSpanner0.getId())))
                .andExpect(jsonPath("$[0].name", is(stubSpanner0.getName())))
                .andExpect(jsonPath("$[0].size", is(stubSpanner0.getSize())))
                .andExpect(jsonPath("$[0].owner", is(stubSpanner0.getOwner())))
                .andExpect(jsonPath("$[1].id", is(stubSpanner1.getId())))
                .andExpect(jsonPath("$[1].name", is(stubSpanner1.getName())))
                .andExpect(jsonPath("$[1].size", is(stubSpanner1.getSize())))
                .andExpect(jsonPath("$[1].owner", is(stubSpanner1.getOwner())));
    }


    @Test
    public void testGetById() throws Exception {

        final int id = 101;
        Spanner stubSpanner = aTestSpanner()
                                .withId(id)
                                .withSize(10)
                                .named("Spanner Bob")
                                .ownedBy("Me").build();
        when(spannersDao.get(id)).thenReturn(stubSpanner);

        mockMvc.perform(get(SERVICE_ROOT).param(PARAM_ID, Integer.toString(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(stubSpanner.getId())))
                .andExpect(jsonPath("$.name", is(stubSpanner.getName())))
                .andExpect(jsonPath("$.size", is(stubSpanner.getSize())))
                .andExpect(jsonPath("$.owner", is(stubSpanner.getOwner())));
    }

}
