package org.dontpanic.spanners.springmvc.controllers;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.dontpanic.spanners.springmvc.controllers.AddSpannerController.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test for add spanner controller
 * User: Stevie
 * Date: 20/10/13
 */
@RunWith(MockitoJUnitRunner.class)
public class AddSpannerControllerTest {

    @Mock private SpannersDAO spannersDAO;
    @InjectMocks private AddSpannerController controller = new AddSpannerController();


    @Test
    public void testPageDisplay() {

        // Request the page
        ModelAndView response = controller.displayPage();
        assertNotNull("no response", response);

        // Assert that correct page is displayed
        assertEquals("view", VIEW_ADD_SPANNER, response.getViewName());
    }


    @Test
    public void testAddSpanner() {

        // Create the form submission data
        Spanner newSpanner =  new Spanner();
        newSpanner.setName("Keeley");
        newSpanner.setSize(12);

        // Stub logged in user
        Principal currentUser = new TestingAuthenticationToken("user", "pass");

        // Submit the form
        ModelAndView response = controller.addSpanner(newSpanner, currentUser);
        assertNotNull("no response", response);

        // Verify that spanner was created
        ArgumentCaptor<Spanner> spannerArgumentCaptor = ArgumentCaptor.forClass(Spanner.class);
        verify(spannersDAO).create(spannerArgumentCaptor.capture());

        // Verify that spanner has has name and size set from the form submission
        Spanner createdSpanner = spannerArgumentCaptor.getValue();
        assertEquals("name", newSpanner.getName(), createdSpanner.getName());
        assertEquals("size", newSpanner.getSize(), createdSpanner.getSize());

        // Verify that spanner owner is current logged in user
        assertEquals("owner", currentUser.getName(), createdSpanner.getOwner());

        // Assert that we forward to correct page
        assertEquals("view", VIEW_SUCCESS, response.getViewName());

    }

}
