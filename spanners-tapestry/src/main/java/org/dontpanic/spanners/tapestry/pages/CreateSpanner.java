package org.dontpanic.spanners.tapestry.pages;

import org.apache.tapestry5.annotations.Property;
import org.dontpanic.spanners.Spanner;

/**
 * Page for creating a new spanner
 * User: Stevie
 * Date: 29/01/12
 */
public class CreateSpanner extends SpannerPage {

    @Property
    private String name;

    @Property
    private Integer size;

    String onSuccess() {

        Spanner spanner = new Spanner();
        spanner.setName(name);
        spanner.setSize(size);

        getSpannersDAO().create(spanner);
        return "CreateSpanner";
    }
}
