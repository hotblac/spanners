package org.dontpanic.spanners.dao;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static com.googlecode.catchexception.CatchException.verifyException;


/**
 * Test access control of SpannersDAO.
 * This tests that only authorized users may access SpannersDAO methods.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
                "classpath:spring-hibernate.xml", // Containing Hibernate session bean, transaction config and DAOs
                "classpath:spring-test-dataSources.xml", // Containing HyperSQL datasource and test Hibernate properties
                "classpath:spring-test-security.xml" // Enables security annotations
        })
public class SpannersSecurityTest {

    // Note that the WithMockUser annotation prepends 'ROLE_' to these role names
    private static final String ROLE_VIEWER = "VIEWER";
    private static final String ROLE_EDITOR = "EDITOR";

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
    @WithMockUser(roles=ROLE_VIEWER)
    public void testViewerAccess() {

        // Viewer should have access to get* methods - just call the method to check no exception is thrown
        spannersDAO.get(1);
        spannersDAO.getAll();

        // Viewer should not have access to create / update / delete
        Spanner spanner = newSpanner();
        verifyException(spannersDAO, AccessDeniedException.class).create(spanner);
        verifyException(spannersDAO, AccessDeniedException.class).update(spanner);
        verifyException(spannersDAO, AccessDeniedException.class).delete(spanner);
    }

    @Test
    @WithMockUser(roles=ROLE_EDITOR)
    public void testEditorAccess() {

        // Editor should have access to create / update / delete - just call the method to check no exception is thrown
        Spanner spanner = newSpanner();
        spannersDAO.create(spanner);
        spannersDAO.update(spanner);
        spannersDAO.delete(spanner);

        // Editor should not have access to get* methods (seems daft but them's the rules...)
        verifyException(spannersDAO, AccessDeniedException.class).get(1);
        verifyException(spannersDAO, AccessDeniedException.class).getAll();
    }

    @Test
    @WithMockUser(username = "smith", roles = ROLE_VIEWER)
    public void testOwnerAccess() {

        // Smith can create a spanner only if he's the owner
        Spanner newSpanner = newSpanner();
        newSpanner.setOwner("smith");
        spannersDAO.create(newSpanner);

        // Smith should be able to update / delete spanner 1
        Spanner spanner1 = spannersDAO.get(1);
        spannersDAO.update(spanner1);
        spannersDAO.delete(spanner1);

        // Smith should not be able to create a spanner for another user
        newSpanner = newSpanner();
        newSpanner.setOwner("jones");
        verifyException(spannersDAO, AccessDeniedException.class).create(newSpanner);

        // Smith should not be able to update / delete spanner 2
        Spanner spanner2 = spannersDAO.get(2);
        verifyException(spannersDAO, AccessDeniedException.class).update(spanner2);
        verifyException(spannersDAO, AccessDeniedException.class).delete(spanner2);
    }

    @Test
    public void testNotLoggedIn() {

        // If we're not logged in, we should not be able to access any method of SpannersDAO
        Spanner spanner = newSpanner();
        verifyException(spannersDAO, AuthenticationCredentialsNotFoundException.class).get(1);
        verifyException(spannersDAO, AuthenticationCredentialsNotFoundException.class).getAll();
        verifyException(spannersDAO, AuthenticationCredentialsNotFoundException.class).create(spanner);
        verifyException(spannersDAO, AuthenticationCredentialsNotFoundException.class).update(spanner);
        verifyException(spannersDAO, AuthenticationCredentialsNotFoundException.class).delete(spanner);
    }


    private Spanner newSpanner() {
        Spanner spanner = new Spanner();
        spanner.setName("Bertha");
        spanner.setSize(16);
        return spanner;
    }


}
