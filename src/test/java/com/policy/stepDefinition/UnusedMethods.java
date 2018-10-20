package com.policy.stepDefinition;

import static com.policy.Utility.Constant.driver;

import org.openqa.selenium.By;

import cucumber.api.java.en.When;

public class UnusedMethods {
	
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
	
	@When("^gets the min and max premium amount$")
	public void gets_the_min_and_max_premium_amount() throws Throwable {
		String Minpremium = driver.findElement(By.xpath("//*[@name='premiumMinInput']")).getAttribute("value");

		System.out.println("Minimum premium is " + Minpremium);

		String Maxpremium = driver.findElement(By.xpath("//*[@name='premiumMaxInput']")).getAttribute("value");

		System.out.println("Maximum premium is " + Maxpremium);
	}

}
