package org.dontpanic.spanners.stubs;

import org.dontpanic.spanners.dao.Spanner;
import org.dontpanic.spanners.springmvc.forms.SpannerForm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Stubs for spanners
 * User: Stevie
 * Date: 13/10/13
 */
public class SpannersStubs {

    public static final int SPANNER_ID = 99;
    public static final Spanner SPANNER = stubSpanner(SPANNER_ID);
    public static final List<Spanner> SPANNERS = stubSpanners();

    public static List<Spanner> stubSpanners() {
        List<Spanner> spanners = new ArrayList<Spanner>();
        for (int i=1; i<=5; i++) {
            spanners.add(stubSpanner(i));
        }
        return spanners;
    }

    public static Spanner stubSpanner(int id) {
        Spanner spanner = new Spanner();
        spanner.setId(id);
        spanner.setName("Spanner number " + id);
        spanner.setOwner("Owner");
        spanner.setSize(10 + id);
        return spanner;
    }


    public static SpannerForm stubSpannerForm(int id) {
        SpannerForm formData = new SpannerForm();
        formData.setId(id);
        formData.setName("Spanner number " + id);
        formData.setSize(10 + id);
        return formData;
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


    /**
     * Assert that spanner form matches given spanner
     * Both expected and actual spanner objects are non-null.
     */
    public static void assertSpannerEquals(Spanner expected, SpannerForm actual) {

        assertNotNull("Spanners not equal: expected is null", expected);
        assertNotNull("Spanners not equal: actual is null", actual);

        assertEquals("spanner id", expected.getId(), actual.getId());
        assertEquals("spanner name", expected.getName(), actual.getName());
        assertEquals("spanner size", expected.getSize(), actual.getSize());
    }


    /**
     * Assert that spanner matches submitted spanner form data
     * Both expected and actual spanner objects are non-null.
     */
    public static void assertSpannerEquals(SpannerForm expected, Spanner actual) {

        assertNotNull("Spanners not equal: expected is null", expected);
        assertNotNull("Spanners not equal: actual is null", actual);

        assertEquals("spanner id", expected.getId(), actual.getId());
        assertEquals("spanner name", expected.getName(), actual.getName());
        assertEquals("spanner size", expected.getSize(), actual.getSize());
    }
}
