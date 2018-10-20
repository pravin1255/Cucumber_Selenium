package com.policy.Utility;

import org.openqa.selenium.WebDriver;

public class Constant {
	
	public static WebDriver driver;
	public static long wait=30;
	public static String screehsotPath=System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/";
	public static String rootDir=System.getProperty("user.dir");
	public static String reportPath=rootDir+"\\report\\";
}