package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.Spanner;

/**
 * Page to create a new Spanner
 * User: Stevie
 * Date: 03/03/12
 */
public class CreateSpannerAction extends SpannerAction {

    private Spanner spanner;
    private String message;

    public Spanner getSpanner() {
        return spanner;
    }

    public void setSpanner(Spanner spanner) {
        this.spanner = spanner;
    }

    public String getMessage() {
        return message;
    }

    public String execute() throws Exception {
        if (spanner != null) {
            spannersDAO.create(spanner);
            message = "You have created a spanner called: " + spanner.getName();
        }
        return SUCCESS;
    }
}
