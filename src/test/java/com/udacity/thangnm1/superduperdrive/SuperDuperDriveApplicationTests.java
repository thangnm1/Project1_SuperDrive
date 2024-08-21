package com.udacity.thangnm1.superduperdrive;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
class SuperDuperDriveApplicationTests {

	protected WebDriver driver;
	@LocalServerPort
	protected int port;

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

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
// create user and login
	protected HomePageTests signUpAndLogin() {
		driver.get("http://localhost:" + this.port + "/signup");
		SignUpTests newUser = new SignUpTests(driver);
		newUser.setFirstName("Nguyen");
		newUser.setLastName("Thang");
		newUser.setUserName("wingo");
		newUser.setPassword("123456");
		newUser.signUp();
		driver.get("http://localhost:" + this.port + "/login");
		
		SignInTests login= new SignInTests(driver);
		login.setUserName("wingo");
		login.setPassword("123456");
		login.login();

		return new HomePageTests(driver);
	}

}
