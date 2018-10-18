package com.policy.stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import static com.policy.Utility.Constant.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.policy.Utility.Normal_Methods;

//my new step on 15-10
public class TestSteps extends Normal_Methods 
{
	public String testCaseName="";
	
	String proceed="//*[@type='submit']//*[text()='Proceed']";
	
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

	/*@When("^user fills all the details$")
	public void user_fills_all_the_details() throws Throwable {
		String health="//span[text()='Health']";
	       
	       waitAndDoActionXpath(health);
	       
	       String country="//select[@ng-model='setCountryCodeId.CountryCode']";
	       
	       waitAndDoActionXpath(country);
	       
	       String country1="//select[@ng-model='setCountryCodeId.CountryCode']//child::option";
	       
	       String value="Angola";
	       
	       dropDownSelect(country1, value);

	       try
	       {
	    	   List<WebElement> ele=driver.findElements(By.xpath("//*[@role='dialog']//*[@style='display: block;']//span"));
	           
	           ele.get(1).click();   
	       }
	       catch(Exception e)
	       {
	    	   System.out.println(" is not present");
	       }
	       String tel="//input[@type='tel']";
	       
	       waitAndType(tel, "9920530848");
	       
	       String city="//input[@type='search']";
	       
	       Normal_Methods.waitAndDoActionXpath(city);
	       
	       Normal_Methods.enterTextinAutoCompletion(city, "mumbai");
	       
	       String selfCheck="//*[@name='checkboxSelf']";
	       
	       Normal_Methods.waitAndDoActionXpath(selfCheck);
	       
	       String age="//*[@name='selectSelf']//child::option";
	       
	       Normal_Methods.dropDownSelect(age, "30");
	}*/

	@When("^clicks on proceed button$")
	public void clicks_on_proceed_button() throws Throwable {
	
	    waitAndDoActionXpath(proceed);
		//Normal_Methods.waitUntilElementIsClickable(proceed, 35);
		//driver.findElement(By.cssSelector("span:contains('Proceed')")).click();
	}

	/*
	 * @When("^User enters the next page$") public void
	 * user_enters_the_next_page() throws Throwable {
	 * 
	 * String title="//select[@name='salutation']//child::option";
	 * 
	 * waitAndDoActionXpath(title);
	 * 
	 * dropDownSelect(title, "Mr.");
	 * 
	 * String selfName="//*[@name='selfName']";
	 * 
	 * waitAndType(selfName, "Akash");
	 * 
	 * String email="//*[@name='email']";
	 * 
	 * Normal_Methods.waitAndType(email, "akash@yopmail.com");
	 * 
	 * WebElement medicalCondition=driver.findElement(By.
	 * xpath("//*[@value='1' and @role='radio']"));
	 * 
	 * Normal_Methods.highlight(medicalCondition);
	 * 
	 * String b1=medicalCondition.getAttribute("aria-checked");
	 * 
	 * System.out.println("b1 "+b1);
	 * 
	 * if(!b1.equalsIgnoreCase("false")) { System.out.println("Entered loop");
	 * driver.findElement(By.xpath("//*[@class='md-container']")).click(); }
	 * else { System.out.println("entered in else "); }
	 * 
	 * //Normal_Methods.waitAndDoActionXpath(proceed); }
	 */

	@When("^User searches search text on search bar$")
	public void user_searches_search_text_on_search_bar() throws Throwable {
	    
	}

	@When("^clicks on search button$")
	public void clicks_on_search_button() throws Throwable {
		String searchText = "//*[@name='searchtext']";

		waitAndType(searchText, "Religare");

		String searchicon = "//*[@role='button']//child::span[@class='serach-wrapper']";

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

	@When("^gets the min and max premium amount$")
	public void gets_the_min_and_max_premium_amount() throws Throwable {
		String Minpremium = driver.findElement(By.xpath("//*[@name='premiumMinInput']")).getAttribute("value");

		System.out.println("Minimum premium is " + Minpremium);

		String Maxpremium = driver.findElement(By.xpath("//*[@name='premiumMaxInput']")).getAttribute("value");

		System.out.println("Maximum premium is " + Maxpremium);
	}
	
	@When("^user fills all the details \"(.*?)\"$")
	public void user_fills_all_the_details(String arg1) throws Throwable {

		testCaseName = arg1;
		readDataFromExcel("Data");

		String health = "//span[text()='Health']";

		waitAndDoActionXpath(health);

		String country = "//select[@ng-model='setCountryCodeId.CountryCode']";

		waitAndDoActionXpath(country);

		String country1 = "//select[@ng-model='setCountryCodeId.CountryCode']//child::option";

		// String value = "Angola";

		dropDownSelect(country1, accessTestData(testCaseName, "Country"));

		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//*[@role='dialog']//*[@style='display: block;']//span"));

			ele.get(1).click();
		} catch (Exception e) {
			System.out.println(" is not present");
		}
		String tel = "//input[@type='tel']";

		waitAndType(tel, accessTestData(testCaseName, "MobileNo"));

		String city = "//input[@type='search']";

		waitAndDoActionXpath(city);

		enterTextinAutoCompletion(city, accessTestData(testCaseName, "City"));

		String selfCheck = "//*[@name='checkboxSelf']";

		waitAndDoActionXpath(selfCheck);

		String age = "//*[@name='selectSelf']//child::option";

		dropDownSelect(age, accessTestData(testCaseName, "Age"));
	}
	
	@When("^User enters the next page \"(.*?)\"$")
	public void user_enters_the_next_page(String arg1) throws Throwable {

		testCaseName = arg1;

		String title = "//select[@name='salutation']//child::option";

		waitAndDoActionXpath(title);

		dropDownSelect(title, accessTestData(testCaseName, "Title"));

		String selfName = "//*[@name='selfName']";

		waitAndType(selfName, accessTestData(testCaseName, "Name"));

		String email = "//*[@name='email']";

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
}  