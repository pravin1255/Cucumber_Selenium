package com.policy.cucumberTest;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;

import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import com.policy.Utility.UIMapper;

import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.policy.stepDefinition"},
		format={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html", "pretty"},		
		monochrome=true,
		tags={"@RegressionTest"}
		)
public class TestRunner {

	@AfterClass
	public static void writeExtentReport() throws IOException
	{
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")
				+ UIMapper.getValue("reportConfigPath")));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "32 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");	
		Reporter.setSystemInfo("Java Version", "1.8.0_151");	
		
	}
}