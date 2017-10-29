package com.automation.pages;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.automation.base.BasePageObject;

public class LogInPage extends BasePageObject<LogInPage>{
	private By usernameField = By.xpath(prop.getProperty("usernameField"));
	private By passwordField = By.xpath(prop.getProperty("passwordField"));
	private By clientField = By.xpath(prop.getProperty("clientField"));
	private By signInButton = By.xpath(prop.getProperty("signInButton"));
	private By errorMessage = By.xpath(prop.getProperty("loginErrorMessage"));
	
	public LogInPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}
	
	public void openPage() {
		log.info("Navigating to " + config.getProperty("testEnv") + " " + "URL");
		getPage(config.getProperty("env"));
	}
	
	public void enterUsernamePasswordClient(String username, String password, String client) {
		log.info("Entering login details");
		typeInput(username, usernameField);
		typeInput(password, passwordField);
		typeInput(client, clientField);
	}
	
	public UserDashboardPage clickSignInButton() {
		log.info("Siging In");
		click(signInButton);
		return new UserDashboardPage(driver, log, prop, config, jse);
	}

	public String getLogInErrorMessage() {
		log.info("Waiting for error message");
		waitForVisibilityOfElement(errorMessage);
		return getText(errorMessage);
	}
}
