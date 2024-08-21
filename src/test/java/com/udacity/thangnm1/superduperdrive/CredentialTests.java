package com.udacity.thangnm1.superduperdrive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.udacity.thangnm1.superduperdrive.enitites.Credential;

/**
 * Tests for Credential Creation, Viewing, Editing, and Deletion.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests extends SuperDuperDriveApplicationTests {

	public static final String URL1 = "https://congtroidongiang.com/";
	public static final String USERNAME1 = "thangnm1";
	public static final String PASSWORD1 = "thusuong";
	public static final String URL2 = "https://fvgtravel.com.vn/";
	public static final String USERNAME2 = "wingo";
	public static final String PASSWORD2 = "buxinhdep";

	/**
	 * Creates a set of credentials, displays them
	 * Password is encrypted.
	 */
	@Test
	public void testCredentialCreation() {
		HomePageTests homePage = signUpAndLogin();
		createAndVerifyCredential(URL1, USERNAME1, PASSWORD1, homePage);
		homePage.deleteCredential();
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
		homePage.logout();
	}

	private void createAndVerifyCredential(String url, String username, String password, HomePageTests homePage) {
		createCredential(url, username, password, homePage);
		homePage.navToCredentialsTab();
		Credential credential = homePage.getFirstCredential();
		Assertions.assertEquals(url, credential.getUrl());
		Assertions.assertEquals(username, credential.getUserName());
		Assertions.assertNotEquals(password, credential.getPassword());
	}

	private void createCredential(String url, String username, String password, HomePageTests homePage) {
		homePage.navToCredentialsTab();
		homePage.addNewCredential();
		setCredentialFields(url, username, password, homePage);
		homePage.saveCredentialChanges();
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
		homePage.navToCredentialsTab();
	}

	private void setCredentialFields(String url, String username, String password, HomePageTests homePage) {
		homePage.setCredentialUrl(url);
		homePage.setCredentialUsername(username);
		homePage.setCredentialPassword(password);
	}

	/**
	 * Views an existing set of credentials, 
	 * Password is not encrypted, 
	 * Change the credentials and displays them.
	 */
	@Test
	public void testCredentialModification() {
		HomePageTests homePage = signUpAndLogin();
		createAndVerifyCredential(URL1, USERNAME1, PASSWORD1, homePage);
		Credential originalCredential = homePage.getFirstCredential();
		String firstEncryptedPassword = originalCredential.getPassword();
		homePage.editCredential();
		String newUrl = URL2;
		String newCredentialUsername = USERNAME2;
		String newPassword = PASSWORD2;
		setCredentialFields(newUrl, newCredentialUsername, newPassword, homePage);
		homePage.saveCredentialChanges();
		
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
		homePage.navToCredentialsTab();
		Credential modifiedCredential = homePage.getFirstCredential();
		Assertions.assertEquals(newUrl, modifiedCredential.getUrl());
		Assertions.assertEquals(newCredentialUsername, modifiedCredential.getUserName());
		String modifiedCredentialPassword = modifiedCredential.getPassword();
		Assertions.assertNotEquals(newPassword, modifiedCredentialPassword);
		Assertions.assertNotEquals(firstEncryptedPassword, modifiedCredentialPassword);
		homePage.deleteCredential();
		resultPage.clickOk();
		homePage.logout();
	}

	/**
	 * Deletes the credentials and verifies that they are no longer displayed.
	 */
	@Test
	public void testDeletion() {
		HomePageTests homePage = signUpAndLogin();
		createCredential(URL1, USERNAME1, PASSWORD1, homePage);
		createCredential(URL2, USERNAME2, PASSWORD2, homePage);
		createCredential("http://www.johnlennon.com/", "lennon", "julia", homePage);
		Assertions.assertFalse(homePage.noCredentials(driver));
		homePage.deleteCredential();
		ResultTests resultPage = new ResultTests(driver);
		resultPage.clickOk();
		homePage.navToCredentialsTab();
		homePage.deleteCredential();
		resultPage.clickOk();
		homePage.navToCredentialsTab();
		homePage.deleteCredential();
		resultPage.clickOk();
		homePage.navToCredentialsTab();
		Assertions.assertTrue(homePage.noCredentials(driver));
		homePage.logout();
	}
}
