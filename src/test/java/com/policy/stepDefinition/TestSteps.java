package com.policy.stepDefinition;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import com.cucumber.listener.Reporter;

//import static com.policy.Utility.Constant.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xml.utils.UnImplNode;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.policy.Utility.Constant;
import com.policy.Utility.Normal_Methods;
import com.policy.Utility.UIMapper;
import com.policy.cucumberTest.ChromeDriverEx;
import com.cucumber.listener.Reporter;
import static com.policy.Utility.javascriptMethods.highlight;
import static com.policy.Utility.javascriptMethods.jsClick;


//my new step on 15-10
public class TestSteps extends Normal_Methods 
{
	String capture="";
	String flow="";
	Boolean userNamePresent,expandg;
	File downloadFile;
	ArrayList<String> lines;
	LinkedHashMap<String,String> approver=new LinkedHashMap<>();
	List<String> users;
	static ChromeDriverEx driver=getChromeDriver();
	/*
	 * Before a class
	 */
	
	@Before
	public void loadProperties(Scenario scenario) throws IOException
	{
		System.out.println("Executing Scenario :"+scenario.getName());
		String fileName=System.getProperty("user.dir")+"//element.properties";
		UIMapper ui=new UIMapper(fileName);
	}
	
	@After
	public void last(Scenario scenario) throws IOException
	{
		scenario.write("Finished scenario");
		if (scenario.isFailed()) {
			System.out.println("This scenario failed");
			String capture = Normal_Methods.capture(driver, "Failed page");
			Reporter.addScreenCaptureFromPath(capture, "Failed Page");
		}	 
	}
	
	
	public String testCaseName="";
	String health;	
	String proceed;
	
	@Given("^User opens the browser$")
	public void user_opens_the_browser() throws Throwable {
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("useAutomationExtension", false);
//		options.addArguments("disable-infobars");
//		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//com/policy//resources//chromedriver.exe");
//	    driver=new ChromeDriverEx(options);
		
	}

	@Given("^opens the policy bazar site$")
	public void opens_the_policy_bazar_site() throws Throwable {
		 driver.get("https://www.policybazaar.com");
	     driver.manage().window().maximize();
	     addScreenshot("Policy bazar page");
//	     String capture=Normal_Methods.capture(driver, "Policy bazar page");
//		 Reporter.addScreenCaptureFromPath(capture, "Policy bazar Page");
	}	

	@When("^clicks on proceed button$")
	public void clicks_on_proceed_button() throws Throwable {
	
	    waitAndDoActionXpath(UIMapper.getValue("proceed"));
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
		waitToVisible(UIMapper.getValue("health"));
		waitAndDoActionXpath(UIMapper.getValue("health"));
		addScreenshot("Health");
		waitAndDoActionXpath(UIMapper.getValue("femaleButton"));
		addScreenshot("Female Button");
		waitAndType(UIMapper.getValue("Name"), accessTestData(testCaseName, "Name"));
		waitAndType(UIMapper.getValue("tel"), accessTestData(testCaseName, "MobileNo"));
		addScreenshot("Mobile No");
		waitAndDoActionXpath(UIMapper.getValue("continue"));

		try {
			waitToVisible(UIMapper.getValue("TellUs"));
			boolean flag = verifyCondition(UIMapper.getValue("TellUs"), "Tell us");
			System.out.println("flag 1st " + flag);

			try {
				jsclick(UIMapper.getValue("Me1"));
			} catch (Exception e) {
				System.out.println("FAILED IN ME1");
			}
			try {
				waitAndDoActionXpath(UIMapper.getValue("age"));
				// waitToVisibleElements(UIMapper.getValue("age1"));
				selectDropDownList(UIMapper.getValue("age1"), accessTestData(testCaseName, "Age1"));
				waitAndDoActionXpath(UIMapper.getValue("continue"));
				waitToVisible(UIMapper.getValue("city"));
				enterTextinAutoCompletion(UIMapper.getValue("city"), accessTestData(testCaseName, "City1"));
				waitAndDoActionXpath(UIMapper.getValue("viewPlans"));
			} catch (Exception e) {
				System.out.println("FAILED IN FILLING AGE");
			}
		} catch (Exception e) {
			boolean flag = verifyCondition(UIMapper.getValue("FindThe"), "Find the");
			System.out.println("flag find the" + flag);
			jsclick(UIMapper.getValue("son"));
			jsclick(UIMapper.getValue("plus"));
			addScreenshot("SON");
			waitAndDoActionXpath(UIMapper.getValue("continue"));
			waitToVisible(UIMapper.getValue("selectSelf"));
			addScreenshot("Select Self");
			selectDropdown(accessTestData(testCaseName, "Age"), UIMapper.getValue("selectSelf"));
			selectDropdown(accessTestData(testCaseName, "Son1 age"), UIMapper.getValue("Son1"));
			selectDropdown(accessTestData(testCaseName, "Son2 age"), UIMapper.getValue("Son2"));			
			waitAndDoActionXpath(UIMapper.getValue("continue"));
			waitToVisible(UIMapper.getValue("city"));
			enterTextinAutoCompletion(UIMapper.getValue("city"), accessTestData(testCaseName, "City"));
			waitAndDoActionXpath(UIMapper.getValue("viewPlans"));
		}
	}
	
	@When("^selects two policy$")
	public void selects_two_policy() throws Throwable {

		try {
			selectCheckBox("NCB Super Premium");
			String premiumAmt=driver.findElement(By.xpath("//*[@class='quotes_select']")).getText();
			String selectedOptions=getSelectedOptions("//*[@class='quotes_select']");
			writeDataToExcel("Data", "TC1", selectedOptions);
			selectCheckBox("Health Pulse Enhanced");			
		} catch (Exception e) {
			selectRadioButton("NCB Super Premium");
			selectRadioButton("Health Companion with Recharge (Money Saver)");
		}
	}
	
