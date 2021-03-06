package com.policy.Utility;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.ExitCode;

import com.cucumber.listener.Reporter;
//import com.cucumber.listener.Reporter;
import com.google.common.base.Function;
import com.policy.cucumberTest.ChromeDriverEx;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import static com.policy.Utility.javascriptMethods.highlight;

//This is the normal methods
public class Normal_Methods extends BaseFactoryClass 
{
	LinkedHashMap<String, LinkedHashMap<String,String>> outerMap;
	LinkedHashMap<String,String> innerMap;
	LinkedHashMap<String,String> tempMap;
	int rowCount;
	int colCount;
	public static String dest;
	public static Robot re;
	public static Alert al;
	
	HSSFSheet sheet;
	
	public int count;
	private String backgroundProperty;
	JavascriptExecutor jsExecutor=getJavascriptExecutor();
	static ChromeDriverEx driver=getChromeDriver();
	
	
	public void waitUntilVisible(WebElement element){
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(Exception.class);
	}
	
	public void waitAndDoActionXpath(final String xpathName) {
		count = 1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking " + xpathName + " " + count);
				count++;
				WebElement element = input.findElement(By.xpath(xpathName));

				try {
					if (element.isDisplayed() && element.isEnabled()) {
						highlight(element);
						return true;
					}
				} catch (Exception e) {
					throw new RuntimeException("Element not present " + element);
				}
				return false;
			}
		};
		wait.until(function);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathName))).click();//new condition on 15-10-18
	}
	
	public void dropDownSelect(String drop, String value) {
		List<WebElement> ele = driver.findElements(By.xpath(drop));
		System.out.println("The value is "+value);
		int i=1;
		for (WebElement ele1 : ele) {
			System.out.println("Count of i "+i);
			System.out.println("Entered list");
			System.out.println("ELE1 "+ele1.getText()+" value "+value);
			if (ele1.getText().equals(value)) {
				//System.out.println("Ele1 is " + ele1.getText());
				highlight(ele1);
				ele1.click();
				try {
					//Robot rb = new Robot();
					//rb.keyPress(KeyEvent.VK_ESCAPE);
					//rb.keyRelease(KeyEvent.VK_ESCAPE);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			i++;
		}
	}
	
	public void waitToVisible(final String xpathName) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {

				System.out.println("Before waiting " + xpathName + " " + count);
				count++;

				WebElement element = input.findElement(By.xpath(xpathName));
				try {
					if (element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}	
				} catch (Exception e) {
					throw new RuntimeException("The elemnt is not visible " + xpathName);
				}
				return false;
			}
		};

		wait.until(function);
		System.out.println("After waitToVisible method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName)));
	}
	
	public void waitToVisibleElements(String xpathName) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathName)));
	}
	
	public void waitAndType(final String xpathName,String value) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(Constant.wait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {

				System.out.println("Before waiting " + xpathName + " " + count);
				count++;

				WebElement element = input.findElement(By.xpath(xpathName));
				try {
					if (element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}	
				} catch (Exception e) {
					throw new RuntimeException("The elemnt is not visible " + xpathName);
				}
				return false;
			}
		};

		wait.until(function);
		System.out.println("After waitAndType method");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).sendKeys(value);;
	}
	
	public void enterTextinAutoCompletion(String xpathName, String value)
	{
		Actions builder = new Actions(driver);

		WebElement textbox = driver.findElement(By.xpath(xpathName));
		Action seriesOfAction = builder.moveToElement(textbox).click().sendKeys(value).build();
		
		seriesOfAction.perform();	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		/*Action series1 = builder.moveToElement(textbox).sendKeys(Keys.ARROW_DOWN).build();

		series1.perform();
*/		
		Action series2 = builder.moveToElement(textbox).sendKeys(Keys.ENTER).build();

		series2.perform();
	}
	
	public void waitUntilElementIsClickable(String text,long time)
	{
		System.out.println("Before waiting");
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(text))).click();
		System.out.println("After wait");
	}
	
	//Method to get the current time and Date
	public String getTimeAndDate()
	{
		DateFormat date1=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date date=new Date();
		return date1.format(date);
	}
	
	public void clickElement(String xpathName)
	{
		highlightGreen(xpathName);
		WebElement element=driver.findElement(By.xpath(xpathName));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
		waitUntilElementIsClickable(xpathName, 20);
		driver.findElement(By.xpath(xpathName)).click();
	}

	private void highlightGreen(String xpathName) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid green');", driver.findElement(By.xpath(xpathName)));		
	}
	
	public void readDataFromExcel(String sheetName)
	{
		try
		{
			FileInputStream fis=new FileInputStream("TestData.xls");
			
			try(HSSFWorkbook workbook=new HSSFWorkbook(fis);)
			{
				outerMap=new LinkedHashMap<>();
				
				sheet=workbook.getSheet(sheetName);
				
				System.out.println("The sheet name is "+sheet);
				
				//row count starts from zero so the count will be 2 if there are 3 row in sheet
				rowCount=sheet.getLastRowNum();
				
				System.out.println("The rowcount is "+rowCount);
				
				colCount=sheet.getRow(0).getLastCellNum();
				
				//column count starts from 1 but for getColumn we should always start from 0 and not from 1 and column count will 
				//be 5 if there are 5 column
				System.out.println("The column count is "+colCount);
				
				for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					innerMap=new LinkedHashMap<String,String>();
					
					//here j is starting from 1st column i.e Country column so it should be less than lastCellNum
					//If we take less than equal to than we will get NullPointerException
					//here for j<sheet.getRow(i) here i is important. It should not be  zero if we take 0 than
					//we will get null pointer exception. to know use sheet Data
					for(int j=1;j<sheet.getRow(i).getLastCellNum();j++)
					{
						System.out.println("value 1 "+sheet.getRow(0).getCell(j).getStringCellValue());
						System.out.println("value 2 "+sheet.getRow(i).getCell(j).getStringCellValue());
						innerMap.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
					}
					outerMap.put(sheet.getRow(i).getCell(0).getStringCellValue(), innerMap);
				}
				System.out.println(outerMap);
			}				
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeDataToExcel(String sheetName,String testcaseName,String value)
	{
		try
		{
			FileInputStream fis=new FileInputStream("TestData.xls");
			
			try(HSSFWorkbook workbook=new HSSFWorkbook(fis);)
			{
				sheet=workbook.getSheet(sheetName);
				
				System.out.println("The sheet name is "+sheet);
				
				//row count starts from zero so the count will be 2 if there are 3 row in sheet
				rowCount=sheet.getLastRowNum();
				
				System.out.println("The rowcount is "+rowCount);
				
				colCount=sheet.getRow(0).getLastCellNum();
				
				//column count starts from 1 but for getColumn we should always start from 0 and not from 1 and column count will 
				//be 5 if there are 5 column
				System.out.println("The column count is "+colCount);
				
				for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
						for(int j=1;j<sheet.getRow(i).getLastCellNum();j++)
						{
							System.out.println("Type :"+sheet.getRow(i).getCell(j).getCellType());
							System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
							System.out.println("ENTERED IF");
							//for getStringCellValue to return empty in excel sheet in 
							//which cell you need to write value just put 1 apostrophe i.e '
							if(sheet.getRow(i).getCell(j).getStringCellValue().isEmpty())
							{
								System.out.println("ENTERED SECOND IF STATEMENT");
								sheet.getRow(i).getCell(j).setCellValue(value);
								
								FileOutputStream fos=new FileOutputStream("TestData.xls");
								
								workbook.write(fos);
								break;
							}
						}
					}										
				}
			}				
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String accessTestData(String testCaseName, String fieldName)
	{
		tempMap=new LinkedHashMap<>();
		
		tempMap=outerMap.get(testCaseName);
		
		System.out.println("Field value is "+tempMap.get(fieldName));
		
		String value=tempMap.get(fieldName);
		
		return value;
	}

	public static String capture(WebDriver driver, String screenShotName)
	{
		String dest=null;
		try
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			dest=Constant.screehsotPath+"\\"+screenShotName+".png";
			File destination=new File(dest);
			FileUtils.copyFile(source, destination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dest;
	}
	
	public static String captureFullScreen(String screenShotName)
	{
		String dest=null;
		try
		{
			File source=driver.getFullScreenshotAs(OutputType.FILE);
			dest=Constant.screehsotPath+"\\"+screenShotName+".png";
			File destination=new File(dest);
			FileUtils.copyFile(source, destination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dest;
	}
	
	public void switchToNewTab()
	{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
	}
	
	public void switchToOldTab()
	{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(0));
	}
	
	public void getBackgroundColor(String xpathName)
	{
		String backgroundColor=driver.findElement(By.xpath(xpathName)).getCssValue("background-color");
		Reporter.addStepLog("Background Color <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+backgroundColor+"</font>");
	    System.out.println("background color is "+backgroundColor);
	    String hex=Color.fromString(backgroundColor).asHex();
	    Reporter.addStepLog("Background Color in HEX <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+hex+"</font>");
	    System.out.println("Colors in hex "+hex);	   
	}
	
	//Here property can be color, fill, box-sizing, margin, padding etc
	public void getBackgroundColor(String xpathName,String property)
	{
		String backgroundProperty=driver.findElement(By.xpath(xpathName)).getCssValue(property);
		Reporter.addStepLog("Background "+property+"<font style=\"color:white;background-color:rgb(251, 100, 27);\">"+backgroundProperty+"</font>");
	    System.out.println("background color is "+backgroundProperty);
	    String hex=Color.fromString(backgroundProperty).asHex();
	    Reporter.addStepLog("Background Color in HEX <font style=\"color:white;background-color:rgb(251, 100, 27);\">"+hex+"</font>");
	    System.out.println("Colors in hex "+hex);	   
	}
	
	public void switchToFrame(String csstext)
	{
		WebElement element=driver.findElement(By.cssSelector("[id*='"+csstext+"']"));
		driver.switchTo().frame(element);
	}
	
	public boolean verifyCondition(String xpath,String text)
	{
		System.out.println("Entered verifyCondition method");
		
		String element=driver.findElement(By.xpath(xpath)).getText();
		
		System.out.println("ELEMENT "+element);
		
		boolean flag=element.contains(text);
		
		return flag;
	}
	
	public static void waitUntilTextIsVisible(String xpath,String text)
	{
		System.out.println("Waiting for text "+text);
		new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), text));
		System.out.println("After waitUntilTextIsVisible method");
	}
	
	public void waitAndTypeCss(String css, String text)
	{
		System.out.println("Waiting for css "+css);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css))).clear();
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css))).sendKeys(text);
		System.out.println("After waiting for CSS");
	}
	
	public void waitAndDoActionCss(final String cssName)
	{
		count=1;
		
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).
								   pollingEvery(1, TimeUnit.SECONDS).
								   withTimeout(30, TimeUnit.SECONDS).
								   ignoring(NoSuchElementException.class).
								   ignoring(StaleElementReferenceException.class).
								   ignoring(WebDriverException.class).
								   ignoring(Exception.class);
		
		Function<WebDriver, Boolean> function=new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking "+cssName+" "+count);
				count++;
				WebElement element=driver.findElement(By.cssSelector(cssName));
				
				try
				{
					if(element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("ELEMENT NOT PRESENT "+element);
				}
				return false;
			}
		};
		wait.until(function);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssName))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssName))).click();
	}
	
	public void waitToVisibleCss(final String cssName)
	{
		count=1;
		
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).
								   pollingEvery(1, TimeUnit.SECONDS).
								   withTimeout(30, TimeUnit.SECONDS).
								   ignoring(NoSuchElementException.class).
								   ignoring(StaleElementReferenceException.class).
								   ignoring(WebDriverException.class).
								   ignoring(Exception.class);
		
		Function<WebDriver, Boolean> function=new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking "+cssName+" "+count);
				count++;
				WebElement element=driver.findElement(By.cssSelector(cssName));
				
				try
				{
					if(element.isDisplayed() && element.isEnabled())
					{
						highlight(element);
						return true;
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("ELEMENT NOT PRESENT "+element);
				}
				return false;
			}
		};
		wait.until(function);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssName)));
	}
	
	/*
	 * Waits for the element to be invisible
	 */
	public void waitToInvisible(String cssName)
	{
		System.out.println("Waiting for the element to be invisible");
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssName)));
		System.out.println("Element is invisible");
	}
	
	public void scrollIntoView(String xpathName)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(xpathName)));
	}
	
	public void jsclick(String xpathName)
	{
		JavascriptExecutor ex = (JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathName)));
	}
	
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
	
	public void scrollBottomOfPage(){
		JavascriptExecutor ex = (JavascriptExecutor)driver;
		ex.executeScript("window.scrollBy(0,1000)");
	}
	
	//Used for mouse hover on an element and click on the child element which is displayed when we do mouse hover	
	public void mouseHover(String... xpath)
	{
		Actions act = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    //WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parentxpath)));
	    WebElement parent = driver.findElement(By.xpath(xpath[0]));
	    act.moveToElement(parent).perform();
	    if(xpath.length>1) {
	    	WebElement child = driver.findElement(By.xpath(xpath[1]));
		    child.click();
	    }
	}
	
	//Used for mouse hover on an element and click on the child element which is displayed when we do mouse hover	
	public void mouseHoverCss(String parentCss, String childCss)
	{
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement parent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
		act.moveToElement(parent).perform();
		WebElement child = driver.findElement(By.cssSelector(childCss));
		child.click();
	}
	
	public void assertionCheck(String userInput, String uiText)
	{
		//Assert.assertTrue(userInput+" is not matched with "+uiText, userInput.contains(uiText));
		Assert.assertTrue(userInput.contains(uiText), userInput+" is not matched with "+uiText);		
		System.out.println("ASSERTION PASSED");
	}
	
	public void back()
	{
		driver.navigate().back();
	}
	
	/*
	 * This method is used when we get error in chromeBrowser saying element is not clickable at point (x,x)
	 * This error i.e WebDriverException only comes in Chrome browser
	 */
	public void clickXpath(String xpathName)
	{
		WebElement element = driver.findElement(By.xpath(xpathName));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
	/*
	 * This method is used when we get error in chromeBrowser saying element is not clickable at point (x,x)
	 * This error i.e WebDriverException only comes in Chrome browser
	 */
	public void clickCss(String cssName)
	{
		WebElement element = driver.findElement(By.cssSelector(cssName));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();						
	}
	
	public void dropDownSelectUsingSelect(String xpathName,String value)
	{
		waitAndDoActionXpath(xpathName);
		Select select=new Select(driver.findElement(By.xpath(xpathName)));
		select.selectByVisibleText(value);
	}
	
	public void uploadFile(String fileName)
	{
		StringSelection stringSelection=new StringSelection(fileName);
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		Robot robot=null;
		
		try {
			robot=new Robot();
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//robot.delay(2000);
	    robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(150);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);		
	}
	
	private FluentWait<WebDriver> configureWait() {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
							   .withTimeout(30, TimeUnit.SECONDS)
							   .pollingEvery(5, TimeUnit.SECONDS)
							   .ignoring(NoSuchElementException.class);
		
		return wait;
	}
	
	public void waitAndType(WebElement element,String message) {
		FluentWait<WebDriver> wait = configureWait();
		element = wait.until(ExpectedConditions.visibilityOf(element));
		if(element.isEnabled()) {
			//element.clear();
			element.sendKeys(message);
		}
	}
	
	public void waitAndClick(WebElement element) {
		FluentWait<WebDriver> wait = configureWait();
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		if(element.isDisplayed() && element.isEnabled())
			element.click();
	}
	
	public void selectDropdown(String text, String xpathName){
		WebElement ele=driver.findElement(By.xpath(xpathName));
		Select select =new Select(ele);
		select.selectByVisibleText(text);
	}
	
	public void selectDropDownList(String xpathName, String text){
		List<WebElement> list=driver.findElements(By.xpath(xpathName));
		System.out.println("The size of the list is "+list.size());
		list.forEach(action->{
			if(action.getText().equals(text)) {
				System.out.println("The text is: "+text);
				action.click();
				return;
			}
		});
		
//		for(WebElement ele:list){
//			if(ele.getText().equals(text)){
//				System.out.println("The Text is "+text);
//				ele.click();
//				break;
//			}				
//		}
	}
	
	public void selectCheckBox(String value){
		String xpath="//*[text()='"+value+"']//parent::div//preceding-sibling::div[@class='checkbox_container']";
		waitToVisible(xpath);
		waitAndDoActionXpath(xpath);
	}
	
	public void selectRadioButton(String value){
		String xpath="//*[text()='"+value+"']//ancestor::div[@class='top_quotes_content']//following-sibling::div//*[@class='checkbox_container']";
		waitToVisible(xpath);
		waitAndDoActionXpath(xpath);
	}
	
	public String getSelectedOptions(String xpath){
		Select select=new Select(driver.findElement(By.xpath(xpath)));
		List<WebElement>ele=select.getAllSelectedOptions();
		String selectedOption=ele.get(0).getText();
		System.out.println("The premium amount is "+selectedOption);
		return selectedOption;
	}
	
	public String getTextFromUI(String xpath){
		waitToVisible(xpath);
		String text=driver.findElement(By.xpath(xpath)).getText();
		System.out.println("The Text from UI is "+text);
		return text;
	}
	
	public String getTextFromUI(String... xpath){
		boolean flag1=isDisplayed(xpath[0]);
		boolean flag2=isDisplayed(xpath[1]);
		if(flag1) {
			String text=driver.findElement(By.xpath(xpath[0])).getText();
			System.out.println("The Text from UI is "+text);
			return text;	
		}
		else if (flag2==true){
			{
				String text=driver.findElement(By.xpath(xpath[1])).getText();
				System.out.println("The Text from UI is "+text);
				return text;	
			}
		}
		return null;
	}
	
	public static int getDifference(int i, int j) {
		System.out.println("The difference is "+(i-j));
		return i-j;
	}

	public static int getInteger(String text){
		String s1=text.substring(text.indexOf("₹")+2, text.indexOf("monthly")-1).replace(",", "");
		int num=Integer.valueOf(s1);
		System.out.println("The num is "+num);
		return num;
	}
	
	public void addScreenshot(String text){
		String capture=Normal_Methods.capture(driver, text);
		try {
			Reporter.addScreenCaptureFromPath(capture, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	interface interf {
		String num(int numValue);
	}
	
	public void getDateAndYear(int num) {
		interf i = a -> {
			switch (a) {
			case 1:
				return String.valueOf("01");
			case 2:
				return String.valueOf("02");
			case 3:
				return String.valueOf("03");
			case 4:
				return String.valueOf("04");
			case 5:
				return String.valueOf("05");
			case 6:
				return String.valueOf("06");
			case 7:
				return String.valueOf("07");
			case 8:
				return String.valueOf("08");
			case 9:
				return String.valueOf("09");
			default:
				System.out.println("Not proper num");
			}
			return "Not Proper Num";
		};
		if (num <= 9)
			System.out.println(i.num(num));
	}
	
	public void waitUntilVisibleFluent(String xpath) {
		WebElement ele = driver.findElement(By.xpath(xpath));
		System.out.println("Enter waitUntilVisible fluent wait method");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.withTimeout(60, TimeUnit.SECONDS);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				if (ele.isDisplayed()) {
					return true;
				} else {
					throw new RuntimeException("The element is not visible");
				}
			}
		});
	}
	
	public static void saveFullPageScreenshot(String name) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "PNG", new File(name));

	}
	
	public static String takeFullpageScreenshot() {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(), "PNG",
					new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/"));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dest;
	}
	
	/* To Capture Screenshot(Replaces if already exists) */
	/*
	 * Also, Make sure that the automation in running in the foreground to capture
	 * the Image of the Browser. Else, It'll capture the open Window
	 */
	public void robotScreenCapture(String robotImageName) throws Exception {
		re = new Robot();
		Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage bufferedImage = re.createScreenCapture(area);
		// Save as PNG
		File file = new File(robotImageName);
		if (file.exists()) {
			file.delete();
		}
		ImageIO.write(bufferedImage, "png", file);
	}

	/* To ZoomOut */
	public void robotZoomOut() throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}

	/* To ZoomIn */
	public void robotZoomIn() throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}

	/* To ScrollUp using ROBOT */
	public void robotScrollUp() throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_UP);
		re.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	
	/* To ScrollDown using ROBOT */
	public void robotScrollDown() throws Exception {
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_DOWN);
		re.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	/* To ScrollUp using JavaScript Executor */
	public void scrollUp() throws Exception {
		((JavascriptExecutor) driver).executeScript("scroll(0, -100);");
	}

	/* To ScrollDown using JavaScript Executor */
	public void scrollDown() throws Exception {
		((JavascriptExecutor) driver).executeScript("scroll(0, 100);");
	}

	/* To Move cursor to the Desired Location */
	public void moveCursor(int X_Position, int Y_Position) throws Exception {
		re.mouseMove(X_Position, Y_Position);
	}

	/* To Accept the Alert Dialog Message */
	public void alertAccept() throws Exception {
		al = driver.switchTo().alert();
		al.accept();
	}

	/* To Dismiss the Alert Dialog Message */
	public void alertDismiss() throws Exception {
		al = driver.switchTo().alert();
		al.dismiss();
	}
	
	/* To Get Tooltip Text */
	public String getTooltipText(WebElement element) {
		String tooltipText = element.getAttribute("title").trim();
		return tooltipText;
	}
	
	/*
	 * Scroll to the Bottom of the Page
	 */
	public void scrollToPageBottom() {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectationLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(2000);
			WebDriverWait waitForLoad = new WebDriverWait(driver, 33);
			waitForLoad.until(expectationLoad);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static void waitForAjaxFinished() {
		ExpectedCondition<Boolean> expectationAjax = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0"));
			}
		};
		try {
			Thread.sleep(2000);
			WebDriverWait waitForAjax = new WebDriverWait(driver, 33);
			waitForAjax.until(expectationAjax);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Ajax Finished to complete.");
		}
	}
	
	public boolean isDisplayed(WebElement element) {
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean status = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				status = element.isDisplayed();
				break;
			} catch (StaleElementReferenceException e) {
			} catch (NoSuchElementException e) {
			}catch (Exception e) {
				}
			attempts++;
		}
		return status;
	}
	
	public boolean isDisplayed(String xpathName) {
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		boolean status = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				status=driver.findElement(By.xpath(xpathName)).isDisplayed();
				break;
			} catch (StaleElementReferenceException e) {
			} catch (NoSuchElementException e) {
			}catch (Exception e) {
				}
			attempts++;
		}
		return status;
	}
	
	private static ExpectedCondition<Boolean> textDisplayed2(final By elementFindBy, final String text) {
		return input -> input.findElement(elementFindBy).getText().contains(text);
	}
	
	public ExpectedCondition<Boolean> untilVisible(String xpath){
		System.out.println("Xpath: "+xpath);
		return input->input.findElement(By.xpath(xpath)).isDisplayed() ;
	}	
	
	private static ExpectedCondition<Boolean> textDisplayed(final By elementFindBy, final String text){
		return new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				return input.findElement(elementFindBy).getText().contains(text);
			}
		};
			
	}
}