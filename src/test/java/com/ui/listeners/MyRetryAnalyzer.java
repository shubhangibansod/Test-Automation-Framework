package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	//static final private int MAX_NO_OF_ATTEMPTS=Integer.parseInt(PropertiesUtil.readProperty(Env.DEV, "MAX_NO_OF_ATTEMPTS"));
	static final private int MAX_NO_OF_ATTEMPTS = JSONUtility.readJson(Env.QA).getMAX_NO_OF_ATTEMPTS();
	private static int currentAttempt=1;
	
	public boolean retry(ITestResult result) {
		
		if(currentAttempt<=MAX_NO_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}

}
