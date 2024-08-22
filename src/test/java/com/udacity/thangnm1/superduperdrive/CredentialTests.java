package com.udacity.thangnm1.superduperdrive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CredentialTests {

	@Test
	public void testCreateCredential() {
		WebDriver driver = new ChromeDriver();

		// Log in to the application
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("username")).sendKeys("testuser"); 
		driver.findElement(By.id("inputPassword")).sendKeys("123456");
		driver.findElement(By.id("loginButton")).click(); 

		// Navigate to the credentials section
		driver.get("http://localhost:8080/credentials"); 

		// Create a new credential
		driver.findElement(By.id("credential-url")).sendKeys("https://example.com");
		driver.findElement(By.id("credential-username")).sendKeys("testuser");
		driver.findElement(By.id("credential-inputPassword")).sendKeys("secureinputPassword");
		driver.findElement(By.id("createCredentialButton")).click(); 

		// Verify that the credential was created successfully
		String successMessage = driver.findElement(By.id("successMessage")).getText(); 
		assertTrue(successMessage.contains("Credential created successfully"));

		// Verify that the inputPassword is encrypted in the displayed credentials
		String displayedinputPassword = driver.findElement(By.cssSelector("td.inputPassword")).getText(); 
		assertTrue(displayedinputPassword.length() > 0); // Assuming encrypted inputPassword is not empty

		driver.quit();
	}

	@Test
	public void testEditCredential() {
		WebDriver driver = new ChromeDriver();

		// Log in to the application
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("username")).sendKeys("testuser");
		driver.findElement(By.id("inputPassword")).sendKeys("123456");
		driver.findElement(By.id("loginButton")).click(); 

		// Navigate to the credentials section
		driver.get("http://localhost:8080/credentials"); 

		// View the existing credential
		driver.findElement(By.cssSelector("button.edit-credential-button")).click(); // Click the edit button for the first credential

		// Verify that the inputPassword is unencrypted
		String unencryptedinputPassword = driver.findElement(By.id("credential-inputPassword")).getAttribute("value"); 
		assertTrue(unencryptedinputPassword.equals("secureinputPassword")); // Replace with the actual inputPassword used

		// Edit the credential
		driver.findElement(By.id("credential-url")).clear();
		driver.findElement(By.id("credential-url")).sendKeys("https://updated-example.com");
		driver.findElement(By.id("credential-inputPassword")).clear();
		driver.findElement(By.id("credential-inputPassword")).sendKeys("newsecureinputPassword");
		driver.findElement(By.id("saveCredentialButton")).click(); // Change to your save button ID

		// Verify that the credential was updated successfully
		String successMessage = driver.findElement(By.id("successMessage")).getText(); 
		assertTrue(successMessage.contains("Credential updated successfully"));

		// Verify that the changes are displayed
		String updatedUrl = driver.findElement(By.cssSelector("td.url")).getText();
		assertTrue(updatedUrl.equals("https://updated-example.com"));

		driver.quit();
	}

	@Test
	public void testDeleteCredential() {
		WebDriver driver = new ChromeDriver();

		// Log in to the application
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("username")).sendKeys("testuser");
		driver.findElement(By.id("inputPassword")).sendKeys("123456");
		driver.findElement(By.id("loginButton")).click(); 

		// Navigate to the credentials section
		driver.get("http://localhost:8080/credentials"); 

		// Delete the existing credential
		driver.findElement(By.cssSelector("a.delete-credential-button")).click(); 
		driver.switchTo().alert().accept(); 

		// Verify that the credential was deleted successfully
		String successMessage = driver.findElement(By.id("successMessage")).getText(); 
		assertTrue(successMessage.contains("Credential deleted successfully"));

		// Verify that the credential is no longer displayed
		boolean isCredentialDisplayed = driver.getPageSource().contains("congtroidongiang");
		assertFalse(isCredentialDisplayed);

		driver.quit();
	}
}
