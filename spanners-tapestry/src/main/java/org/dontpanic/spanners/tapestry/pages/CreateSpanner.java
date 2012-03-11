package org.dontpanic.spanners.tapestry.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.dontpanic.spanners.Spanner;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

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

    @Property
    @Persist
    private String message;

    String onSuccess() {

        Spanner spanner = new Spanner();
        spanner.setName(name);
        spanner.setSize(size);

        getSpannersDAO().create(spanner);

        message = "You've just created a spanner called " + name;
        return "CreateSpanner";
    }

    /**
     * Simplest format - the string is returned unformatted.
     */
    public Format getDefaultFormat() {
        return new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                return toAppendTo.append(obj);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return source;
            }
        };
    }
}
