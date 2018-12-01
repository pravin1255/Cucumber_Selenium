package com.policy.stepDefinition;

import com.cucumber.listener.Reporter;
import com.policy.Utility.Normal_Methods;
import com.policy.Utility.UIMapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.policy.Utility.Constant.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

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
		
		/*
		 * This waitToVisibleCss is the most important step if we don't do wait here than it will not enter for loop in 
		 * line 2 and we will always get the size as zero in line 1
		 */
		waitToVisibleCss("[class$='imageThumbnail a-declarative']"); 
		
		List<WebElement> elements1=driver.findElements(By.cssSelector("[class$='imageThumbnail a-declarative']"));
		System.out.println("HELLO here");
		
		System.out.println("SIZE is "+elements1.size());//-----------------------LINE 1
		for(WebElement element:elements1)//--------------------------------------LINE 2 
		{
			System.out.println("EnterED FOR LOOP");
			element.findElement(By.cssSelector("[class$='a-button-focus']")).click();
			System.out.println("EnterED FOR LOOP 2nd TIME");
			capture=Normal_Methods.capture(driver, "1st item UI");
			Reporter.addScreenCaptureFromPath(capture, "1st item UI");
			Reporter.addStepLog("Clicked on item image<font style=\"color:white;background-color:rgb(251, 100, 27);\">"
					+ accessTestData("TC1", "Product") + "</font>");			
			break;
		}
		
		String prodTitle=driver.findElement(By.cssSelector("[id='productTitle']")).getText();
		
		System.out.println("Product title "+prodTitle);
		String cssSelectorvalue="[alt='"+prodTitle+"']";
		System.out.println("cssSelector value "+cssSelectorvalue);
		
		waitAndDoActionCss(cssSelectorvalue);
		capture=Normal_Methods.capture(driver, "Zoom UI");
		Reporter.addScreenCaptureFromPath(capture, "Zoom UI");
		
		waitAndDoActionCss(UIMapper.getValue("closeA"));
	}
	
	@When("^user clicks on Buy Now button$")
	public void user_clicks_on_Buy_Now_button() throws Throwable {
		waitToVisibleCss(UIMapper.getValue("buyNowA"));
		clickCss(UIMapper.getValue("buyNowA"));
	}
	
	@When("^user edits the mailing address \"(.*?)\"$")
	public void user_edits_the_mailing_address(String arg1) throws Throwable {
	    waitAndDoActionXpath(UIMapper.getValue("editA"));
	    waitToInvisible(UIMapper.getValue("spinnerA"));
	    waitAndTypeCss(UIMapper.getValue("fullNameA"), "Pravin M");
	    waitAndTypeCss(UIMapper.getValue("mobilenoA"), "9920530849");
	    
	    List<WebElement> elements=driver.findElements(By.tagName("option"));
	    
	    for(WebElement element:elements)
	    {
	    	String text=element.getAttribute("value");
	    	System.out.println("TEXT IS"+text);
	    	
	    	if(text.equalsIgnoreCase("res"))
	    	{
	    		element.click();
	    		break;
	    	}
	    }
	    waitAndTypeCss(UIMapper.getValue("addCityA"), "Mumbai");
	}
	
	@When("^user first verifies the cart image$")
	public void user_first_verifies_the_cart_image() throws Throwable {
	 
		waitToVisibleCss(UIMapper.getValue("cartA"));
		String text=driver.findElement(By.cssSelector(UIMapper.getValue("cartA"))).getText();
		capture=Normal_Methods.capture(driver, "CART image");
		Reporter.addScreenCaptureFromPath(capture, "CART image");
		Reporter.addStepLog("Before adding item to cart count of cart is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"
				+text + "</font>");
	}

	@When("^then clicks on Add to cart and again verfies the cart image$")
	public void then_clicks_on_Add_to_cart_and_again_verfies_the_cart_image() throws Throwable {
	 
		String text=driver.findElement(By.cssSelector(UIMapper.getValue("cartA"))).getText();
		
		int num=Integer.parseInt(text);
		
		waitAndDoActionCss(UIMapper.getValue("cartButtonA"));
		
		text=driver.findElement(By.cssSelector(UIMapper.getValue("cartA"))).getText();
		capture=Normal_Methods.capture(driver, "CART image 2");
		Reporter.addScreenCaptureFromPath(capture, "CART image 2");
		Reporter.addStepLog("After adding item to cart count of cart is <font style=\"color:white;background-color:rgb(251, 100, 27);\">"
				+text + "</font>");
		
		num=num+1;
		
		String text2=Integer.toString(num);
		assertionCheck(text2, text);		
	}

	@When("^goes to cart page$")
	public void goes_to_cart_page() throws Throwable {
	 
		waitAndDoActionCss(UIMapper.getValue("cartPageA"));
		capture=Normal_Methods.capture(driver, "CART PAGE");
		Reporter.addScreenCaptureFromPath(capture, "CART PAGE");
		Reporter.addStepLog("Navigating to cart page <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+ "</font>");
	}
	
	@Given("^User navigates to Men's Sport shoes section$")
	public void user_navigates_to_Men_s_Sport_shoes_section() throws Throwable {
		mouseHoverCss(UIMapper.getValue("shopAllA"), UIMapper.getValue("mensFashionA"));
		waitAndDoActionXpath("//*[contains(text(),'Sports Shoes')]");		
	}
	
	@Given("^user moves to Wishlist page$")
	public void user_moves_to_Wishlist_page() throws Throwable {

		mouseHover(UIMapper.getValue("orderA"), UIMapper.getValue("wishListA"));
		capture=Normal_Methods.capture(driver, "WishList Page");
		Reporter.addScreenCaptureFromPath(capture, "Wish List");
		Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">User is on Wishlist Page </font>");
	}

	@Given("^moves item to top of wishlist \"(.*?)\"$")
	public void moves_item_to_top_of_wishlist(String arg1) throws Throwable {

		List<WebElement> elements=driver.findElements(By.xpath(UIMapper.getValue("wishlistItemA")));
		
		/*
		 * This for loop works when adidas is in first row and we can drag the adidas file to last row
		 */
		for(WebElement e:elements)
		{
			String text1=e.getText();
			System.out.println("text1 is "+text1);
			if(text1.contains("Adidas Men's Agora 1.0 Multisport Training Shoes"))	
			{
				System.out.println("Test PASSED");
				scrollIntoView(UIMapper.getValue("wishlistItemA"));
				Actions act=new Actions(driver);
				
				act.moveToElement(e.findElement(By.xpath(UIMapper.getValue("wishlistItemA")))).build().perform();
				
				WebElement ele= driver.findElement(By.xpath("(//*[starts-with(@data-id,'2PR46')])[1]"));
				Point point = ele.getLocation();
				int xcord = point.getX();
				int ycord = point.getY();
				
				System.out.println("X cord is "+xcord);
				System.out.println("Y cord is "+ycord);
				act.dragAndDropBy(driver.findElement(By.cssSelector("[id='itemDragIcon_I1K7NMUBK9LKYB']")), 321, 729).perform();
			}
		}
		
		
		
		/*for(WebElement e:elements)
		{
			String text1=e.getText();
			System.out.println("text1 is "+text1);
			if(text1.contains("Adidas Men's Agora 1.0 Multisport Training Shoes"))	
			{
				System.out.println("Test PASSED");
				//scrollIntoView(UIMapper.getValue("wishlistItemA"));
				Actions act=new Actions(driver);
				
				act.moveToElement(e.findElement(By.xpath(UIMapper.getValue("wishlistItemA")))).build().perform();
				
				WebElement ele= driver.findElement(By.xpath("(//*[starts-with(@data-id,'2PR46')])[2]"));
				Point point = ele.getLocation();
				int xcord = point.getX();
				int ycord = point.getY();
				
				System.out.println("X cord is "+xcord);
				System.out.println("Y cord is "+ycord);
				act.dragAndDropBy(ele.findElement(By.cssSelector("[id*='itemDragIcon']")), 321, 729).perform();
			}
		}*/
	}

	@Then("^item should be displayed at top of wishlist$")
	public void item_should_be_displayed_at_top_of_wishlist() throws Throwable {

	}
	
	//@Given("^moves item to top of wishlist \"(.*?)\"$")
	@Then("^searches for the item and clicks on item \"(.*?)\"$")
	public void searches_for_the_item_and_clicks_on_item(String arg1) throws Throwable {
	
		waitAndTypeCss(UIMapper.getValue("searchA"), accessTestData(arg1, "Product"));
		
		capture=Normal_Methods.capture(driver, "search Page");
		Reporter.addScreenCaptureFromPath(capture, "search List");
		Reporter.addStepLog("User searches for <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+accessTestData(arg1, "Product")+"</font>");		
		driver.findElement(By.cssSelector(UIMapper.getValue("searchA"))).sendKeys(Keys.ENTER);		
		waitAndDoActionCss("[title*=\""+accessTestData(arg1, "Product")+"\"]");			
	}

	@Then("^User navigates to the product page$")
	public void user_navigates_to_the_product_page() throws Throwable {
	
		capture=Normal_Methods.capture(driver, "Product Page 1");
		Reporter.addScreenCaptureFromPath(capture, "Product Pages 1");
		Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">User is on Product page</font>");
	}

	@When("^user selects the size \"(.*?)\"$")
	public void user_selects_the_size(String arg1) throws Throwable {
	
		dropDownSelectUsingSelect(UIMapper.getValue("sizeA"), accessTestData(arg1, "Size"));
		capture=Normal_Methods.capture(driver, "Size image");
		Reporter.addScreenCaptureFromPath(capture, "Size image pages");
		Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">User selects the size "+accessTestData(arg1, "Size")+"</font>");
		
	}

	@When("^clicks on Buy Now button$")
	public void clicks_on_Buy_Now_button() throws Throwable {
		/*
		 * Here we have used do-while loop because in product page if we click once on buy now button
		 * it was not redirecting to delivery address page because at that time page is loading and it receives the click.
		 * So we used do-while loop. Thread.sleep can be used but its 
		 * not recommended. 
		 * Now how does do-while loop works
		 * 1st in "do" we click on buy now button and check whether image in delivery page is displayed. If its not displayed
		 * than we will get NoSuchElementException so we handled it using try-catch block,(Used Exception instead of
		 * NoSuchElementException as NoSuchElementException was not working for catch block)
		 * Now in while loop we check whether title not contains delivery page title name. In 1st click of buy now button
		 * it doesn't contain delivery page title so it return true and again it enters do-while loop
		 * Now in second time it again clicks on buy now button. This time its redirected to delivery page and 
		 * the image gets displayed and the title of the delivery page we got. Now in while loop we check again the condition 
		 * i.e title not contains delivery page title and this time it return false as it contains delivery page title 
		 * and it comes out of do-while loop		 *  
		 */
		
		int i=0;
		String title=driver.getTitle();
		do
		{
			System.out.println("Title "+title);
			i++;
			waitToVisibleCss(UIMapper.getValue("buyNowA"));
			clickCss(UIMapper.getValue("buyNowA"));
			System.out.println("I "+i);
			System.out.println("Title 2 "+title);
			try
			{
				boolean flag=driver.findElement(By.cssSelector("[src*='pickup_flag_icon']")).isDisplayed();
				if(flag)
				{
					title=driver.getTitle();
					System.out.println("Title inside if "+title);
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception catch count "+i);
			}
		}
		while(!title.contains("Enter the delivery address for this order"));
	}

	@Then("^User is taken to delivery page$")
	public void user_is_taken_to_delivery_page() throws Throwable {
	
		capture=Normal_Methods.capture(driver, "Delivery Page 1");
		Reporter.addScreenCaptureFromPath(capture, "This is the Delivery page");
		Reporter.addStepLog("<font style=\"color:white;background-color:rgb(251, 100, 27);\">User is in Delivery page </font>");
	}
	public void worflowimplementation()
	{
		
	}
}