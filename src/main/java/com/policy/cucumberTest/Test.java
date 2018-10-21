package com.policy.cucumberTest;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeOptions;


public class Test {
	public static void main(String args[]) throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-infobars");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//com/policy//resources//chromedriver.exe");
		ChromeDriverEx driver = new ChromeDriverEx(options);
		driver.manage().window().maximize();
		driver.get("https://in.yahoo.com/?p=us");
		File file = driver.getFullScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("F:///FullPageScreenshot3.png"));
	}
}