package org.dontpanic.spanners.springmvc.stubs;

import org.dontpanic.spanners.springmvc.domain.Spanner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Stubs for spanners
 * User: Stevie
 * Date: 13/10/13
 */
public class SpannersStubs {

    public static final Long SPANNER_ID = 99l;
    public static final Spanner SPANNER = stubSpanner(SPANNER_ID);
    public static final List<Spanner> SPANNERS = stubSpanners();

    public static List<Spanner> stubSpanners() {
        List<Spanner> spanners = new ArrayList<Spanner>();
        for (long i=1; i<=5; i++) {
            spanners.add(stubSpanner(i));
        }
        return spanners;
    }

    public static Spanner stubSpanner(Long id) {
        Spanner spanner = new Spanner();
        spanner.setId(id);
        spanner.setName("Spanner number " + id);
        spanner.setOwner("Owner");
        spanner.setSize(10 + id.intValue());
        return spanner;
    }


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

}
