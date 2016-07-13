package org.dontpanic.spanners.users.data;

import org.dontpanic.spanners.users.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Spring framework tests on the user repository.
 * Created by stevie on 13/07/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepositoryTest {

    private static final String USERNAME = "jbloggs";
    private static final String PASSWORD = "password123";
    private static final Boolean ENABLED = true;

    @Autowired private UserRepository repository;

    @Test
    public void testCreate() {

        User user = new User(USERNAME, PASSWORD, ENABLED);

        // Create a new user
        User savedUser = repository.save(user);
        assertNotNull(savedUser);

        // Check it's there
        User loadedSpanner = repository.findOne(USERNAME);
        assertNotNull(loadedSpanner);
        assertEquals(USERNAME, loadedSpanner.getUsername());
        assertEquals(PASSWORD, loadedSpanner.getPassword());
        assertEquals(ENABLED, loadedSpanner.getEnabled());
    }

}