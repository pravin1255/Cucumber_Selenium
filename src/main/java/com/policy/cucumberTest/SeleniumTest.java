/*package com.policy.cucumberTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import com.policy.Utility.Normal_Methods;
import static com.policy.Utility.Constant.driver;

public class SeleniumTest {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//java//com/policy//resources//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.policybazaar.com");
		driver.manage().window().maximize();

		String health = "//span[text()='Health']";

		Normal_Methods.waitAndDoActionXpath(health);

		String country = "//select[@ng-model='setCountryCodeId.CountryCode']";

		Normal_Methods.waitAndDoActionXpath(country);

		String country1 = "//select[@ng-model='setCountryCodeId.CountryCode']//child::option";

		String value = "Angola";

		Normal_Methods.dropDownSelect(country1, value);

		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//*[@role='dialog']//*[@style='display: block;']//span"));

			ele.get(1).click();
		} catch (Exception e) {
			System.out.println(" is not present");
		}
		String tel = "//input[@type='tel']";

		Normal_Methods.waitAndType(tel, "9920530848");

		String city = "//input[@type='search']";

		Normal_Methods.enterTextinAutoCompletion(city, "mumbai");

		String selfCheck = "//*[@name='checkboxSelf']";

		Normal_Methods.waitAndDoActionXpath(selfCheck);

		String age = "//*[@name='selectSelf']//child::option";

		Normal_Methods.dropDownSelect(age, "30");

		
		 * String income="//*[@class='income']//li";
		 * 
		 * List<WebElement> ele1=driver.findElements(By.xpath(income));
		 * 
		 * ele1.get(5).click();
		 

		// Normal_Methods.waitAndDoActionXpath(income);

		String proceed = "//span[text()='Proceed']";

		Normal_Methods.waitAndDoActionXpath(proceed);

		String title = "//select[@name='salutation']//child::option";

		Normal_Methods.waitAndDoActionXpath(title);

		Normal_Methods.dropDownSelect(title, "Mr.");

		String selfName = "//*[@name='selfName']";

		Normal_Methods.waitAndType(selfName, "Akash");

		String email = "//*[@name='email']";

		Normal_Methods.waitAndType(email, "akash@yopmail.com");

		WebElement medicalCondition = driver.findElement(By.xpath("//*[@value='1' and @role='radio']"));

		Normal_Methods.highlight(medicalCondition);

		String b1 = medicalCondition.getAttribute("aria-checked");

		System.out.println("b1 " + b1);

		if (!b1.equalsIgnoreCase("false")) {
			System.out.println("Entered loop");
			driver.findElement(By.xpath("//*[@class='md-container']")).click();
		} else {
			System.out.println("entered in else ");
		}

		Normal_Methods.waitAndDoActionXpath(proceed);

		String searchText = "//*[@name='searchtext']";

		Normal_Methods.waitAndType(searchText, "Religare");

		String searchicon = "//*[@role='button']//child::span[@class='serach-wrapper']";

		Normal_Methods.waitAndDoActionXpath(searchicon);

		String viewAll1 = "//*[text()='+ View All Feature Details']";

		Normal_Methods.waitToVisibleElements(viewAll1);

		List<WebElement> viewAll = driver.findElements(By.xpath("//*[text()='+ View All Feature Details']"));

		for (WebElement view : viewAll) {
			System.out.println("The 1st view is " + view.getText());
			view.click();
			break;
		}

		String Minpremium = driver.findElement(By.xpath("//*[@name='premiumMinInput']")).getAttribute("value");

		System.out.println("Minimum premium is " + Minpremium);

		String Maxpremium = driver.findElement(By.xpath("//*[@name='premiumMaxInput']")).getAttribute("value");

		System.out.println("Maximum premium is " + Maxpremium);

	}
}*/