package com.policy.stepDefinition;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import static com.policy.Utility.Constant.driver;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.policy.Utility.Normal_Methods;
import com.policy.Utility.UIMapper;

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
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//com/policy//resources//chromedriver.exe");
	       driver=new ChromeDriver();          
	}

	@Given("^opens the policy bazar site$")
	public void opens_the_policy_bazar_site() throws Throwable {
		 driver.get("https://www.policybazaar.com");
	     driver.manage().window().maximize();
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
	}
	
	@Given("^opens the mddx site$")
	public void opens_the_mddx_site() throws Throwable {
		driver.get("http://t.mddximage.com");
	     driver.manage().window().maximize();
	}

	@When("^clicks on signin button and gets the last login detail$")
	public void clicks_on_signin_button_and_gets_the_last_login_detail() throws Throwable {
		waitAndDoActionXpath(UIMapper.getValue("submit"));
		
		waitUntilElementIsClickable(UIMapper.getValue("notification"), 30);
		
		String loginDetails=driver.findElement(By.xpath(UIMapper.getValue("notification"))).getText();
		
		System.out.println("Login Details "+loginDetails);
	}
	
	@When("^user fills the login details\"(.*?)\"$")
	public void user_fills_the_login_details(String arg1) throws Throwable {
		testCaseName = arg1;
		readDataFromExcel("Login");
		
		waitAndType(UIMapper.getValue("username"), accessTestData(testCaseName, "Username"));
		
		waitAndType(UIMapper.getValue("password"), accessTestData(testCaseName, "Password"));
		
		
	}

}  