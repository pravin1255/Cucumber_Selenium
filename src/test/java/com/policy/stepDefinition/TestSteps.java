package com.policy.stepDefinition;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import com.cucumber.listener.Reporter;

import static com.policy.Utility.Constant.driver;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import com.policy.Utility.Normal_Methods;
import com.policy.Utility.UIMapper;
import com.policy.cucumberTest.ChromeDriverEx;
import com.relevantcodes.extentreports.ExtentTest;

//my new step on 15-10
public class TestSteps extends Normal_Methods 
{
	@Before
	public void loadProperties() throws IOException
	{
		String fileName=System.getProperty("user.dir")+"//element.properties";
		UIMapper ui=new UIMapper(fileName);
	}
	
	public String testCaseName="";
	String health;	
	String proceed;
	
	@Given("^User opens the browser$")
	public void user_opens_the_browser() throws Throwable {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-infobars");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//com/policy//resources//chromedriver.exe");
	    driver=new ChromeDriverEx(options);      
	}

	@Given("^opens the policy bazar site$")
	public void opens_the_policy_bazar_site() throws Throwable {
		 driver.get("https://www.policybazaar.com");
	     driver.manage().window().maximize();
	     
	     String capture=Normal_Methods.capture(driver, "Policy bazar page");
		 Reporter.addScreenCaptureFromPath(capture, "Policy bazar Page");
	}	

	@When("^clicks on proceed button$")
	public void clicks_on_proceed_button() throws Throwable {
	
	    waitAndDoActionXpath(UIMapper.getValue("proceed"));
		//Normal_Methods.waitUntilElementIsClickable(proceed, 35);
		//driver.findElement(By.cssSelector("span:contains('Proceed')")).click();
	}

	@When("^User searches search text on search bar$")
	public void user_searches_search_text_on_search_bar() throws Throwable {
	    
	}

	@When("^clicks on search button$")
	public void clicks_on_search_button() throws Throwable {
		String searchText = UIMapper.getValue("searchText");

		waitAndType(searchText, "Religare");

		String searchicon = UIMapper.getValue("searchicon");

		waitAndDoActionXpath(searchicon);
	}

	@When("^User clicks on View All Feature Details$")
	public void user_clicks_on_View_All_Feature_Details() throws Throwable {
		String viewAll1 = "//*[text()='+ View All Feature Details']";

		waitToVisible(viewAll1);

		// Normal_Methods.waitToVisibleElements(viewAll1);
		List<WebElement> viewAll = driver.findElements(By.xpath("//*[text()='+ View All Feature Details']"));

		for (WebElement view : viewAll) {
			System.out.println("The 1st view is " + view.getText());
			view.click();
			break;
		}
	}	
	
	@When("^user fills all the details \"(.*?)\"$")
	public void user_fills_all_the_details(String arg1) throws Throwable {

		testCaseName = arg1;
		readDataFromExcel("Data");

		waitAndDoActionXpath(UIMapper.getValue("health"));

		String country = UIMapper.getValue("country");

		waitAndDoActionXpath(country);

		String country1 =UIMapper.getValue("country1");

		// String value = "Angola";

		dropDownSelect(country1, accessTestData(testCaseName, "Country"));

		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//*[@role='dialog']//*[@style='display: block;']//span"));

			ele.get(1).click();
		} catch (Exception e) {
			System.out.println(" is not present");
		}
		String tel =UIMapper.getValue("tel");

		waitAndType(tel, accessTestData(testCaseName, "MobileNo"));

		String city =UIMapper.getValue("city");

		waitAndDoActionXpath(city);

		enterTextinAutoCompletion(city, accessTestData(testCaseName, "City"));

		String selfCheck = UIMapper.getValue("selfCheck");

		waitAndDoActionXpath(selfCheck);

		String age =UIMapper.getValue("age");

		dropDownSelect(age, accessTestData(testCaseName, "Age"));
		
		String capture=Normal_Methods.capture(driver, "Policy bazar page fill");
		Reporter.addScreenCaptureFromPath(capture, "Details filled Page");
	}
	
	@When("^User enters the next page \"(.*?)\"$")
	public void user_enters_the_next_page(String arg1) throws Throwable {

		testCaseName = arg1;

		String title =UIMapper.getValue("title");

		waitAndDoActionXpath(title);

		dropDownSelect(title, accessTestData(testCaseName, "Title"));

		String selfName =UIMapper.getValue("selfName");

		waitAndType(selfName, accessTestData(testCaseName, "Name"));

		String email = UIMapper.getValue("email");

		waitAndType(email, accessTestData(testCaseName, "Email"));

		WebElement medicalCondition = driver.findElement(By.xpath("//*[@value='1' and @role='radio']"));

		highlight(medicalCondition);

		String b1 = medicalCondition.getAttribute("aria-checked");

		System.out.println("b1 " + b1);

		if (!b1.equalsIgnoreCase("false")) {
			System.out.println("Entered loop");
			driver.findElement(By.xpath("//*[@class='md-container']")).click();
		} else {
			System.out.println("entered in else ");
		}
		
		String capture=Normal_Methods.capture(driver, "Policy bazar page fill2");
		Reporter.addScreenCaptureFromPath(capture, "Details filled Page 2");
	}
	
	@Given("^opens the mddx site$")
	public void opens_the_mddx_site() throws Throwable {
		driver.get("http://t.mddximage.com");
	    driver.manage().window().maximize();
	    /*String capture=Normal_Methods.capture(driver, "MDDX Page");
	    Reporter.addScreenCaptureFromPath(capture, "MDDX Page");*/
	}

	@When("^clicks on signin button and gets the last login detail$")
	public void clicks_on_signin_button_and_gets_the_last_login_detail() throws Throwable {
		waitAndDoActionXpath(UIMapper.getValue("submit"));
		
		//String capture=Normal_Methods.capture(driver, "MDDX LOGIN page");
		String capture=Normal_Methods.captureFullScreen("MDDX LOGIN page");
	    Reporter.addScreenCaptureFromPath(capture, "LOGIN Page");
	    
		waitUntilElementIsClickable(UIMapper.getValue("notification"), 30);
		
		//String capture2=Normal_Methods.capture(driver, "Notification Page");
		String capture2=Normal_Methods.captureFullScreen("Notification Page");
	    Reporter.addScreenCaptureFromPath(capture2, "Notification Page");
	    
		String loginDetails=driver.findElement(By.xpath(UIMapper.getValue("notification"))).getText();
		
		System.out.println("Login Details "+loginDetails);
	}
	
	@When("^user fills the login details\"(.*?)\"$")
	public void user_fills_the_login_details(String arg1) throws Throwable {
		testCaseName = arg1;
		readDataFromExcel("Login");
		
		waitAndType(UIMapper.getValue("username"), accessTestData(testCaseName, "Username"));
		
		waitAndType(UIMapper.getValue("password"), accessTestData(testCaseName, "Password"));	
		
		//String capture=Normal_Methods.capture(driver, "LOGIN Details page");
		String capture=Normal_Methods.captureFullScreen("LOGIN Details page");
	    Reporter.addScreenCaptureFromPath(capture, "LOGIN Details Page");
	}
	
	@When("^User logout from application$")
	public void user_logout_from_application() throws Throwable {
	    
		waitAndDoActionXpath(UIMapper.getValue("logout"));
		
		//String capture=Normal_Methods.capture(driver, "MDDX LOGOUT page");
		String capture=Normal_Methods.captureFullScreen("MDDX LOGOUT page");
	    Reporter.addScreenCaptureFromPath(capture, "LOGOUT Page");
	}
	
	@Given("^opens flipkart site$")
	public void opens_flipkart_site() throws Throwable {
	    
		driver.get("https://www.flipkart.com/");
	    driver.manage().window().maximize();	    
	}

	@When("^user login to site \"(.*?)\"$")
	public void user_login_to_site(String arg1) throws Throwable {
	    
		testCaseName = arg1;
		readDataFromExcel("Flipkart");
		
		waitAndType(UIMapper.getValue("usernameFlip"), accessTestData(testCaseName, "Username"));
		Reporter.addStepLog("Username is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(testCaseName, "Username")+"</font>");
	
		waitAndType(UIMapper.getValue("passFlip"), accessTestData(testCaseName, "Password"));
		Reporter.addStepLog("Password is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(testCaseName, "Password")+"</font>");
		
		String capture=Normal_Methods.capture(driver, "sign in 2");
	    Reporter.addScreenCaptureFromPath(capture, "Sign in 2 Page");
	    
	    Reporter.addStepLog("Password is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"Logged in FlipKART"+"</font>");
	    
	    waitAndDoActionXpath(UIMapper.getValue("submitFlip"));
	}

	@When("^search for iphone \"(.*?)\"$")
	public void search_for_iphone(String arg1) throws Throwable {
	    waitAndType(UIMapper.getValue("searchBox"), "Iphone");
	    
	    waitAndDoActionXpath(UIMapper.getValue("searchIcon"));	    
	}



	@When("^gets the first item amount$")
	public void gets_the_first_item_amount() throws Throwable {
	    
		Thread.sleep(3000);
		List<WebElement> ele=driver.findElements(By.xpath(UIMapper.getValue("result")));
		for(WebElement e:ele)
		{
			System.out.println("The amount of first item "+e.getText());
			Reporter.addStepLog("The amount of first item "+e.getText());
			
			String capture=Normal_Methods.capture(driver, "image screen");
		    Reporter.addScreenCaptureFromPath(capture, "image screen");
		    e.click();
		    
			break;
		}
	}
	
	@When("^search for product \"(.*?)\"$")
	public void search_for_product(String arg1) throws Throwable {
	    testCaseName=arg1;
	    
	    readDataFromExcel("Flipkart");
	    
	    waitAndType(UIMapper.getValue("searchBox"), accessTestData(testCaseName, "Product"));
	    
	    Reporter.addStepLog("Searched item is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(testCaseName, "Product")+"</font>");
	    waitAndDoActionXpath(UIMapper.getValue("searchIcon"));
	    
		String capture=Normal_Methods.capture(driver, "Product");
	    Reporter.addScreenCaptureFromPath(capture, "Product");
	}

	@When("^clicks on first product$")
	public void clicks_on_first_product() throws Throwable {
		
		Thread.sleep(3000);
	    List<WebElement> ele=driver.findElements(By.xpath(UIMapper.getValue("product")));
	    
	    for(WebElement e:ele)
	    {
	    	if(e.getAttribute("text").equals("Samsung Galaxy On8 (Blue, 64 GB)"))
	    	{
	    		e.click();
	    		Reporter.addStepLog("Product is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"Samsung Galaxy On8 (Blue, 64 GB)"+"</font>");
	    		break;
	    	}
	    }
	    
		String capture=Normal_Methods.capture(driver, "First product");
	    Reporter.addScreenCaptureFromPath(capture, "First product");
	}

	@When("^browser switch to new tab$")
	public void browser_switch_to_new_tab() throws Throwable {
	  
		switchToNewTab();	    
		Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"Switched to new tab"+"</font>");
		String capture=Normal_Methods.capture(driver, "New Tab");
	    Reporter.addScreenCaptureFromPath(capture, "New Tab");
	}

	@When("^user enters the pin code$")
	public void user_enters_the_pin_code() throws Throwable {
		Thread.sleep(3000);
	    waitAndType(UIMapper.getValue("pincode"), accessTestData(testCaseName, "PinCode"));
		Reporter.addStepLog("Entering Pincode <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(testCaseName, "PinCode")+"</font>");
	    waitAndDoActionXpath(UIMapper.getValue("check"));
	    waitToVisible(UIMapper.getValue("buyNow"));
	    getBackgroundColor(UIMapper.getValue("buyNow"));
	    
	    String capture=Normal_Methods.capture(driver, "Color");
	    Reporter.addScreenCaptureFromPath(capture, "Color");
	}
}  