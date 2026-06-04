package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase {
	
	/* Test method
	 * Test script small
	 * You  can not have conditional statemnt,looptry catch in your test method
	 * Test script--->test step
	 * reduce the use of local var
	 * at least on assertion
	 * 
	 */
	

	


	@Test(description = "Verifies the valid user is able to login to the application", groups = {"e2e","sanity"},
		dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,  dataProvider = "LoginTestDataProvider")
	public  void LoginJsonTest(User user) {
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(), "Shubhangi Bansod");
		
	}
	
	@Test(description = "Verifies the valid user is able to login to the application", groups = {"e2e","sanity"},
			dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,  dataProvider = "LoginTestCSVDataProvider",
			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class
			)
		public  void LoginCSVTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(),user.getPassword()).getUserName(), "Shubhangi Bansod");
		}
}
