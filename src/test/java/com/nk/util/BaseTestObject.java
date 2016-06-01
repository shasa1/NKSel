package com.nk.util;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTestObject {

	protected static WebDriver uiDriver;

	public static final String USERNAME = "sam1201";
	public static final String AUTOMATE_KEY = "6ffDpQ95L5SEJqzXot1p";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

	/**
	 * 
	 * This function will execute before each Test tag in testng.xml
	 * 
	 * @param browser
	 * 
	 * @throws Exception
	 * 
	 */

	@BeforeClass
	@Parameters({ "browserType", "url" })
	public void setup(String browser, String url) throws Exception {

		if (browser.equalsIgnoreCase("FF")) {
			uiDriver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("GC")) {
			System.setProperty("webdriver.chrome.driver", "resource/chromedriver.exe");
			uiDriver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\IEdriver.exe");
			uiDriver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("BS")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "48");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "7");
			caps.setCapability("browserstack.debug", "true");
			uiDriver = new RemoteWebDriver(new URL(URL), caps);
		} else {
			throw new Exception("Browser is not correct");
		}
		uiDriver.manage().deleteAllCookies();
		uiDriver.manage().window().maximize();
		Thread.sleep(2000);
		uiDriver.get(url);
		uiDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass
	public void tearDown() {
		uiDriver.quit();
	}
}
