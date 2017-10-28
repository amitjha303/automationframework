package com.invoke.pages;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.invoke.base.BasePageObject;

public class UserDashboardPage extends BasePageObject<UserDashboardPage> {
	private By mySessionsTableHeader = By.xpath(prop.getProperty("mySessionsTableHeader"));
	private By userNameText = By.xpath(prop.getProperty("userNameText"));
	private By projectMenu = By.xpath(prop.getProperty("projectMenu"));
	private By createProjectSubMenu = By.xpath(prop.getProperty("createProjectSubMenu"));
	private By projectlistSubMenu = By.xpath(prop.getProperty("projectlistSubMenu"));
	private By projectLinkTable = By.xpath(prop.getProperty("projectLinkTable"));
	private By surveyMenu = By.xpath(prop.getProperty("surveyMenu"));
	private By createTemplateSubMenu = By.xpath(prop.getProperty("createTemplateSubMenu"));
	private By surveyTemplateListSubMenu = By.xpath(prop.getProperty("surveyTemplateListSubMenu"));

	public UserDashboardPage(WebDriver driver, Logger log, Properties prop, Properties config, JavascriptExecutor jse) {
		super(driver, log, prop, config, jse);
	}
	
	public void waitForUserDashboardToLoad() {
		log.info("Waiting for user dashboard to load");
		waitForVisibilityOfElement(mySessionsTableHeader);
		waitForVisibilityOfElement(projectMenu, 10);
	} 

	public boolean isCorrectUserDashboardLoaded(String correctUserName) {
		if (getText(userNameText).equals(correctUserName)) {
			return true;
		}
		return false;
	}

	public ProjectEditorPage clickCreateProjectLink() {
		clickMouseHoverLink(projectMenu, createProjectSubMenu);
		return new ProjectEditorPage(driver, log, prop, config, jse);
	}

	public ProjectListPage clickProjectlistLink() {
		clickMouseHoverLink(projectMenu, projectlistSubMenu);
		return new ProjectListPage(driver, log, prop, config, jse);
	}

	public ProjectEditorPage clickProjectLinkInTable() {
		click(projectLinkTable);
		return new ProjectEditorPage(driver, log, prop, config, jse);
	}

	public SurveyNamePage clickCreateTemplateLink() {
		log.info("Clicking on Create Template link");
		clickMouseHoverLink(surveyMenu, createTemplateSubMenu);
		return new SurveyNamePage(driver, log, prop, config, jse);
	}

	public SurveyTemplateListPage clickSurveyTemplateList() {
		log.info("Clicking on Survey Template List link");
		clickMouseHoverLink(surveyMenu, surveyTemplateListSubMenu);
		return new SurveyTemplateListPage(driver, log, prop, config, jse);
	}

}
