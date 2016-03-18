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
 * Test access control of SpannersService.
 * This tests that only authorized users may access SpannersService methods.
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

    @Qualifier("spannersService")
    @Autowired protected SpannersService spannersService; // Injected by springy magic
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
        spannersService.get(1);
        spannersService.getAll();

        // Viewer should not have access to create / update / delete
        Spanner spanner = newSpanner();
        verifyException(spannersService, AccessDeniedException.class).create(spanner);
        verifyException(spannersService, AccessDeniedException.class).update(spanner);
        verifyException(spannersService, AccessDeniedException.class).delete(spanner);
    }

    @Test
    @WithMockUser(roles=ROLE_EDITOR)
    public void testEditorAccess() {

        // Editor should have access to create / update / delete - just call the method to check no exception is thrown
        Spanner spanner = newSpanner();
        spannersService.create(spanner);
        spannersService.update(spanner);
        spannersService.delete(spanner);

        // Editor should not have access to get* methods (seems daft but them's the rules...)
        verifyException(spannersService, AccessDeniedException.class).get(1);
        verifyException(spannersService, AccessDeniedException.class).getAll();
    }

    @Test
    @WithMockUser(username = "smith", roles = ROLE_VIEWER)
    public void testOwnerAccess() {

        // Smith can create a spanner only if he's the owner
        Spanner newSpanner = newSpanner();
        newSpanner.setOwner("smith");
        spannersService.create(newSpanner);

        // Smith should be able to update / delete spanner 1
        Spanner spanner1 = spannersService.get(1);
        spannersService.update(spanner1);
        spannersService.delete(spanner1);

        // Smith should not be able to create a spanner for another user
        newSpanner = newSpanner();
        newSpanner.setOwner("jones");
        verifyException(spannersService, AccessDeniedException.class).create(newSpanner);

        // Smith should not be able to update / delete spanner 2
        Spanner spanner2 = spannersService.get(2);
        verifyException(spannersService, AccessDeniedException.class).update(spanner2);
        verifyException(spannersService, AccessDeniedException.class).delete(spanner2);
    }

    @Test
    public void testNotLoggedIn() {

        // If we're not logged in, we should not be able to access any method of SpannersService
        Spanner spanner = newSpanner();
        verifyException(spannersService, AuthenticationCredentialsNotFoundException.class).get(1);
        verifyException(spannersService, AuthenticationCredentialsNotFoundException.class).getAll();
        verifyException(spannersService, AuthenticationCredentialsNotFoundException.class).create(spanner);
        verifyException(spannersService, AuthenticationCredentialsNotFoundException.class).update(spanner);
        verifyException(spannersService, AuthenticationCredentialsNotFoundException.class).delete(spanner);
    }


    private Spanner newSpanner() {
        Spanner spanner = new Spanner();
        spanner.setName("Bertha");
        spanner.setSize(16);
        return spanner;
    }


}
