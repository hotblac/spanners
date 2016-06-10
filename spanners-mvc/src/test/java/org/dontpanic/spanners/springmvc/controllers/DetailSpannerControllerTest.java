package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.services.SpannersService;
import org.dontpanic.spanners.springmvc.exception.SpannerNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.dontpanic.spanners.springmvc.controllers.DetailSpannerController.MODEL_SPANNER;
import static org.dontpanic.spanners.springmvc.controllers.DetailSpannerController.VIEW_DETAIL_SPANNER;
import static org.dontpanic.spanners.springmvc.stubs.SpannerAssert.assertSpannerEquals;
import static org.dontpanic.spanners.springmvc.stubs.SpannersStubs.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Test for spanner detail page
 * User: Stevie
 * Date: 13/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailSpannerControllerTest {

    @Mock private SpannersService spannersService;
    @InjectMocks private DetailSpannerController controller = new DetailSpannerController();

    @Test
    public void testDisplayPage() throws Exception {

        // Stub behaviours - dao returns spanner detail
        when(spannersService.findOne(SPANNER_ID)).thenReturn(SPANNER);

        // Request the page for the given spanner id
        ModelAndView response = controller.displayDetail(SPANNER_ID);
        assertNotNull("no response", response);

        // Assert that we forward to correct view
        assertEquals(VIEW_DETAIL_SPANNER, response.getViewName());

        // Assert that spanner detail is available to view
        Spanner spannerInModel =  (Spanner)response.getModelMap().get(MODEL_SPANNER);
        assertSpannerEquals(SPANNER, spannerInModel);
    }


    @Test
    public void testSpannerNotFound() throws Exception {

        // Stub behaviours = spanner not found
        when(spannersService.findOne(SPANNER_ID)).thenReturn(null);

        // Request the page for the given spanner id - this should generate exception
        try {
            controller.displayDetail(SPANNER_ID);
            fail("Expected: SpannerNotFoundException");
        } catch(SpannerNotFoundException notFoundEx) {
            assertEquals("Exception spanner id", SPANNER_ID, notFoundEx.getSpannerId());
        }
    }

}
