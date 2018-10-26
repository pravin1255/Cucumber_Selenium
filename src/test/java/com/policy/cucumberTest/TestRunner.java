package com.policy.cucumberTest;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;

import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import com.policy.Utility.Report;
import com.policy.Utility.UIMapper;
import com.policy.Utility.WriteLog;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;



@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.policy.stepDefinition"},
		format={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html", "pretty"},		
		//format={WriteLog.createReportingFolder("com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports.html")},
		monochrome=true,
		tags={"~@Policy","~@RegressionTest","~@flipkartTest","@BackgroundColor"}
		)
public class TestRunner {

	@AfterClass
	public static void writeExtentReport() throws IOException
	{
		/*Reporter.loadXMLConfig(new File(System.getProperty("user.dir")
				+ UIMapper.getValue("reportConfigPath")));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "32 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");	
		Reporter.setSystemInfo("Java Version", "1.8.0_151");*/
		WriteLog.createReportingFolder("target/cucumber-reports/report.html");
	}
	
	
	
}