package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.constants.Env;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

public final class  HomePage extends BrowserUtility {


	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public HomePage(Browser browserName,boolean isHeadless ) {
		super(browserName,isHeadless);   //to call parent class constructor from the child class constructor
		//goToWebsite(readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJson(Env.QA).getUrl());
		logger.info("Launched the website");
		maximizeWindow();
	}
	
	

	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readJson(Env.QA).getUrl());
	}



	public LoginPage goToLoginPage() {//page class function does not have oid as return type
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
	
	
}