package org.dontpanic.spanners.springmvc.stubs;

import org.dontpanic.spanners.springmvc.domain.Spanner;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;
import org.junit.Assert;

import static org.junit.Assert.assertNotNull;

/**
 * Stubs for MVC forms
 * User: Stevie
 * Date: 26/10/14
 */
public class FormAssert {

    /**
     * Assert that spanner form matches given spanner
     * Both expected and actual spanner objects are non-null.
     */
    public static void assertSpannerEquals(Spanner expected, SpannerForm actual) {

        assertNotNull("Spanners not equal: expected is null", expected);
        assertNotNull("Spanners not equal: actual is null", actual);

        Assert.assertEquals("spanner id", expected.getId(), actual.getId());
        Assert.assertEquals("spanner name", expected.getName(), actual.getName());
        Assert.assertEquals("spanner size", expected.getSize(), actual.getSize());
    }


    /**
     * Assert that spanner matches submitted spanner form data
     * Both expected and actual spanner objects are non-null.
     */
    public static void assertSpannerEquals(SpannerForm expected, Spanner actual) {

        assertNotNull("Spanners not equal: expected is null", expected);
        assertNotNull("Spanners not equal: actual is null", actual);

        Assert.assertEquals("spanner id", expected.getId(), actual.getId());
        Assert.assertEquals("spanner name", expected.getName(), actual.getName());
        Assert.assertEquals("spanner size", expected.getSize(), actual.getSize());
    }
}
