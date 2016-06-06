package org.dontpanic.spanners.springmvc.error;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.dontpanic.spanners.springmvc.error.UnauthorizedErrorController.VIEW_UNAUTHORIZED;

/**
 * Tests for HTTP 403 (unauthorized) page
 * Created by Stevie on 15/06/2015.
 */
public class UnauthorizedErrorControllerTest {

    private UnauthorizedErrorController controller = new UnauthorizedErrorController();

    @Test
    public void testForwardView() {
        String view = controller.unauthorizedError();
        assertEquals(VIEW_UNAUTHORIZED, view);
    }

}
