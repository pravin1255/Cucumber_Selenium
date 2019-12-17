package com.policy.Utility;

import org.openqa.selenium.WebDriver;

import com.policy.cucumberTest.ChromeDriverEx;

public class Constant {
	
	public static WebDriver driver1;
	public static long wait=5;
	public static String screehsotPath=System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
	public static String rootDir=System.getProperty("user.dir");
	public static String reportPath=rootDir+"\\report\\";
	public static ChromeDriverEx driver;	
}