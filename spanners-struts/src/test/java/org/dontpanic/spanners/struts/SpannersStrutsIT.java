package org.dontpanic.spanners.struts;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.*;

/**
 * Simple integration tests verifying that the app is started and connected
 * User: Stevie
 * Date: 09/01/13
 */
public class SpannersStrutsIT {

    /**
     * Test that the application starts up
     */
    @Test
    public void testAppStarted() {

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:9090/spanners-struts/");
        // Check that the login page is shown
        assertEquals("Login Page", driver.getTitle());
    }

}
