package org.dontpanic.spanners.springmvc.filters;

import org.dontpanic.spanners.dao.SpannersDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.dontpanic.spanners.stubs.SpannersStubs.*;
import static org.mockito.Mockito.*;

/**
 * Test for the ShortUrlFilter
 * User: Stevie
 * Date: 23/10/14
 */
@RunWith(MockitoJUnitRunner.class)
public class ShortUrlFilterTest {

    private static final String CONTEXT_PATH = "/spanners-mvc";
    private static final String SPANNER_NAME = "Bertha";

    @Mock private SpannersDAO spannersDao;
    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private FilterChain filterChain;
    @InjectMocks private ShortUrlFilter filter = new ShortUrlFilter();

    @Before
    public void onSetUp() {
        // Default behaviour of stubs
        when(spannersDao.findByName(SPANNER_NAME)).thenReturn(SPANNER);
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);
    }

    /**
     * Test that we forward to correct spanner detail page if one exists
     */
    @Test
    public void testForward() throws Exception {

        // Spanner name is in request path
        when(request.getServletPath()).thenReturn("/" + SPANNER_NAME);

        // Filter is invoked as request is made
        filter.doFilter(request, response, filterChain);

        // Verify that we forward to correct page
        String expectedRedirect = CONTEXT_PATH + "/detailSpanner?id=" + SPANNER_ID;
        verify(response).sendRedirect(expectedRedirect);

        // Verify that filter chain does not continue
        verify(filterChain, never()).doFilter(any(ServletRequest.class), any(ServletResponse.class));
    }


    /**
     * Test that filter continues if no spanner is requested
     */
    @Test
    public void testSpannerNotFound() throws Exception {

        // A regular page is requested
        when(request.getServletPath()).thenReturn("/displaySpanners");

        // Filter is invoked as request is made
        filter.doFilter(request, response, filterChain);

        // Verify that next filter in chain is invoked
        verify(filterChain).doFilter(request, response);

        // Verify that no redirect is attempted
        verify(response, never()).sendRedirect(anyString());
    }
}
