package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.Spanner;

/**
 * Delete given spanner
 * User: Stevie
 * Date: 20/05/12
 */
public class DeleteSpannerAction extends SpannerAction {

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
        spannersDAO.delete(spanner);
		return SUCCESS;
	}
}
