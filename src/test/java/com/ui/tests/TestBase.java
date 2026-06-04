package com.ui.tests;

import static com.constants.Browser.CHROME;

import java.net.MalformedURLException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	private boolean isLambdaTest;
	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Parameters({"browser","isLambdaTest","isHeadLess"})
	@BeforeMethod(description = "Load the homepage of the website")
	public void setUp(String browser,boolean isLambdaTest , boolean isHeadLess,ITestResult result) throws MalformedURLException {
		WebDriver lambdaDriver;
		if (isLambdaTest) {
			this.isLambdaTest = isLambdaTest;
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);

		} else {

			// running the test in local machine
			logger.info("Load the homepage of the website");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess);
		}
	}

	public BrowserUtility getInstance() {
		return homePage;

	}
	
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession();
		}else {
			homePage.Quit();
		}
		
	}
	
}
