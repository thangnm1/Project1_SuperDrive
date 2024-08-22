package com.udacity.thangnm1.superduperdrive;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NoteTests {

    @Test
    public void testCreateNote() {
        WebDriver driver = new ChromeDriver();
        
        // Log in to the application
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("inputUsername")).sendKeys("wingo"); 
        driver.findElement(By.id("inputPassword")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click(); 

        // Navigate to the notes section
        driver.get("http://localhost:8080/notes"); 

        // Open the modal to create a new note
        driver.findElement(By.id("create-note-btn")).click(); // Button to add a new note

        // Fill in the note details
        driver.findElement(By.id("note-title")).sendKeys("Test Note Title");
        driver.findElement(By.id("note-description")).sendKeys("This is a test note description.");
        driver.findElement(By.id("noteSubmit")).click(); // Submit the form

        // Verify that the note was created successfully
        String successMessage = driver.findElement(By.id("successMessage")).getText();
        assertTrue(successMessage.contains("Note created successfully"));

        driver.quit();
    }


    @Test
    public void testEditNote() {
        WebDriver driver = new ChromeDriver();
        
        // Log in to the application
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("inputUsername")).sendKeys("wingo"); 
        driver.findElement(By.id("inputPassword")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click();

        // Navigate to the notes section
        driver.get("http://localhost:8080/notes"); 

        // Open the modal to edit an existing note
        driver.findElement(By.cssSelector("button.update-note-button")).click(); // Click the edit button of the first note

        // Edit the note details
        driver.findElement(By.id("note-title")).clear();
        driver.findElement(By.id("note-title")).sendKeys("Updated Note Title");
        driver.findElement(By.id("note-description")).clear();
        driver.findElement(By.id("note-description")).sendKeys("This is an updated test note description.");
        driver.findElement(By.id("noteSubmit")).click(); // Submit the form

        // Verify that the note was updated successfully
        String successMessage = driver.findElement(By.id("successMessage")).getText();
        assertTrue(successMessage.contains("Note updated successfully"));

        driver.quit();
    }


    @Test
    public void testDeleteNote() {
        WebDriver driver = new ChromeDriver();
        
        // Log in to the application
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("inputUsername")).sendKeys("wingo");
        driver.findElement(By.id("inputPassword")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click(); 

        // Navigate to the notes section
        driver.get("http://localhost:8080/notes");

        // Delete the existing note
        driver.findElement(By.cssSelector("a.delete-note-button")).click(); // Click the delete button of the first note
        driver.switchTo().alert().accept(); 

        // Verify that the note was deleted successfully
        String successMessage = driver.findElement(By.id("successMessage")).getText();
        assertTrue(successMessage.contains("Note deleted successfully"));

        driver.quit();
    }
}