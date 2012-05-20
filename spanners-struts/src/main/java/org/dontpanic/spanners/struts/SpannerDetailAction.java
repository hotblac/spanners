package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.Spanner;

/**
 * Display a single spanner
 * User: Stevie
 * Date: 24/02/12
 */
public class SpannerDetailAction extends SpannerAction {

    private int id;
    private Spanner spanner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spanner getSpanner() {
        return spanner;
    }

    public String execute() throws Exception {
        spanner = spannersDAO.get(id);
		return SUCCESS;
	}

}
