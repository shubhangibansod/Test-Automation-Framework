package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public BrowserUtility(WebDriver driver) {
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(String browserName) {
		logger.info("Lauching browser for " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else {
			System.err.println("Invalid browser name.Please select chrome or edge");
			logger.error("Invalid browser name.Please select chrome or edge");
		}
	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		} else {
			System.err.println("Invalid browser name.Please select chrome or edge");
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadLess) {
		if (browserName == Browser.CHROME) {
			if (isHeadLess) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless=old");
				chromeOptions.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(chromeOptions));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadLess) {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless=old");
				edgeOptions.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(edgeOptions));
			} else {

				driver.set(new EdgeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadLess) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disable-gpu");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}
		} else {
			System.err.println("Invalid browser name.Please select chrome or edge");
		}
	}

	public void goToWebsite(String i) {

		driver.get().get(i);
	}

	public void maximizeWindow() {
		driver.get().manage().window().maximize();
		logger.info("Mximized the window");
	}

	public void clickOn(By locator) {
		WebElement signInLinkWebElement = driver.get().findElement(locator);
		logger.info("Finding the elemnt with locator " + locator);
		signInLinkWebElement.click();

	}

	public void enterText(By locator, String text) {
		logger.info("Finding the elemnt with locator " + locator);
		WebElement emailTextBoxWebElement = driver.get().findElement(locator);
		logger.info("Element found and now enter text " + text);

		emailTextBoxWebElement.sendKeys(text);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding the elemnt with locator " + locator);
		WebElement userName = driver.get().findElement(locator);
		return userName.getText();
	}

	public String takeScreenshot(String name) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = simpleDateFormat.format(date);
		String path = System.getProperty("user.dir") + "//screenshot//" + name + "-" + timeStamp + ".png";
		File screenShotFile = new File(path);
		FileUtils.copyFile(screenshotData, screenShotFile);
		return path;
	}

	public void Quit() {
		driver.get().quit();
	}
}
