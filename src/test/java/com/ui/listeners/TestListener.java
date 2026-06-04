package com.ui.listeners;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentRepoterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentRepoterUtility.createExtentTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		ExtentRepoterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");

	}

	public void onTestFailure(ITestResult result) {

		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentRepoterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentRepoterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		Object testClass = result.getInstance();
			BrowserUtility browserUtility = ((TestBase)testClass).getInstance();
			logger.info("Capturing screnshotfor failed test cases");
			try {
				String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
				logger.info("Attaching the screenshot to the html file");
				ExtentRepoterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		ExtentRepoterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");

	}

	public void onStart(ITestContext context) {
		logger.info("Test SUITE STARTED");
		ExtentRepoterUtility.setupSparkResporter("report.html");

	}

	public void onFinish(ITestContext context) {
		logger.info("Test SUITE COMPLETED");
		ExtentRepoterUtility.flushReport();
	}
}
