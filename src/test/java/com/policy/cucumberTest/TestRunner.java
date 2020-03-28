package com.policy.cucumberTest;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;


//import com.cucumber.listener.ExtentProperties;//uncomment for new extent report
import com.policy.Utility.UIMapper;
import com.policy.Utility.WriteLog;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
/*
 * This Cucumber options generates the report in target/cucumber-reports/report.html folder
 * To generate this report in pom.xml uncomment the "This is OLD DEPENDENCY" dependencies
 * 
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.policy.stepDefinition"},
		format={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html", "pretty"},
		monochrome=true,
		tags={"~@Policy,~@AddPolicy","@GetPolicyDetails","~@changePolicyAmt","~@RegressionTest","~@BuyNow","~@Gmailapproval","~@BuyNow","~@FileUpload"}
		)
public class TestRunner {	
	
	@AfterClass
	public static void writeExtentReport() throws IOException
	{
		
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")
				+ UIMapper.getValue("reportConfigPath2")));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "32 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");	
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
		Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
	}
}


/*
 * This Cucumber options generates the report in our desired folder
 * To generate this report in pom.xml uncomment the "This is NEW DEPENDENCY" dependencies
 * 
 */

/*@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		//features="Feature/AmazonTest.feature",
		glue={"com.policy.stepDefinition"},
		format={"com.cucumber.listener.ExtentCucumberFormatter:", "pretty"},
		monochrome=true,
		tags={"@Policy","@AddPolicy","~@RegressionTest","~@flipkartTest","~@BackgroundColor","~@Gmailapproval","~@WishList"}
		//tags={"~@Login","~@AddtoCart","~@ShoesPage","~@MoveToTop","~@BuyNow","~@FileDownload3Users","~@FileUploadValidation"}
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
				+ UIMapper.getValue("reportConfigPath2")));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "32 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");	
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
		WriteLog.copyScreenshot();
	}
}*/