package com.policy.Utility;

import java.io.IOException;
import java.util.Date;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Report {
	
	static ExtentReports report;
	static ExtentTest test,testChild;
	static ExtentTest parentTest;
	static ExtentTest test1=new ExtentTest("", "");
	public static ExtentReports createNewReport(String reportName){
		report = new ExtentReports(Constant.reportPath+reportName + ".html",true);
		return report;
	}
	
	public static ExtentTest createTest(String testName){
		ExtentTest newTest = report.startTest(testName);
		setTest(newTest);
		return newTest;
	}
	
	public static void setTest(ExtentTest test){
		Report.test = test;
	}
	
	public static void setChildTest(ExtentTest test){
		parentTest=Report.test;
		Report.test = test;
	}
	
	public static ExtentTest createChilTest(String testName){
		ExtentTest childTest = report.startTest(testName);
		test.appendChild(childTest);
		setChildTest(childTest);
		return childTest;
	}
	
	public static ExtentTest createChilTest2(String testName){
		createNewReport(testName);
		ExtentTest childTest = report.startTest(testName);
		//test.appendChild(childTest);
		setChildTest(childTest);
		return childTest;
	}
	
	public static ExtentTest createChildTest(String testName){
		ExtentTest newTest = report.startTest(testName);
		//setTest(newTest);
		return newTest;
	}
	
	public static void logInfo(String detail){
		test.log(LogStatus.INFO, detail); 
	}
	
	public static void logInfo2(String detail){
		test1.log(LogStatus.INFO, detail); 
	}
	
	public static void logPass(String detail) throws IOException{
		test.log(LogStatus.PASS, detail); 
		captureScreenshot();
	}
	
	public static void logFail(Exception e) throws IOException {
		test.log(LogStatus.FAIL, e);
		captureScreenshot();
	}
	
	public static void logFail(String detail) throws IOException {
		test.log(LogStatus.FAIL, detail);
		captureScreenshot();
	}
	
	public static void logError(String detail) throws IOException{
		test.log(LogStatus.ERROR, detail); 
		captureScreenshot();
	}
	
	public static void logWarning(String detail) throws IOException{
		test.log(LogStatus.WARNING, detail); 
		captureScreenshot();
	}
	
	public static void captureScreenshot() throws IOException{
		//String screenshot = UtilsClass.capture(driver, "screenShotName" + new Date().getTime()); 
		//test.log(LogStatus.INFO, test.addScreenCapture(screenshot)); 
	}
	
	public static void flushAndCloseReport(){
		report.flush();
		report.close();
	}
	
	public static void endChildTest() {
		report.endTest(test);
		Report.test = Report.parentTest;
	}
	
	public static void endTest() {
		report.endTest(test);
	}
	
	
}