package com.udacity.thangnm1.superduperdrive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserTests {

    @Test
    public void testUserSignupLoginLogout() {
        WebDriver driver = new ChromeDriver();

        // Sign up a new user
        driver.get("http://localhost:8080/signup");
        driver.findElement(By.id("inputinputUsername")).sendKeys("wingo"); 
        driver.findElement(By.id("inputPassword")).sendKeys("123456");
        driver.findElement(By.id("inputFirstName")).sendKeys("Nguyen");
        driver.findElement(By.id("inputLastName")).sendKeys("Thang");
        driver.findElement(By.id("buttonSignUp")).click(); 

        // Verify successful sign up and redirect to login page

        // Log in with the new user
        driver.findElement(By.id("inputinputUsername")).sendKeys("wingo");
        driver.findElement(By.id("inputPassword")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click(); 

        // Verify that the home page is accessible
        assertEquals("Home", driver.getTitle()); 

        // Log out

        // Verify that the home page is no longer accessible
        driver.get("http://localhost:8080/home");
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/login", currentUrl);

        driver.quit();
    }
}
