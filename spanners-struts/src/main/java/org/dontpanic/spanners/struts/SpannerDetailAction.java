package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.Spanner;

/**
 * Display a single spanner
 * User: Stevie
 * Date: 24/02/12
 */
public class SpannerDetailAction extends SpannerAction {

    private Spanner spanner;

    public Spanner getSpanner() {
        return spanner;
    }

    public String execute() throws Exception {
        spanner = spannersDAO.get(1);
		return SUCCESS;
	}

}
