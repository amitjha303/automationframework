package com.automation.testcases;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.base.CsvDataProvider;
import com.automation.pages.SurveyEditorPage;
import com.automation.pages.SurveyNamePage;
import com.automation.pages.UserDashboardPage;

public class CreateSurveyTemplateTest extends BaseTest {

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 1, groups = {"create1"})
	public void createSurvey(Map<String, String> testData) {
		String surveyName = testData.get("surveyName");
		
		UserDashboardPage userDashboardPage = new UserDashboardPage(driver, log, prop, config, jse);
		
		//Click on create survey link
		SurveyNamePage surveyNamePage = userDashboardPage.clickCreateTemplateLink();
		surveyNamePage.waitForSurveyNamePageToLoad();
		
		//Click on get started button
		surveyNamePage.clickGetStartedButton();
		surveyNamePage.waitForSurveyNameFieldToLoad();
		
		//Enter Survey Name
		surveyNamePage.enterSurveyName(surveyName);
		
		//Click on create survey button
		SurveyEditorPage surveyEditorPage = surveyNamePage.clickCreateButton();
		surveyEditorPage.waitForSurveyEditorToLoad();
		
		//Verify survey is created successfully
		String surveyHeaderName = surveyEditorPage.getSurveyName();
		Assert.assertTrue(surveyHeaderName.equals(surveyName), "Something went wrong in survey creation");
		
		//Navigate back to user dashboard page
		userDashboardPage = surveyEditorPage.clickHomeLink();
		userDashboardPage.waitForUserDashboardToLoad();
	}
}

