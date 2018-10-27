package com.policy.cucumberTest;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.policy.Utility.UIMapper;
import com.policy.Utility.WriteLog;
import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.policy.stepDefinition"},
		format={"com.cucumber.listener.ExtentCucumberFormatter:", "pretty"},
		monochrome=true,
		tags={"~@Policy","~@RegressionTest","~@flipkartTest","@BackgroundColor"}
		)
public class TestRunner {
	
	@BeforeClass
	public static void setup() {		
	    ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	    String folderLocation=WriteLog.createReportingFolder();
	    extentProperties.setReportPath("target//OUTPUT_HTML//"+folderLocation+"//"+"report.html");    
	}

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
		WriteLog.copyScreenshot();
	}
}