	@When("^gets all the policy information$")
	public void gets_all_the_policy_information() throws Throwable {
		
		Thread.sleep(10000);
		scrollBottomOfPage();
		Thread.sleep(10000);
		scrollBottomOfPage();
		List<WebElement> elements=driver.findElements(By.xpath("//div[contains(@class,'quotes_stack_content_container')]//*[@class='quotes_plan_name']"));
		System.out.println("Size is: "+elements.size());	
		
//		for(WebElement ele:elements){
//			WebElement ele1=ele.findElement(By.xpath("//*[@class='quotes_select']//option"));
//			String xpath=ele1.toString();
//			System.out.println("xpath: "+xpath);
//			String selectedOption=getSelectedOptions(ele1.toString());
//			System.out.println(selectedOption);
//		}
		List<WebElement> elements1=driver.findElements(By.xpath("//div[contains(@class,'quotes_stack_content_container')]"));
		for(int i=0;i<elements.size();i++){
			WebElement ele=elements.get(i);
			Select select=new Select(ele.findElement(By.xpath("//*[@class='quotes_select']")));
			List<WebElement>ele2=select.getAllSelectedOptions();
			String selectedOption=ele2.get(0).getText();
			//System.out.println("The premium amount is "+selectedOption);
						
		}
		
		for(int i=0;i<elements.size();i++){
			WebElement ele=elements.get(i);
			Thread.sleep(1000);
			String text=ele.findElement(By.xpath("//*[@class='span_cover_content ']")).getText();
			System.out.println("The next amount is "+text);						
		}
		
		
		
	}

	@When("^clicks on Compare now button$")
	public void clicks_on_Compare_now_button() throws Throwable {
		waitAndDoActionXpath(UIMapper.getValue("compareNow"));
		addScreenshot("Compare2");
	}
	
	@Then("^verify the amount displayed in Sum insured is same in both pages \"(.*?)\"$")
	public void verify_the_amount_displayed_in_Sum_insured_is_same_in_both_pages(String arg1) throws Throwable {
		String testCaseName=arg1;
		readDataFromExcel("Data");
		waitToVisible("(//*[@class='select'])[1]");
		addScreenshot("AMT 1");
		String selectedValue=getSelectedOptions("(//*[@class='select'])[1]");
		addScreenshot("AMT 2");
		System.out.println("SELECTED VALUE "+selectedValue);
		String excelValue=accessTestData(testCaseName, "Policy1");
		Assert.assertEquals(excelValue, selectedValue);		
	}
	
