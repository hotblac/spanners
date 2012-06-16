package org.dontpanic.spanners;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
                "classpath:spring-hibernate.xml", // Containing Hibernate session bean, transaction config and DAOs
                "classpath:spring-test-dataSources.xml" // Containing HyperSQL datasource and test Hibernate properties
        })
public class SpannersTest {

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
        checkSpanner(hazell, 1, "Hazell", 12);
    }

    @Test
    public void testGetAll() {
        List<Spanner> spanners = spannersDAO.getAll();
        assertNotNull(spanners);
        assertEquals(2, spanners.size());
        checkSpanner(spanners.get(0), 1, "Hazell", 12);
        checkSpanner(spanners.get(1), 2, "Kitty", 18);
    }


    @Test
    public void testCreate() {

        // Create the new spanner
        Spanner newSpanner = newSpanner(); // Bertha
        int id = spannersDAO.create(newSpanner);

        // Check it's there
        Spanner bertha = spannersDAO.get(id);
        checkSpanner(bertha, id, newSpanner.getName(), newSpanner.getSize());

        // Check original data is still intact
        Spanner hazell = spannersDAO.get(1);
        checkSpanner(hazell, 1, "Hazell", 12);
    }


    @Test(expected = ConstraintViolationException.class)
    public void testCreateDuplicate() {

        // Create a spanner with the same name as an existing one
        Spanner evilHazell = duplicateSpanner();
        try {
            spannersDAO.create(evilHazell); // throws DataIntegrityViolationException - hbm specifies that name must be unique
        } finally {
             // Check real Hazell has not been violated!
            Spanner realHazell = spannersDAO.get(1); // Hazell
            checkSpanner(realHazell, 1, "Hazell", 12);
        }
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testNullSpanner() {
    	spannersDAO.create(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testZeroSizeSpanner() {
    	
    	// Create a spanner with zero size
    	Spanner kate = zeroSizeSpanner();
		spannersDAO.create(kate);	
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

    /**
     * Bertha
     * @return A new Spanner called Bertha
     */
    private Spanner newSpanner() {
        Spanner spanner = new Spanner();
        spanner.setName("Bertha");
        spanner.setSize(16);
        return spanner;
    }


    /**
     * Hazell
     * @return A new Spanner with the same name as an existing Spanner
     */
    private Spanner duplicateSpanner() {
        Spanner spanner = new Spanner();
        spanner.setName("Hazell");
        spanner.setSize(8);
        return spanner;
    }
    
    
    /**
     * Kate
     * @return A new Spanner with zero size
     */
    private Spanner zeroSizeSpanner() {
    	Spanner spanner = new Spanner();
    	spanner.setName("Kate");
    	spanner.setSize(0);
    	return spanner;
    }


    /**
     * Check that given spanner has expected properties
     * @param spanner to be checked
     * @param id expected id
     * @param name expected name
     * @param size expected size
     */
    private void checkSpanner(Spanner spanner, int id, String name, int size) {
        assertNotNull(spanner);
        assertEquals(id, spanner.getId());
        assertEquals(name, spanner.getName());
        assertEquals(size, spanner.getSize());
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
