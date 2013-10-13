package org.dontpanic.spanners.springmvc.exception;

/**
 * Exception when a spanner cannot be displayed because it is not found in database.
 * User: Stevie
 * Date: 13/10/13
 */
public class SpannerNotFoundException extends Exception {

    private int spannerId;

    /**
     * New exception
     * @param spannerId id of requested spanner
     */
    public SpannerNotFoundException(int spannerId) {
        super("Spanner not found: " + spannerId);
        this.spannerId = spannerId;
    }

    public int getSpannerId() {
        return spannerId;
    }
}
