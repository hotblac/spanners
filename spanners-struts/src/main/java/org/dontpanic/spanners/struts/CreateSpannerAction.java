package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.dao.Spanner;

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

    /**
     * Draws the CreateSpanner page ready for user input
     */
    public String draw() throws Exception {
        spanner = new Spanner();
        message = null;
        return SUCCESS;
    }

    /**
     * Submits user inputted spanner to database
     */
    public String submit() throws Exception {
        spanner.setOwner(getUsername());
        spannersDAO.create(spanner);
        message = "You have created a spanner called: " + spanner.getName();
        return SUCCESS;
    }
}
