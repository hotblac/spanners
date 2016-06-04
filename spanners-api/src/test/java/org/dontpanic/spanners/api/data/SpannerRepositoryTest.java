package org.dontpanic.spanners.api.data;

import org.dontpanic.spanners.api.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.dontpanic.spanners.api.stubs.SpannerBuilder.aTestSpanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by stevie on 04/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpannerRepositoryTest {

    @Autowired SpannerRepository repository;

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


    /**
     * Check that given spanner has expected properties
     * @param spanner to be checked
     * @param id expected id
     * @param name expected name
     * @param size expected size
     */
    private void checkSpanner(Spanner spanner, Long id, String name, int size, String owner) {
        assertNotNull(spanner);
        assertEquals(id, spanner.getId());
        assertEquals(name, spanner.getName());
        assertEquals(size, spanner.getSize());
        assertEquals(owner, spanner.getOwner());
    }


}