	@Then("^Gets the monthly amount of both the policy with difference$")
	public void gets_the_monthly_amount_of_both_the_policy_with_difference() throws Throwable {
	   String policy1AMT=getTextFromUI(UIMapper.getValue("policy1Amt"));
	   String policy2AMT=getTextFromUI(UIMapper.getValue("policy2Amt"));
	   Reporter.addStepLog("Amount of Policy 1 is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+policy1AMT+"</font>");
	   Reporter.addStepLog("Amount of Policy 2 is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+policy2AMT+"</font>");
	   Reporter.addScenarioLog("This is the scenario");
	   int policy1 = getInteger(policy1AMT);
	   int policy2 = getInteger(policy2AMT);
	   int diff = getDifference(policy1, policy2);
	   Reporter.addStepLog("Difference of policy is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+diff+"</font>");
	}

	@Then("^User adds a new policy$")
	public void user_adds_a_new_policy() throws Throwable {
		addScreenshot("PLUS SIGN");
		selectDropDownList("//*[@class='css-kj6f9i-menu']//../div",accessTestData("TC1", "PolicyName1"));
		addScreenshot("1st dropdown");
		selectDropDownList(UIMapper.getValue("selectNewPlan"),accessTestData("TC1", "PolicyName2"));
		addScreenshot("2nd dropdown");
		selectDropDownList(UIMapper.getValue("selectNewPlan"),accessTestData("TC1", "Amount"));
		addScreenshot("3rd dropdown");
	}
	
	@When("^changes the policy cover to another amount \"(.*?)\"$")
	public void changes_the_policy_cover_to_another_amount(String arg1) throws Throwable {
	  waitAndDoActionXpath(UIMapper.getValue("cover"));
	  waitAndDoActionXpath(UIMapper.getValue("3to5lacs"));
	}

	@When("^clicks on apply button$")
	public void clicks_on_apply_button() throws Throwable {
	  waitAndDoActionXpath(UIMapper.getValue("applyBTN"));
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
	}

	@When("^clicks on signin button and gets the last login detail$")
	public void clicks_on_signin_button_and_gets_the_last_login_detail() throws Throwable {
		waitAndDoActionXpath(UIMapper.getValue("submit"));
		
		String capture=Normal_Methods.captureFullScreen("MDDX LOGIN page");
	    Reporter.addScreenCaptureFromPath(capture, "LOGIN Page");
	    
		waitUntilElementIsClickable(UIMapper.getValue("notification"), 30);
		
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
		
		String capture=Normal_Methods.captureFullScreen("LOGIN Details page");
	    Reporter.addScreenCaptureFromPath(capture, "LOGIN Details Page");
	}
	
	@When("^User logout from application$")
	public void user_logout_from_application() throws Throwable {
	    
		waitAndDoActionXpath(UIMapper.getValue("logout"));
		
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
	
	//Gmail workflow code
	@Given("^go to gmail login page$")
	public void go_to_gmail_login_page() throws Throwable {
		driver.get("https://www.gmail.com/");
	    driver.manage().window().maximize();
	    String capture=Normal_Methods.capture(driver, "Gmail Login Page");
	    Reporter.addScreenCaptureFromPath(capture, "Gmail Login Page");
	}
	
	@Given("^Read the Workflow Levels\"(.*?)\"$")
	public void read_the_Workflow_Levels(String arg1) throws Throwable {
	    testCaseName=arg1;
	    
	    readDataFromExcel("Workflow Name");
	    
	    String stages=accessTestData(testCaseName, "WorkFlow");
	    
	    String[] stage=stages.split("[$]");
	 
	    users=new ArrayList<>();
	    for(String s:stage) {
	    	users.add(s);
	    } 
	}
	
	@When("^User Enters Username and password of \"(.*?)\"$")
	public void user_Enters_Username_and_password_of(String arg1) throws Throwable {
		
		String users=arg1;
		
		System.out.println("arg1 "+users);
		
		readDataFromExcel("Users");
			
		String requester_id=accessTestData(users, "Username");
		
		System.out.println(arg1+" email id "+requester_id);
		
		String requester_pass=accessTestData(users, "Password");
		
		System.out.println(arg1+" password "+requester_pass);
		
		waitAndType(UIMapper.getValue("Username"), requester_id);
		Reporter.addStepLog(arg1+" username <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(users, "Username")+"</font>");
		capture=Normal_Methods.capture(driver, arg1+" Username");
	    Reporter.addScreenCaptureFromPath(capture, arg1+" Username");
		
		waitAndDoActionXpath(UIMapper.getValue("next"));
		
		waitAndType(UIMapper.getValue("Password"), requester_pass);
		Reporter.addStepLog(arg1+" password <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(users, "Password")+"</font>");
		capture=Normal_Methods.capture(driver, arg1+" Password");
	    Reporter.addScreenCaptureFromPath(capture, arg1+" Password");
	}

	@When("^clicks on sign in button$")
	public void clicks_on_sign_in_button() throws Throwable {
		waitAndDoActionXpath(UIMapper.getValue("next"));
		String capture=Normal_Methods.capture(driver, "Sign IN");
		Reporter.addScreenCaptureFromPath(capture, "Sign IN BUTTON");
	}

	@When("^users clicks on Compose button$")
	public void users_clicks_on_Compose_button() throws Throwable {
	    waitAndDoActionXpath(UIMapper.getValue("compose"));
	    String capture=Normal_Methods.capture(driver, "Compose Button");
	    Reporter.addScreenCaptureFromPath(capture, "Compose Button");

	}
	
	@When("^composes mail \"(.*?)\" and sends to \"(.*?)\"$")
	public void composes_mail_and_sends_to(String arg1, String arg2) throws Throwable {
	    
		String composeMail=arg1;
		
		readDataFromExcel("Gmail");
		
		waitAndType(UIMapper.getValue("subject"), accessTestData(composeMail, "Subject Line"));
		
		waitAndType(UIMapper.getValue("body"), accessTestData(composeMail, "Email Body"));
		
		readDataFromExcel("Users");
		
		waitAndDoActionXpath(UIMapper.getValue("recipients"));
		
		waitAndType(UIMapper.getValue("to"), accessTestData(arg2, "Username"));
		
		capture=Normal_Methods.capture(driver, arg2+"");
		Reporter.addScreenCaptureFromPath(capture, arg2+"");
		
		waitAndDoActionXpath(UIMapper.getValue("sendButton"));
		
		waitToVisible(UIMapper.getValue("msgSent"));
		System.out.println(" String IS"+driver.findElement(By.xpath(UIMapper.getValue("msgSent"))).getText());
		
		waitUntilTextIsVisible(UIMapper.getValue("msgSent"), "Message sent.");
		
		waitToVisible(UIMapper.getValue("msgSent"));
		
		boolean condition=verifyCondition(UIMapper.getValue("msgSent"),"Message sent.");
		
		System.out.println("CONDITION "+condition);
		
		if(condition)
		{
			Reporter.addStepLog("Message sent<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"</font>");

			capture=Normal_Methods.capture(driver, "Product");
			Reporter.addScreenCaptureFromPath(capture, "Product");
		}		
	}

	@When("^Logout from Gmail Account$")
	public void logout_from_Gmail_Account() throws Throwable {
	    
		waitAndDoActionCss(UIMapper.getValue("signOut"));
		
		waitAndDoActionCss(UIMapper.getValue("logOut"));
		
		Reporter.addStepLog("Clicked on Sign out<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"</font>");

		String capture=Normal_Methods.capture(driver, "Sign out");
		Reporter.addScreenCaptureFromPath(capture, "Sign Out");
	}

	@When("^\"(.*?)\" logins in$")
	public void logins_in(String arg1) throws Throwable {
	 
		try
		{
			boolean flag=driver.findElement(By.id(UIMapper.getValue("profileIden"))).getText().contains(accessTestData(arg1, "Username"));
			
			System.out.println("Flag is "+flag);
			if(!driver.findElement(By.id(UIMapper.getValue("profileIden"))).getText().contains(accessTestData(arg1, "Username")))
			{
				waitAndDoActionXpath(UIMapper.getValue("fill"));
			}
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION");			
		}
		
		waitAndDoActionXpath(UIMapper.getValue("useAnother"));
	}

	@When("^\"(.*?)\" opens the mail received by of subjectLines \"(.*?)\"$")
	public void opens_the_mail_received_by_of_subjectLines(String arg1, String arg2) throws Throwable {
		Thread.sleep(3000);
		List<WebElement> ele=driver.findElements(By.xpath(UIMapper.getValue("mailBody")));
		Reporter.addStepLog("Checking mail body <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"</font>");
		capture=Normal_Methods.capture(driver, "Mail Body");
		Reporter.addScreenCaptureFromPath(capture, "Mail Body");
		
		System.out.println("SIZE IS "+ele.size());
		readDataFromExcel("Gmail");
		
		for(WebElement e:ele)
		{
			if(e.findElement(By.cssSelector("td:nth-child(6)")).getText().contains(accessTestData(arg2, "Subject Line")))
			{
				e.findElement(By.cssSelector("td:nth-child(6)")).click();			
				break;	
			}
			else
			{
				refreshPage();
				//waitToVisibleElements(UIMapper.getValue("compose"));
				waitToVisible(UIMapper.getValue("compose"));
			}
		}
	}

	@When("^\"(.*?)\" confirms the Subject line and mail body \"(.*?)\"$")
	public void confirms_the_Subject_line_and_mail_body(String arg1, String arg2) throws Throwable {
	
		waitToVisibleCss(UIMapper.getValue("approverSubjectLine"));
		
		waitToVisibleCss(UIMapper.getValue("approverMailBody"));
		
		String subjectLine=driver.findElement(By.cssSelector(UIMapper.getValue("approverSubjectLine"))).getText();
		
		String mailBody=driver.findElement(By.cssSelector(UIMapper.getValue("approverMailBody"))).getText();
		
		if(subjectLine.contains(accessTestData(arg2, "Subject Line")) && mailBody.contains(accessTestData(arg2, "Email Body")))
		{
			System.out.println("Approved by "+arg1);
			Reporter.addStepLog("Approved by <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+arg1+"</font>");

			String capture=Normal_Methods.capture(driver, arg1+"approval");
			Reporter.addScreenCaptureFromPath(capture, arg1+"approval");
		}
	}

	@When("^send to \"(.*?)\"$")
	public void send_to(String arg1) throws Throwable {
	
		waitAndDoActionXpath(UIMapper.getValue("forward"));
		readDataFromExcel("Users");
		waitAndType(UIMapper.getValue("to"), accessTestData(arg1, "Username"));
		
		capture=Normal_Methods.capture(driver, arg1+"");
		Reporter.addScreenCaptureFromPath(capture, arg1+"");
		waitAndDoActionXpath(UIMapper.getValue("sendButton"));		
	}
	
	@When("^clicks on first product \"(.*?)\"$")
	public void clicks_on_first_product(String arg1) throws Throwable {
	
		Thread.sleep(3000);
		testCaseName=arg1;
		
		String productName=accessTestData(testCaseName, "Product");
		String[] prod=productName.split("[(]");
		
		String prod1=prod[0].trim();
		
		System.out.println("PRODUCT1 is "+prod1);
	    List<WebElement> ele=driver.findElements(By.cssSelector("[title*='"+prod1+"']"));
	    
	    for(WebElement e:ele)
	    {
	    	System.out.println("Get attribute "+e.getText());
	    	//if(e.getAttribute("text").contains(accessTestData(testCaseName, "Product")))
	    	if(accessTestData(testCaseName, "Product").contains(e.getAttribute("text")))
	    	{
	    		System.out.println("Entered LOOp");
	    		e.click();
	    		Reporter.addStepLog("Product is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(testCaseName, "Product")+"</font>");
	    		break;
	    	}
	    }
	    
		String capture=Normal_Methods.capture(driver, "First product");
	    Reporter.addScreenCaptureFromPath(capture, "First product");
	}
	
	@When("^User verifies the product and click on wishlist button \"(.*?)\"$")
	public void user_verifies_the_product_and_click_on_wishlist_button(String arg1) throws Throwable {
	
		String text=accessTestData(arg1, "Product");
		
		String[] prod=text.split("[(]");
		
		String prod1=prod[0].trim();
		
		String text1="//*[text()='"+prod1+"']";
		
		String retrievedtext=driver.findElement(By.xpath(text1)).getText();
		
		System.out.println("Retrieved text "+retrievedtext);
		
		Assert.assertTrue("Product is not matched", (accessTestData(arg1, "Product").contains(retrievedtext)));
		
		System.out.println("Product is matched");
		
		waitToVisibleElements(UIMapper.getValue("wishlistenable"));
		
		waitAndDoActionXpath(UIMapper.getValue("wishlistenable"));
	
		/*String background =driver.findElement(By.xpath("(//*[@opacity='.9'])[1]")).getCssValue("fill");
		
		System.out.println("BACK "+background);
		
		String hex=Color.fromString(background).asHex();
	    Reporter.addStepLog("Background Color in HEX <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+hex+"</font>");
	    System.out.println("Colors in hex "+hex);*/
		
		capture=Normal_Methods.capture(driver, "Wishlist button page");
	    Reporter.addScreenCaptureFromPath(capture, "Wishlist button page");
	}

	@When("^User verifies the message when they click on wishlist button$")
	public void user_verifies_the_message_when_they_click_on_wishlist_button() throws Throwable {
	
		String warMSG=driver.findElement(By.xpath(UIMapper.getValue("warning"))).getText();
	    Reporter.addStepLog("Notification message <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+warMSG+"</font>");
	    System.out.println("WARNING msg "+warMSG);
	    
	    capture=Normal_Methods.capture(driver, "Add Wishlist page");
	    Reporter.addScreenCaptureFromPath(capture, "Add WishList page");
	}

	@When("^user closes the window and switch to Parent tab$")
	public void user_closes_the_window_and_switch_to_Parent_tab() throws Throwable {	
		driver.close();
		switchToOldTab();
	}

	@When("^user clicks on wishlist link$")
	public void user_clicks_on_wishlist_link() throws Throwable {
	
		mouseHover(UIMapper.getValue("myAccount"), UIMapper.getValue("wishList"));	
		refreshPage();
	}
	
	@When("^verifies whether added item is in wishlist\"(.*?)\"$")
	public void verifies_whether_added_item_is_in_wishlist(String arg1) throws Throwable {
	
		List<WebElement> element=driver.findElements(By.xpath("//*[@class='TLVGit']"));
		
		for(WebElement ele:element)
		{
			String item=ele.getText();
			
			if((accessTestData(arg1, "Product")).contains(item))
			{
				Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">Added item to wishList</font>");
				capture=Normal_Methods.capture(driver, "added page");
			    Reporter.addScreenCaptureFromPath(capture, "added page");
			    break;
			}
		}		
	}
	
	@When("^Users removes the item from wishlist \"(.*?)\"$")
	public void users_removes_the_item_from_wishlist(String arg1) throws Throwable {
		
List<WebElement> element=driver.findElements(By.xpath("//*[@class='TLVGit']"));
		
		for(WebElement ele:element)
		{
			String item=ele.getText();
			
			if((accessTestData(arg1, "Product")).contains(item))
			{
				waitAndDoActionXpath(UIMapper.getValue("deleteIcon"));
				waitAndDoActionXpath(UIMapper.getValue("remove"));
				waitToVisible(UIMapper.getValue("warning"));
				
				String warMSG=driver.findElement(By.xpath(UIMapper.getValue("warning"))).getText();
			    Reporter.addStepLog("Notification message <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+warMSG+"</font>");
			    System.out.println("WARNING msg "+warMSG);
			    
			    capture=Normal_Methods.capture(driver, "Remove WishList page");
			    Reporter.addScreenCaptureFromPath(capture, "Remove WishList page");
			    break;
			}
		}	
	}
	
	@When("^Clicks on attach button$")
	public void clicks_on_attach_button() throws Throwable {
	    waitAndDoActionXpath(UIMapper.getValue("attachD"));
	    String capture=Normal_Methods.capture(driver, "Attach Button");
	    Reporter.addScreenCaptureFromPath(capture, "Attach Button");
	}

	@When("^attaches the file for uploading$")
	public void attaches_the_file_for_uploading() throws Throwable {
		uploadFile("C:\\Users\\Pravin Shetty\\Pictures\\Saved Pictures\\butterfly.jpg");
		
		
	}
	
	@When("^attaches the file for uploading \"(.*?)\"$")
	public void attaches_the_file_for_uploading(String arg1) throws Throwable {
		
		readDataFromExcel("Gmail");
		String str1=System.getProperty("user.dir")+accessTestData(arg1, "File Attach");
		System.out.println("The upload file is "+str1);
	    uploadFile(System.getProperty("user.dir")+"\\Documents\\"+accessTestData(arg1, "File Attach"));
	}

	@Then("^the files gets uploaded and Users checks the attached file$")
	public void the_files_gets_uploaded_and_Users_checks_the_attached_file() throws Throwable {
	    
		waitToVisibleCss("[class='vI']");
		String text=driver.findElement(By.cssSelector("[class='vI']")).getText();
		System.out.println("Text "+text);
		String text2="C:\\Users\\Pravin Shetty\\Pictures\\Saved Pictures\\butterfly.jpg";
		String[] text3=text2.split("\\\\");
		
		System.out.println("Splitted text "+text3[5]);
		
		String capture=Normal_Methods.capture(driver, "Uploaded File");
	    Reporter.addScreenCaptureFromPath(capture, "Uploaded File");
	}

	@When("^Users implements the given workflow \"(.*?)\"$")
	public void users_implements_the_given_workflow(String arg1) throws Throwable {
	 
		readDataFromExcel("Workflow Name");
		
		System.out.println("FLOW 1"+flow);
		flow=accessTestData(arg1, "WorkFlow");
		System.out.println("FLOW 2"+flow);
		if(flow.lastIndexOf("(DONE)")>0)
		{
			flow=flow.substring(flow.lastIndexOf("(DONE)")+7);
		}
	}
	
	@When("^Users implements the new workflow \"(.*?)\"$")
	public void users_implements_the_new_workflow(String arg1) throws Throwable {
		readDataFromExcel("Workflow Name");		
		System.out.println("FLOW 1"+flow);
		flow=accessTestData(arg1, "WorkFlow");
		System.out.println("FLOW 2"+flow);		
	}

	@When("^Loggins with the users$")
	public void loggins_with_the_users() throws Throwable {
		
		if(flow.lastIndexOf("(DONE)")>0)
		{
			flow=flow.substring(flow.lastIndexOf("(DONE)")+7);
		}
		String groupName=flow.split("[$]")[0];
		
		System.out.println("GroupName 1"+groupName);
		flow=groupName+"(DONE)"+flow.replaceAll(groupName, "");
		System.out.println(flow);
		try {
			gmailLogin(groupName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("^Loggins with the users to yahoo$")
    public void loggins_with_the_users_to_yahoo() throws Throwable {
		if(flow.lastIndexOf("(DONE)")>0)
		{
			flow=flow.substring(flow.lastIndexOf("(DONE)")+7);
		}
		String groupName=flow.split("[$]")[0];		
		System.out.println("GroupName 1"+groupName);
		flow=groupName+"(DONE)"+flow.replaceAll(groupName, "");
		System.out.println(flow);
		try {
			yahooLogin(groupName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void yahooLogin(String groupName) throws IOException {
		readDataFromExcel("User2");
		String profileName=accessTestData(groupName, "ProfileName");
		boolean flag=isDisplayed(UIMapper.getValue("profileNameY"));
		if (flag) {			
			String profileNameWeb = getTextFromUI(UIMapper.getValue("profileNameY"), UIMapper.getValue("profileNameY2"));
			if (!profileName.equalsIgnoreCase(profileNameWeb)) {
				waitAndDoActionXpath(UIMapper.getValue("profile"));
				waitAndDoActionXpath(UIMapper.getValue("signOutyahoo"));
				loginYahoo(groupName);
			}
		} else if(isDisplayed(UIMapper.getValue("profileNameY2"))){
			String profileNameWeb = getTextFromUI(UIMapper.getValue("profileNameY"), UIMapper.getValue("profileNameY2"));
			if (!profileName.equalsIgnoreCase(profileNameWeb)) {
				waitAndDoActionXpath(UIMapper.getValue("profileNameY2"));
				jsClick(UIMapper.getValue("signOutyahoo"));				
				boolean isView=isDisplayed(UIMapper.getValue("addAccount"));
				if(isView)
					jsclick(UIMapper.getValue("addAccount"));
				loginYahoo(groupName);
			}
		}else {
			loginYahoo(groupName);
		}				
	}
	
	/*
	 * This is the login function for yahoo
	 */
	public void loginYahoo(String groupName) throws IOException {
		
		try {
			boolean flag=isDisplayed(UIMapper.getValue("signMeOut"));
			if(flag)
				jsClick(UIMapper.getValue("signMeOut"));
		}
		catch(Exception e) {			
		}
		untilVisible(UIMapper.getValue("SignIn"));
		waitAndDoActionXpath(UIMapper.getValue("SignIn"));
		boolean isView=isDisplayed(UIMapper.getValue("addAccount"));
		if(isView)
			jsclick(UIMapper.getValue("addAccount"));		
		waitAndType(UIMapper.getValue("usernameYah"), accessTestData(groupName, "Username"));
		jsClick(UIMapper.getValue("nextBTN"));
		waitAndType(UIMapper.getValue("Password"), accessTestData(groupName, "Password"));
		jsClick(UIMapper.getValue("nextBTN"));
	}
	

	void gmailLogin(String groupName) throws Exception
	{
		HashMap<String,String> getUserInfor=getUserData();
		
		String userinfo=getUserInfor.get(groupName);
		
		String profileName=userinfo.split(";")[0];
		String username=userinfo.split(";")[1];
		String password=userinfo.split(";")[2];
		System.out.println("PROFILE_NAME "+profileName);
		System.out.println("USERNAME "+username);
		System.out.println("PASSWORD "+password);
		
		try {
			List ele=new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(UIMapper.getValue("Username"))));
			userNamePresent=ele.size()>0;
			System.out.println("Boolean value of userNamePresent and size"+userNamePresent+" "+ele.size());
		} catch (Exception e1) {
			System.out.println("USERNAME IS NOT PRESENT");
			//e1.printStackTrace();
		}
		try {
			List ele1=new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(UIMapper.getValue("logoutExpand"))));
			expandg=ele1.size()>0;
			System.out.println("Boolean value of expandg "+expandg);
		} catch (Exception e1) {
			System.out.println("LOGOUT EXPAND IS NOT PRESENT");
			//e1.printStackTrace();
		}
		
		if(userNamePresent==true)
		{
			System.out.println("inside if Boolean value of userNamePresent and size "+userNamePresent);
			waitAndType(UIMapper.getValue("Username"), username);
			Reporter.addStepLog("username <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+username+"</font>");
			capture=Normal_Methods.capture(driver, username);
		    Reporter.addScreenCaptureFromPath(capture, username);
			
			waitAndDoActionXpath(UIMapper.getValue("next"));
			
			waitAndType(UIMapper.getValue("Password"), password);
			Reporter.addStepLog("password <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+password+"</font>");
			capture=Normal_Methods.capture(driver, password);
		    Reporter.addScreenCaptureFromPath(capture, password);
		    userNamePresent=false;//this statement is very important as it becomes true 
		}
		else if(expandg==true)
		{
			waitAndDoActionCss(UIMapper.getValue("logoutExpand"));
			String profName=driver.findElement(By.xpath(UIMapper.getValue("profileNameG"))).getText();
			if(!profName.contains(profileName))
			{
				waitAndDoActionCss(UIMapper.getValue("logOut"));
				try {
					List ele2 = new WebDriverWait(driver, 5).until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.cssSelector(UIMapper.getValue("loginDownArrow"))));
					if(ele2.size()>0)
					{
						waitAndDoActionCss(UIMapper.getValue("loginDownArrow"));
					}
				} catch (Exception e) {
					
				}
				waitAndDoActionXpath(UIMapper.getValue("useAnother"));
				gmailLogin(groupName);
			}
		}
		/*try
		{			
			waitAndDoActionCss(UIMapper.getValue("logoutExpand"));
			String profName=driver.findElement(By.xpath(UIMapper.getValue("profileNameG"))).getText();
			if(!profName.contains(profileName))
			{
				waitAndDoActionCss(UIMapper.getValue("logOut"));
				try {
					waitAndDoActionCss(UIMapper.getValue("loginDownArrow"));
				} catch (Exception e) {
					
				}
				waitAndDoActionXpath(UIMapper.getValue("useAnother"));
				gmailLogin(groupName);
			}
		}
		catch(Exception e)
		{	
			waitAndType(UIMapper.getValue("Username"), username);
			Reporter.addStepLog("username <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+username+"</font>");
			capture=Normal_Methods.capture(driver, username);
		    Reporter.addScreenCaptureFromPath(capture, username);
			
			waitAndDoActionXpath(UIMapper.getValue("next"));
			
			waitAndType(UIMapper.getValue("Password"), password);
			Reporter.addStepLog("password <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+password+"</font>");
			capture=Normal_Methods.capture(driver, password);
		    Reporter.addScreenCaptureFromPath(capture, password);
		}	*/	
	}
	
	public static HashMap<String,String> getUserData()throws Exception
	{
		HashMap<String,String> map=new HashMap<>();
		
		FileInputStream fis=new FileInputStream("TestData.xls");
		HSSFWorkbook wk=new HSSFWorkbook(fis);
		HSSFSheet sheet=wk.getSheet("User2");
		int rowNum=sheet.getPhysicalNumberOfRows();
		
		for(int i=1;i<rowNum;i++)
		{
			String userInfo=sheet.getRow(i).getCell(0).getStringCellValue();
			String profileName=sheet.getRow(i).getCell(1).getStringCellValue();
			String username=sheet.getRow(i).getCell(2).getStringCellValue();
			String password=sheet.getRow(i).getCell(3).getStringCellValue();
			
			map.put(userInfo, profileName+";"+username+";"+password);			
		}
		return map;
	}
	
	@Then("^users enters subject line and sends the mail to Approver1 \"(.*?)\"$")
	public void users_enters_subject_line_and_sends_the_mail_to_Approver(String arg1) throws Throwable {
	  
		waitAndType(UIMapper.getValue("subject"), accessTestData(arg1, "Subject Line"));
		
		waitAndDoActionXpath(UIMapper.getValue("//*[contains(text(),'Recipients')]"));
		
		
		waitAndType(UIMapper.getValue("//*[@name='to']"),"");
	}
	
	@Then("^users enters subject line \"(.*?)\"> and sends the mail to Approver (\\d+)$")
	public void users_enters_subject_line_and_sends_the_mail_to_Approver(String arg1, int arg2) throws Throwable {
	 
		readDataFromExcel("Gmail");
		waitAndType(UIMapper.getValue("subject"), accessTestData(arg1, "Subject Line"));
		
		waitAndDoActionXpath(UIMapper.getValue("//*[contains(text(),'Recipients')]"));
		
		LinkedHashMap<String,String> map1=m1();
		
		waitAndType(UIMapper.getValue("//*[@name='to']"),map1.get("APPROVER "+arg2));
	}
	
	/*@Then("^users enters subject line \"(.*?)\"> and sends the mail to \"(.*?)\"$")
	public void users_enters_subject_line_and_sends_the_mail_to(String arg1, String arg2) throws Throwable {
	 
		readDataFromExcel("Gmail");
		
		System.out.println("ARG1 "+arg1);
		
		System.out.println("ARG2 "+arg2);
		waitAndType(UIMapper.getValue("subject"), accessTestData(arg1, "Subject Line"));
		
		waitAndDoActionXpath(UIMapper.getValue("//*[contains(text(),'Recipients')]"));
		
		LinkedHashMap<String,String> map1=m1();
		
		waitAndType(UIMapper.getValue("//*[@name='to']"),map1.get(arg2));
	}*/
	
	@Then("^users enters subject line \"(.*?)\" and sends the mail to \"(.*?)\"$")
	public void users_enters_subject_line_and_sends_the_mail_to(String arg1, String arg2) throws Throwable {
	  
		readDataFromExcel("Gmail");
		
		System.out.println("ARG1 "+arg1);
		
		System.out.println("ARG2 "+arg2);
		waitAndType(UIMapper.getValue("subject"), accessTestData(arg1, "Subject Line"));
		
		waitAndDoActionXpath(UIMapper.getValue("recipients"));
		
		LinkedHashMap<String,String> map1=m1();
		
		waitAndType(UIMapper.getValue("to"),map1.get(arg2));
		
		capture=Normal_Methods.capture(driver, arg2+"");
		Reporter.addScreenCaptureFromPath(capture, arg2+"");
		
		waitAndDoActionXpath(UIMapper.getValue("sendButton"));
		
		waitToVisible(UIMapper.getValue("msgSent"));
		System.out.println(" String IS"+driver.findElement(By.xpath(UIMapper.getValue("msgSent"))).getText());
		
		waitUntilTextIsVisible(UIMapper.getValue("msgSent"), "Message sent.");
		
		waitToVisible(UIMapper.getValue("msgSent"));
		
		boolean condition=verifyCondition(UIMapper.getValue("msgSent"),"Message sent.");
		
		System.out.println("CONDITION "+condition);
		
		if(condition)
		{
			Reporter.addStepLog("Message sent<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"</font>");

			capture=Normal_Methods.capture(driver, "Product");
			Reporter.addScreenCaptureFromPath(capture, "Product");
		}
	}
	
	public LinkedHashMap<String,String> m1() throws Exception
	{
		readDataFromExcel("Workflow Name");
		String flow2=accessTestData("WK3", "WorkFlow");
		//System.out.println("FLOW 2"+flow2);
		String arg1=flow2.substring(flow2.indexOf(">")+1);
		String[] arg2=arg1.split(">");
		//System.out.println(Arrays.toString(arg2));
		LinkedHashMap<Integer,String> map=new LinkedHashMap<>();
		approver=new LinkedHashMap<>();
		for(int i=0;i<arg2.length;i++)
		{
			map.put(i+1, arg2[i]);
			System.out.println(map);
			
			readDataFromExcel("User2");			
			System.out.println("APPROVER "+(i+1)+" "+accessTestData(map.get(i+1), "Username"));			
			approver.put("APPROVER "+(i+1), accessTestData(map.get(i+1), "Username"));
		}
		System.out.println(approver);
		return approver;
	}
	
	@When("^user opens the received mail from \"(.*?)\"$")
	public void user_opens_the_received_mail_from(String tcName) throws Throwable {
	  		
		waitToVisible(UIMapper.getValue("mailBodyG"));
		
		WebElement ele=driver.findElement(By.xpath(UIMapper.getValue("mailBodyG")+"//td[6]//span"));
		
		String text=ele.getText();
		
		System.out.println("Text is "+text);
		
		readDataFromExcel("Gmail");
		
		System.out.println("Subject line is "+accessTestData(tcName, "Subject Line"));
		
		if(text.contains(accessTestData(tcName, "Subject Line")))
		{
			waitAndDoActionXpath(UIMapper.getValue("mailBodyG"));
			Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"Opening the received mail"+"</font>");

			capture=Normal_Methods.capture(driver, "Mail");
			Reporter.addScreenCaptureFromPath(capture, "Mail");
		}
	}

	@When("^clicks on download link$")
	public void clicks_on_download_link() throws Throwable {
	  
		waitAndDoActionXpath(UIMapper.getValue("downloadG"));
		Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+"Clicking on Download link"+"</font>");

		capture=Normal_Methods.capture(driver, "Download");
		Reporter.addScreenCaptureFromPath(capture, "Download");

	}
	
	@When("^user opens the downloaded file$")
	public void user_opens_the_downloaded_file() throws Throwable {
	
		downloadFile=new File("C:\\Users\\Pravin Shetty\\Downloads\\PasswordFile.txt");
		
		Thread.sleep(5000);
		if(downloadFile.exists())
		{
			System.out.println("The file is downloaded properly");
		}
		else
			System.out.println("The file is not downloaded");
		
	}

	@When("^checks the fields whether its having the required text \"(.*?)\"$")
	public void checks_the_fields_whether_its_having_the_required(String tcName) throws Throwable {
	
		readDataFromExcel("Gmail");
		
		lines=new ArrayList<>();
		
		BufferedReader reader=new BufferedReader(new FileReader(downloadFile));
		
		String readLine=reader.readLine();
		
		while(readLine!=null)
		{
			lines.add(readLine);
			readLine=reader.readLine();
		}
		
		System.out.println("ArrayList data "+lines);
		
		for(String s:lines)
		{
			if(s.contains(accessTestData(tcName, "Matching Text")))
			{
				System.out.println("The text is present so EXIT THE LOOP");
			}
		}		
	}

	@Then("^take the password from text field and paste in excel sheet \"(.*?)\"$")
	public void take_the_password_from_text_field_and_paste_in_excel_sheet(String tcName) throws Throwable {
	
		for(String s:lines)
		{
			if(s.contains(accessTestData(tcName, "Matching Text")))
			{
				s=s.substring(s.indexOf("=")+1);
				s=s.trim();
				
				System.out.println("PRINTING PASSWORD "+s);
				writeDataToExcel("Gmail", tcName,s);
				break;
			}
		}
	}
	
	@Given("^go to yahoo mail$")
	public void go_to_yahoo_mail() throws Throwable {
		driver.get("http://www.yahoomail.com/");
	    driver.manage().window().maximize();
	    addScreenshot("Yahoo Login Page");	  
	}

	@When("^user enters username of \"(.*?)\" in yahoo page$")
	public void user_enters_username_of_in_yahoo_page(String arg1) throws Throwable {
		readDataFromExcel("User2");
		untilVisible(UIMapper.getValue("SignIn"));
		waitAndDoActionXpath(UIMapper.getValue("SignIn"));
		waitAndType(UIMapper.getValue("usernameYah"), accessTestData(arg1, "Username"));
	}

	@When("^also enters password of \"(.*?)\" in yahoo page$")
	public void also_enters_password_of_in_yahoo_page(String arg1) throws Throwable {
		readDataFromExcel("User2");
		waitAndType(UIMapper.getValue("Password"), accessTestData(arg1, "Password"));
	}
	
	@When("^clicks on next button$")
	public void clicks_on_next_button() throws Throwable {
		jsClick(UIMapper.getValue("nextBTN"));
	}
	
    @When("^User clicks on compose button to compose mail$")
    public void user_clicks_on_compose_button_to_compose_mail() throws Throwable {
    	waitAndDoActionXpath(UIMapper.getValue("composeBtnYahoo"));
    }
    
    @When("^fills the required field$")
    public void fills_the_required_field() throws Throwable {
    	waitAndType(UIMapper.getValue("toPerson"), accessTestData("Yahoo Approver1", "Username"));
    	readDataFromExcel("Gmail");
    	waitAndType(UIMapper.getValue("subjectLine"), accessTestData("Yahoo Approver1", "Username"));
    }
    
    @And("^fills the required field \"([^\"]*)\"$")
    public void fills_the_required_field_something(String testcasename) throws Throwable {
    	waitAndType(UIMapper.getValue("toPerson"), accessTestData("Yahoo Approver1", "Username"));
    	readDataFromExcel("Gmail");
    	waitAndType(UIMapper.getValue("subjectLine"), accessTestData(testcasename, "Subject Line"));
    	waitAndType(driver.findElement(By.xpath(UIMapper.getValue("yahooBody"))), accessTestData(testcasename, "Email Body"));
    	
    }

    @When("^clicks on send button$")
    public void clicks_on_send_button() throws Throwable {
    	waitAndDoActionXpath(UIMapper.getValue("sendBtnYahoo"));
    }
    
    @And("^User logs out from the account$")
    public void user_logs_out_from_the_account() throws Throwable {
    	waitAndDoActionXpath(UIMapper.getValue("profile"));
    	waitAndDoActionXpath(UIMapper.getValue("signOutyahoo"));
    }
    
    @When("^user enters username of Requester in yahoo page \"(.*?)\"$")
    public void user_enters_username_of_Requester_in_yahoo_page(String arg1) throws Throwable {
     	readDataFromExcel("User2");
    	for(String s:users) {
    		untilVisible(UIMapper.getValue("SignIn"));
    		waitAndDoActionXpath(UIMapper.getValue("SignIn"));
    		waitAndType(UIMapper.getValue("usernameYah"), accessTestData(s, "Username"));	
    	}	
    }
    
    @When("^user enters username of user in yahoo page$")
    public void user_enters_username_of_user_in_yahoo_page() throws Throwable {
     	readDataFromExcel("User2");
    	for(String s:users) {
    		untilVisible(UIMapper.getValue("SignIn"));
    		waitAndDoActionXpath(UIMapper.getValue("SignIn"));
    		waitAndType(UIMapper.getValue("usernameYah"), accessTestData(s, "Username"));	
    	}
    }

    @When("^also enters password of user in yahoo page$")
    public void also_enters_password_of_user_in_yahoo_page() throws Throwable {
    	readDataFromExcel("User2");
    	for(String s:users) {
    		waitAndType(UIMapper.getValue("Password"), accessTestData(s, "Password"));	
    	}		
    }
    
    @When("^User clicks on Sign in link$")
    public void user_clicks_on_Sign_in_link() throws Throwable {
    	waitAndDoActionXpath(UIMapper.getValue("signInYahoo"));
    }
}  