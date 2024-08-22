package com.udacity.thangnm1.superduperdrive;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnauthorizedAccessTest {

    @Test
    public void testUnauthorizedUserAccess() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/home");

        // Check if redirected to login page
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/login", currentUrl);

        // Check if login page is accessible
        driver.get("http://localhost:8080/login");
        assertEquals("Login", driver.getTitle());

        // Check if signup page is accessible
        driver.get("http://localhost:8080/signup");
        assertEquals("Sign Up", driver.getTitle());

        driver.quit();
    }
}