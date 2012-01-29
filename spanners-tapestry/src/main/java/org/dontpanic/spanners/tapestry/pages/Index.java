package org.dontpanic.spanners.tapestry.pages;

/**
 * Start page of application spanners-tapestry.
 */
public class Index extends SpannerPage {

    public String getSpanner1() {
        return getSpannersDAO().get(1).getName();
    }


}
