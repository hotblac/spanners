package org.dontpanic.spanners.struts;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


    /**
     * Test that we can login and view the main page
     */
    @Test
    public void testLogin() {

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:9090/spanners-struts/");

        // Enter username and password
        WebElement userField = driver.findElement(By.name("j_username"));
        WebElement passwordField = driver.findElement(By.name("j_password"));
        userField.sendKeys("smith");
        passwordField.sendKeys("password");

        // Click Login button
        WebElement button = driver.findElement(By.name("submit"));
        button.click();

        // Check that user is logged in and spanners list page is shown
        assertEquals("Display Spanners", driver.getTitle());
    }

}
