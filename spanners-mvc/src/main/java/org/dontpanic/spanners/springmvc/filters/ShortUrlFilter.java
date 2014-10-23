package org.dontpanic.spanners.springmvc.filters;

import org.apache.commons.lang.StringUtils;
import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.dao.SpannersDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A filter that forwards to a spanner view based on the URL.
 * User: Stevie
 * Date: 23/10/14
 */
public class ShortUrlFilter implements Filter {

    private SpannersDAO spannersDao;

    public void setSpannersDao(SpannersDAO spannersDao) {
        this.spannersDao = spannersDao;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (request instanceof  HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;

            String pathInfo = httpRequest.getPathInfo();
            String spannerName = StringUtils.removeStart(pathInfo, "/");

            // Find the spanner by name
            Spanner spanner = spannersDao.findByName(spannerName);

            if (spanner == null) {
                // No such spanner, continue processing as normal
                filterChain.doFilter(request, response);
            } else {
                // Stop the current request and display the spanner's details instead
                String redirectPath = redirectPath(httpRequest, spanner);
                httpResponse.sendRedirect(redirectPath);
            }
        }
    }


    /**
     * Assemble the redirect path to the spanner detail page for the given spanner
     */
    private String redirectPath(HttpServletRequest request, Spanner spanner) {
        StringBuffer path  = new StringBuffer();
        path.append(request.getContextPath())
                .append("/detailSpanner?id=")
                .append(spanner.getId());
        return path.toString();
    }
}
