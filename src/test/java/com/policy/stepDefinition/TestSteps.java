package com.policy.stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import com.cucumber.listener.Reporter;

import static com.policy.Utility.Constant.driver;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import com.policy.Utility.Normal_Methods;
import com.policy.Utility.UIMapper;
import com.policy.cucumberTest.ChromeDriverEx;
import com.cucumber.listener.Reporter;

//my new step on 15-10
public class TestSteps extends Normal_Methods 
{
	String capture="";
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

		waitAndDoActionXpath(UIMapper.getValue("health"));

		String country = UIMapper.getValue("country");

		waitAndDoActionXpath(country);

		String country1 =UIMapper.getValue("country1");

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
	    
	    String Requester=stage[0];
	    String Approver_1=stage[1];
	    String Approver_2=stage[2];
	    
	    System.out.println("Stage 0 "+Requester+" "+"Stage 1 "+Approver_1+" "+" Stage 2"+Approver_2);	    
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


}  