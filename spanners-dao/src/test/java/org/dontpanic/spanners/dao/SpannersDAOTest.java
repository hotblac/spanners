package org.dontpanic.spanners.dao;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Test functionality of SpannersDAO.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
                "classpath:spring-hibernate.xml", // Containing Hibernate session bean, transaction config and DAOs
                "classpath:spring-test-dataSources.xml" // Containing HyperSQL datasource and test Hibernate properties
        })
public class SpannersDAOTest {

    @Qualifier("spannersDAO")
    @Autowired protected SpannersDAO spannersDAO; // Injected by springy magic
    @Autowired protected DataSource ds; // Injected by springy magic
    protected IDatabaseTester dbTester;


    @Before
    public void setUp() throws Exception {
        dbTester = new DataSourceDatabaseTester(ds);

        IDataSet dataSet = new FlatXmlDataSet(getClass().getResource("SpannersTest.xml"));
        dbTester.setDataSet(dataSet);
        dbTester.onSetup();
    }


    @After
    public void tearDown() throws Exception {
        dbTester.onTearDown();
    }


    @Test
    public void testGet() {
        Spanner hazell = spannersDAO.get(1);
        checkSpanner(hazell, 1, "Hazell", 12, "smith");
    }

    @Test
    public void testGetAll() {
        List<Spanner> spanners = spannersDAO.getAll();
        assertNotNull(spanners);
        assertEquals(2, spanners.size());
        checkSpanner(spanners.get(0), 1, "Hazell", 12, "smith");
        checkSpanner(spanners.get(1), 2, "Kitty", 18, "jones");
    }


    @Test
    public void testCreate() {

        // Create the new spanner
        Spanner savedSpanner = new SpannerBuilder().createSpanner();
        int id = spannersDAO.create(savedSpanner);

        // Check it's there
        Spanner loadedSpanner = spannersDAO.get(id);
        assertSpannerEquals(savedSpanner, loadedSpanner);

        // Check original data is still intact
        Spanner hazell = spannersDAO.get(1);
        checkSpanner(hazell, 1, "Hazell", 12, "smith");
    }


    @Test(expected = ConstraintViolationException.class)
    public void testCreateDuplicate() {

        // Create a spanner with the same name as an existing one
        Spanner evilHazell = new SpannerBuilder()
                                    .setName("Hazell")
                                    .createSpanner();
        try {
            spannersDAO.create(evilHazell); // throws DataIntegrityViolationException - hbm specifies that name must be unique
        } finally {
             // Check real Hazell has not been violated!
            Spanner realHazell = spannersDAO.get(1); // Hazell
            checkSpanner(realHazell, 1, "Hazell", 12, "smith");
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullSpanner() {
    	spannersDAO.create(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testZeroSizeSpanner() {

    	// Create a spanner with zero size
    	Spanner zeroSizeSpanner = new SpannerBuilder()
                                    .setSize(0)
                                    .createSpanner();
		spannersDAO.create(zeroSizeSpanner);
    }


    @Test (expected = IllegalArgumentException.class)
    public void testZeroSizeSpannerWithoutBuilder() {

        Spanner spanner = new Spanner();
        spanner.setId(1); // Better set an id. Not necessary for the test but every spanner should have an id.
        spanner.setName("Bertha"); // Better set a name too
        spanner.setOwner("Mr Smith"); // Again, we're not testing this, but every spanner should have an owner
        spanner.setSize(0); // This is the important bit! The important attribute of this spanner is that its size is zero!

		spannersDAO.create(spanner);
    }


    @Test
    public void testUpdate() {
        Spanner hazell = spannersDAO.get(1);
        hazell.setName("Bertha");
        hazell.setSize(10);
        spannersDAO.update(hazell);

        // Check it's updated
        Spanner updated = spannersDAO.get(1);
        assertEquals(hazell.getId(), updated.getId());
        assertEquals(hazell.getName(), updated.getName());
        assertEquals(hazell.getSize(), updated.getSize());
    }


    @Test
    public void testDelete() {

        List<Spanner> spanners = spannersDAO.getAll();
        int numberOfSpanners = spanners.size();
        assertTrue(numberOfSpanners > 0);
        for (Spanner spanner : spanners) {
            spannersDAO.delete(spanner);
            numberOfSpanners--;

            spanners = spannersDAO.getAll();
            assertEquals(numberOfSpanners, spanners.size());
            assertNotPresent(spanner, spanners);
        }
        assertEquals(0, numberOfSpanners);
    }


    private class SpannerBuilder {

        public static final int DEFAULT_ID = 1;
        public static final String DEFAULT_NAME = "Bertha";
        public static final int DEFAULT_SIZE = 16;
        public static final String DEFAULT_OWNER = "Mr Smith";

        private int id = DEFAULT_ID;
        private String name = DEFAULT_NAME;
        private int size = DEFAULT_SIZE;
        private String owner = DEFAULT_OWNER;

        public SpannerBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SpannerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SpannerBuilder setSize(int size) {
            this.size = size;
            return this;
        }

        public SpannerBuilder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Spanner createSpanner() {
            Spanner spanner = new Spanner();
            spanner.setId(this.id);
            spanner.setName(this.name);
            spanner.setSize(this.size);
            spanner.setOwner(this.owner);
            return spanner;
        }
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
    private void checkSpanner(Spanner spanner, int id, String name, int size, String owner) {
        assertNotNull(spanner);
        assertEquals(id, spanner.getId());
        assertEquals(name, spanner.getName());
        assertEquals(size, spanner.getSize());
        assertEquals(owner, spanner.getOwner());
    }


    /**
     * Assert that the given spanner is not present in list of spanners
     */
    private void assertNotPresent(Spanner spanner, List<Spanner> spanners) {
        for (Spanner thisSpanner : spanners) {
            if (spanner.getId() == thisSpanner.getId()) {
                fail("Spanner id=" + spanner.getId() + " should not be present");
            } else if (spanner.getSize() == thisSpanner.getSize()) {
                fail("Spanner size=" + spanner.getSize() + " should not be present");
            } else if (spanner.getName().equals(thisSpanner.getName())) {
                fail("Spanner name=" + spanner.getName() + " should not be present");
            }
        }
    }

}