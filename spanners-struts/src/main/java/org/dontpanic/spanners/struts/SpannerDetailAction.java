package org.dontpanic.spanners.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.dontpanic.spanners.Spanner;
import org.dontpanic.spanners.SpannersDAO;

/**
 * Display a single spanner
 * User: Stevie
 * Date: 24/02/12
 */
public class SpannerDetailAction extends ActionSupport {

    private SpannersDAO spannersDAO;
    private Spanner spanner;

    public void setSpannersDAO(SpannersDAO spannersDAO) {
        this.spannersDAO = spannersDAO;
    }

    public Spanner getSpanner() {
        return spanner;
    }

    public String execute() throws Exception {
        spanner = spannersDAO.get(1);
		return SUCCESS;
	}

}
