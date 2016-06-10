package org.dontpanic.spanners.springmvc.stubs;

import org.dontpanic.spanners.springmvc.domain.Spanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by stevie on 10/06/16.
 */
public class SpannerAssert {
    /**
     * Assert that two spanners are equal:
     * They have same id and all attributes are equal.
     * Both expected and actual spanner objects are non-null.
     */
    public static void assertSpannerEquals(Spanner expected, Spanner actual) {

        assertNotNull("Spanners not equal: expected is null", expected);
        assertNotNull("Spanners not equal: actual is null", actual);

        assertEquals("spanner id", expected.getId(), actual.getId());
        assertEquals("spanner name", expected.getName(), actual.getName());
        assertEquals("spanner owner", expected.getOwner(), actual.getOwner());
        assertEquals("spanner size", expected.getSize(), actual.getSize());
    }

    public static void assertSpanner(String expectedName, int expectedSize, String expectedOwner, Spanner actual) {
        assertNotNull("Spanner is null", actual);
        assertEquals("spanner name", expectedName, actual.getName());
        assertEquals("spanner size", expectedSize, actual.getSize());
        assertEquals("spanner owner", expectedOwner, actual.getOwner());
    }
}
