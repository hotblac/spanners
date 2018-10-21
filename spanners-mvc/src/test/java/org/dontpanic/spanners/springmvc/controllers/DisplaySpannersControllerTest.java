package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.dontpanic.spanners.springmvc.services.SpannersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Supplier;

import static org.dontpanic.spanners.springmvc.controllers.DisplaySpannersController.*;
import static org.dontpanic.spanners.springmvc.stubs.SpannersStubs.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for page that displays list of spanners
 * User: Stevie
 * Date: 05/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplaySpannersControllerTest {

    @Mock private SpannersService spannersService;
    @Mock private Supplier<LocalTime> currentTime;
    @InjectMocks private DisplaySpannersController controller = new DisplaySpannersController();

    @Before
    public void initMocks() {
        when(currentTime.get()).thenReturn(LocalTime.NOON);
    }

    /**
     * Test that page shows list of spanners
     */
    @Test
    public void testDisplaySpanners() {

        // Stub behaviours - list of spanners
        when(spannersService.findAll()).thenReturn(SPANNERS);

        // Call the controller
        ModelMap model = new ModelMap();
        String view = controller.displaySpanners(model);

        // Assert that we forward to correct page
        assertEquals("view name", VIEW_DISPLAY_SPANNERS, view);

        // Assert that spanners are in model
        @SuppressWarnings("unchecked")
        List<Spanner> spannersInModel = (List<Spanner>)model.get(MODEL_ATTRIBUTE_SPANNERS);
        assertNotNull(MODEL_ATTRIBUTE_SPANNERS + " is null", spannersInModel);
        assertEquals(SPANNERS, spannersInModel);

    }

    @Test
    public void testTimeOfDayGreetingMorning() {

        // Set the current time to 9AM
        when(currentTime.get()).thenReturn(LocalTime.of(9,0));

        // Call the controller
        ModelMap model = new ModelMap();
        controller.displaySpanners(model);

        // Assert that time of day greeting is morning
        String greeting = (String)model.get(MODEL_ATTRIBUTE_GREETING);
        assertEquals(GREETING_MORNING, greeting);
    }

    @Test
    public void testTimeOfDayGreetingAfternoon() {

        // Set the current time to 2PM
        when(currentTime.get()).thenReturn(LocalTime.of(14,0));

        // Call the controller
        ModelMap model = new ModelMap();
        controller.displaySpanners(model);

        // Assert that time of day greeting is afternoon
        String greeting = (String)model.get(MODEL_ATTRIBUTE_GREETING);
        assertEquals(GREETING_AFTERNOON, greeting);
    }

    @Test
    public void testTimeOfDayGreetingEvening() {

        // Set the current time to 8PM
        when(currentTime.get()).thenReturn(LocalTime.of(20,0));

        // Call the controller
        ModelMap model = new ModelMap();
        controller.displaySpanners(model);

        // Assert that time of day greeting is evening
        String greeting = (String)model.get(MODEL_ATTRIBUTE_GREETING);
        assertEquals(GREETING_EVENING, greeting);
    }

    /**
     * Test for delete spaner action on display spanners page
     */
    @Test
    public void testDeleteSpanner() throws Exception {

        // Stub behaviours - spanner exists to be deleted
        when(spannersService.findOne(SPANNER_ID)).thenReturn(SPANNER);

        // Request a spanner is deleted
        ModelMap model = new ModelMap();
        String view = controller.deleteSpanner(SPANNER_ID, model);

        // Verify that spanner was deleted
        verify(spannersService).delete(SPANNER);

        // Assert spanners list page is shown after deletion
        assertEquals("view name", VIEW_DISPLAY_SPANNERS, view);
    }


    /**
     * Test attempt to delete a spanner that does not exist
     */
    @Test
    public void testDeleteSpannerNotFound() {

        // Stub behaviours - spanner to be deleted does not exist
        when(spannersService.findOne(SPANNER_ID)).thenReturn(null);

        // Request a spanner is deleted
        try {
               controller.deleteSpanner(SPANNER_ID, new ModelMap());
               fail("Expected: SpannerNotFoundException");
           } catch(SpannerNotFoundException notFoundEx) {
               assertEquals("Exception spanner id", SPANNER_ID, notFoundEx.getSpannerId());
           }

    }

}
