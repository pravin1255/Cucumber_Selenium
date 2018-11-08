package com.policy.stepDefinition;

import com.cucumber.listener.Reporter;
import com.policy.Utility.Normal_Methods;
import com.policy.Utility.UIMapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static com.policy.Utility.Constant.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class amazonSteps extends Normal_Methods{

	String capture="";
	
	@Given("^User opens the web page \"(.*?)\"$")
	public void user_opens_the_web_page(String arg1) throws Throwable {
		
		String testcaseName=arg1;
		
		readDataFromExcel("Amazon");
		
		driver.manage().window().maximize();
		driver.get(accessTestData(testcaseName, "URL"));	
		
		String capture=Normal_Methods.capture(driver, "Web page");
		Reporter.addScreenCaptureFromPath(capture, "Web Page");
		
		Reporter.addStepLog("Web page opened is<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(testcaseName, "URL")+"</font>");
	}
	
	@Given("^navigates to Sign in link and clicks on signin button$")
	public void navigates_to_Sign_in_link_and_clicks_on_signin_button() throws Throwable {
	
		mouseHover(UIMapper.getValue("signINamazon"), UIMapper.getValue("signinc"));
		String capture=Normal_Methods.capture(driver, "Sign in page");
		Reporter.addScreenCaptureFromPath(capture, "Sign in Page");
		
	}

	@Given("^enters username and password \"(.*?)\"$")
	public void enters_username_and_password(String arg1) throws Throwable {
	
		waitAndTypeCss(UIMapper.getValue("usernameA"), accessTestData(arg1, "Username"));
		
		waitAndDoActionCss(UIMapper.getValue("continueA"));
		
		capture=Normal_Methods.capture(driver, "Username page");
		Reporter.addScreenCaptureFromPath(capture, "Username Page");
		Reporter.addStepLog("Username is<font style=\"color:white;background-color:rgb(251, 100, 27);\">"
				+ accessTestData(arg1, "Username") + "</font>");
		
		waitAndTypeCss(UIMapper.getValue("passwordA"), accessTestData(arg1, "Password"));
		
		waitAndDoActionCss(UIMapper.getValue("loginA"));
		
		capture=Normal_Methods.capture(driver, "Password page");
		Reporter.addScreenCaptureFromPath(capture, "Password Page");
		
		Reporter.addStepLog("Password is<font style=\"color:white;background-color:rgb(251, 100, 27);\">"
				+ accessTestData(arg1, "Password") + "</font>");
		
	}
	
	@Given("^user verifies the logged in User \"(.*?)\"$")
	public void user_verifies_the_logged_in_User(String arg1) throws Throwable {
	    
		String uiText=driver.findElement(By.cssSelector(UIMapper.getValue("nameA"))).getText();
		
		assertionCheck(accessTestData(arg1, "Username"), uiText);
		
		capture=Normal_Methods.capture(driver, "Logged in user page");
		Reporter.addScreenCaptureFromPath(capture, "Logged in user Page");
	}
	
	@When("^user clicks on home link$")
	public void user_clicks_on_home_link() throws Throwable {
	 
		waitAndDoActionCss(UIMapper.getValue("homeA"));
		capture=Normal_Methods.capture(driver, "Home link");
		Reporter.addScreenCaptureFromPath(capture, "Home link");
	}

	@When("^navigate back to previous page$")
	public void navigate_back_to_previous_page() throws Throwable {
	    
		back();
	}

	@When("^clicks on Today's Deals$")
	public void clicks_on_Today_s_Deals() throws Throwable {
	    
		waitAndDoActionCss(UIMapper.getValue("todaysDeal"));
		capture=Normal_Methods.capture(driver, "Todays Deal");
		Reporter.addScreenCaptureFromPath(capture, "Todays Deal");
	}
	
	@When("^user searches item in search bar \"(.*?)\"$")
	public void user_searches_item_in_search_bar(String arg1) throws Throwable {
	    
		waitAndDoActionCss(UIMapper.getValue("searchBarA"));
		waitAndTypeCss(UIMapper.getValue("searchBarA"), accessTestData(arg1, "Product"));
		waitAndDoActionCss(UIMapper.getValue("searchButtonA"));
		capture=Normal_Methods.capture(driver, "Search Bar");
		Reporter.addScreenCaptureFromPath(capture, "Search Bar");
		Reporter.addStepLog("Searched for <font style=\"color:white;background-color:rgb(251, 100, 27);\">"
				+ accessTestData(arg1, "Product") + "</font>");
	}

	
	//In this method we have used parent element  and than child element
	@When("^open the item and does a zoom in of image displayed$")
	public void open_the_item_and_does_a_zoom_in_of_image_displayed() throws Throwable {
	    
		List<WebElement> elements=driver.findElements(By.xpath(UIMapper.getValue("resultA")));
		
		for(WebElement element:elements)
		{	
			//element.findElement(By.xpath("//child::a//h2")).click();// By using Xpath method
			element.findElement(By.cssSelector("a h2")).click();// By using css selector method
			
			capture=Normal_Methods.capture(driver, "1st item");
			Reporter.addScreenCaptureFromPath(capture, "1st item");
			Reporter.addStepLog("Clicked on first item <font style=\"color:white;background-color:rgb(251, 100, 27);\">"
					+ accessTestData("TC1", "Product") + "</font>");
			break;
		}
		
		switchToNewTab();
		
		List<WebElement> elements1=driver.findElements(By.cssSelector("[class$='imageThumbnail a-declarative']"));
		
		int i=1;
		for(WebElement element:elements1) 
		{
			System.out.println("EnterED FOR LOOP");
			element.findElement(By.cssSelector("[class$='a-button-focus']")).click();
			
			capture=Normal_Methods.capture(driver, "1st item UI"+i);
			Reporter.addScreenCaptureFromPath(capture, "1st item UI"+i);
			Reporter.addStepLog("Clicked on first item image<font style=\"color:white;background-color:rgb(251, 100, 27);\">"
					+ accessTestData("TC1", "Product") + "</font>");
		//	break;
			i++;
			continue;
		}
		waitAndDoActionCss(UIMapper.getValue("itemA"));
		capture=Normal_Methods.capture(driver, "Zoom UI");
		Reporter.addScreenCaptureFromPath(capture, "Zoom UI");
	}
}