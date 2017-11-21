package com.automation.testcases;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import com.automation.base.CsvDataProvider;
import com.automation.pages.LogInPage;
import com.automation.pages.UserDashboardPage;

public class LogInTest extends BaseTest {

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 1, groups = {"login"})
	public void positiveLogInTest(Map<String, String> testData) {
		String expectedPageTitle = testData.get("PageTitle");
		String userName = testData.get("Username");
		String password = testData.get("Password");
		
		LogInPage logInPage = new LogInPage(driver, log, prop, config, jse);

		// Open Invoke url - http://qa1.app.invoke.com/a/ui/login
		logInPage.openPage();

		// Enter username, passowrd and client
		logInPage.enterUsernamePassword(userName, password);

		// Click on Sign In button
		UserDashboardPage userDashboard = logInPage.clickSignInButton();
		userDashboard.waitForUserDashboardToLoad();

		// Verifications
		// - Verify title of the page is correct - Invoke®
		log.info("Verifying logged in user details");
		String actualPageTitle = userDashboard.getDashboardPageTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualPageTitle),
				"Page title is not matching.\nExpected: " + expectedPageTitle + "\nActual: " + actualPageTitle + ".");
	}
}
