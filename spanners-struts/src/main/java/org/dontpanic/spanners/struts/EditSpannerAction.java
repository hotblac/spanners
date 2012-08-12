package org.dontpanic.spanners.struts;

import org.dontpanic.spanners.dao.Spanner;

/**
 * Page to edit an existing Spanner
 * User: Stevie
 * Date: 16/06/12
 */
public class EditSpannerAction extends SpannerAction {

    private int id;
    private Spanner spanner;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spanner getSpanner() {
        return spanner;
    }

    public String getMessage() {
        return message;
    }

    public String draw() throws Exception {
        // Retrieve it for display
        spanner = spannersDAO.get(id);
        message = null;
        return SUCCESS;
    }

    public String submit() throws Exception {
        // Submit edits
        spannersDAO.update(spanner);
        message = "Spanner successfuly updated";
        return SUCCESS;
    }

}
