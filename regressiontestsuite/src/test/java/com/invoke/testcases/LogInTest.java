package com.invoke.testcases;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.invoke.base.BaseTest;
import com.invoke.base.CsvDataProvider;
import com.invoke.pages.LogInPage;
import com.invoke.pages.UserDashboardPage;

public class LogInTest extends BaseTest {

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = {"login"})
	public void positiveLogInTest(Map<String, String> testData) {
		String expectedPageTitle = testData.get("PageTitle");
		String correctUserName = testData.get("User");
		String userName = testData.get("Username");
		String password = testData.get("Password");
		String client = testData.get("Client");
		
		LogInPage logInPage = new LogInPage(driver, log, prop, config, jse);

		// Open Invoke url - http://qa1.app.invoke.com/a/ui/login
		logInPage.openPage();

		// Enter username, passowrd and client
		logInPage.enterUsernamePasswordClient(userName, password, client);

		// Click on Sign In button
		UserDashboardPage userDashboard = logInPage.clickSignInButton();
		userDashboard.waitForUserDashboardToLoad();

		// Verifications
		// - Verify title of the page is correct - Invoke®
		log.info("Verifying logged in user details");
		String actualPageTitle = userDashboard.getTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualPageTitle),
				"Page title is not matching.\nExpected: " + expectedPageTitle + "\nActual: " + actualPageTitle + ".");

		// - Verify correct user name on profile page
		Assert.assertTrue(userDashboard.isCorrectUserDashboardLoaded(correctUserName), "Username is not matching.");
	}

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 1, groups = {"verify"})
	public void negativeLogInTest(Map<String, String> testData) {
		String invalidUsernamePasswordMessage = testData.get("InvalidUsernamePasswordMessage");
		String invalidClientMessage = testData.get("InvalidClientMessage");
		String testNumber = testData.get("No");
		String userName = testData.get("Username");
		String password = testData.get("Password");
		String client = testData.get("Client");
		String description = testData.get("Description");

		log.info("Test No #" + testNumber + " for " + description + " Where\nUsername: " + userName
				+ "\nPassword: " + password + "\nClient: " + client);

		LogInPage logInPage = new LogInPage(driver, log, prop, config, jse);

		// Open Invoke url - http://qa1.app.invoke.com/a/ui/login
		logInPage.openPage();
		
		// Enter username, passowrd and client
		logInPage.enterUsernamePasswordClient(userName, password, client);

		// Click on Sign In button
		logInPage.clickSignInButton();

		// Verifying error message for incorrect credentials
		String actualErrorMessage = logInPage.getLogInErrorMessage();
		
		if(actualErrorMessage.contains("domain")) {
			Assert.assertTrue(actualErrorMessage.contains(invalidClientMessage),
					"Validation message is not matching.\nExpected: " + invalidClientMessage + "\nActual: "
							+ actualErrorMessage + ".");
		} else {
			Assert.assertTrue(actualErrorMessage.contains(invalidUsernamePasswordMessage),
					"Validation message is not matching.\nExpected: " + invalidUsernamePasswordMessage + "\nActual: "
							+ actualErrorMessage + ".");
		}
	}
}
