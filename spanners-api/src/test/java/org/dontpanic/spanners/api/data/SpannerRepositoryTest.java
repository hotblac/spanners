package org.dontpanic.spanners.api.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.dontpanic.spanners.api.stubs.SpannerBuilder.aTestSpanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for the Spring Boot managed SpannerRepository
 * Created by stevie on 04/06/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpannerRepositoryTest {

    @Autowired private SpannerRepository repository;

    @Test
    public void testCreate() {

        // Create the new spanner
        Spanner savedSpanner = aTestSpanner().build();
        savedSpanner = repository.save(savedSpanner);
        assertNotNull(savedSpanner);
        assertNotNull(savedSpanner.getId());

        // Check it's there
        Spanner loadedSpanner = repository.findOne(savedSpanner.getId());
        assertSpannerEquals(savedSpanner, loadedSpanner);
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
