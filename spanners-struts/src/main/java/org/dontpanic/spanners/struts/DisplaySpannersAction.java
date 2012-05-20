package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.Spanner;

import java.util.List;

/**
 * Display all spanners
 * User: Stevie
 * Date: 20/05/12
 */
public class DisplaySpannersAction extends SpannerAction {

    private List<Spanner> spanners;

    public List<Spanner> getSpanners() {
        return spanners;
    }

    public String execute() throws Exception {
        spanners = spannersDAO.getAll();
        return SUCCESS;
	}
}
