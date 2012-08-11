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

    public String execute() throws Exception {
        if (spanner == null) {
            // Retrieve it for display
            spanner = spannersDAO.get(id);
        } else {
            // Submit edits
            spannersDAO.update(spanner);
            message = "Spanner successfuly updated";
        }
        return SUCCESS;
    }

}
