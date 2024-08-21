package com.udacity.thangnm1.superduperdrive;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserTests {
	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	/**
	 * Test that verifies that an unauthorized user can only access the login or sign up pages
	 */
	@Test
	public void testPageAccess() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * Test create a new user, logs in, go to the home page
	 * When logs out the home page is no longer accessible.
	 */
	@Test
	public void testSignUpLoginLogout() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignUpTests signUp = new SignUpTests(driver);
		signUp.setFirstName("Nguyen");
		signUp.setLastName("Thang");
		signUp.setUserName("wingo");
		signUp.setPassword("123456");
		signUp.signUp();

		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		SignInTests signIn = new SignInTests(driver);
		signIn.setUserName("wingo");
		signIn.setPassword("123456");
		signIn.login();

		driver.get("http://localhost:" + this.port + "/home");
		// Use Duration to set the timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Assertions.assertEquals("Login", driver.getTitle());
	}
}
