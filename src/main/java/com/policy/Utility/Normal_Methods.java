package com.policy.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;


import static com.policy.Utility.Constant.driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Normal_Methods 
{
	static LinkedHashMap<String, LinkedHashMap<String,String>> outerMap;
	static LinkedHashMap<String,String> innerMap;
	static LinkedHashMap<String,String> tempMap;
	static int rowCount;
	static int colCount;
	
	static HSSFSheet sheet;
	
	public static int count;
	
	public static void highlight(WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid red');", element);
	}
	
	public static void waitAndDoActionXpath(String xpathName) {
		count = 1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(30, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
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
	
	public static void dropDownSelect(String drop, String value) {
		List<WebElement> ele = driver.findElements(By.xpath(drop));

		for (WebElement ele1 : ele) {
			System.out.println("Entered list");
			if (ele1.getText().contains(value)) {
				System.out.println("Ele1 is " + ele1.getText());
				highlight(ele1);
				ele1.click();
				try {
					Robot rb = new Robot();
					rb.keyPress(KeyEvent.VK_ESCAPE);
					rb.keyRelease(KeyEvent.VK_ESCAPE);

				} catch (AWTException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	public static void waitToVisible(String xpathName) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(30, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName)));
	}
	
	public static void waitToVisibleElements(String xpathName) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(30, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathName)));
	}
	
	public static void waitAndType(String xpathName,String value) {
		count = 1;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(30, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).sendKeys(value);;
	}
	
	public static void enterTextinAutoCompletion(String xpathName, String value)
	{
		Actions builder = new Actions(driver);

		WebElement textbox = driver.findElement(By.xpath(xpathName));
		Action seriesOfAction = builder.moveToElement(textbox).click().sendKeys(value).build();

		seriesOfAction.perform();	
			
		/*Action series1 = builder.moveToElement(textbox).sendKeys(Keys.ARROW_DOWN).build();

		series1.perform();
*/		
		Action series2 = builder.moveToElement(textbox).sendKeys(Keys.ENTER).build();

		series2.perform();
	}
	
	public static void waitUntilElementIsClickable(String text,long time)
	{
		System.out.println("Before waiting");
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(text))).click();
		System.out.println("After wait");
	}
	
	//Method to get the current time and Date
	public static String getTimeAndDate()
	{
		DateFormat date1=new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		Date date=new Date();
		return date1.format(date);
	}
	
	public static void clickElement(String xpathName)
	{
		highlightGreen(xpathName);
		WebElement element=driver.findElement(By.xpath(xpathName));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
		waitUntilElementIsClickable(xpathName, 20);
		driver.findElement(By.xpath(xpathName)).click();
	}

	private static void highlightGreen(String xpathName) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid green');", driver.findElement(By.xpath(xpathName)));		
	}
	
	public static void readDataFromExcel(String sheetName)
	{
		try
		{
			FileInputStream fis=new FileInputStream("TestData.xls");
			
			HSSFWorkbook workbook=new HSSFWorkbook(fis);
			
			outerMap=new LinkedHashMap<>();
			
			sheet=workbook.getSheet(sheetName);
			
			System.out.println("The sheet name is "+sheet);
			
			rowCount=sheet.getLastRowNum();
			
			System.out.println("The rowcount is "+rowCount);
			
			colCount=sheet.getRow(0).getLastCellNum();
			
			System.out.println("The column count is "+colCount);
			
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				innerMap=new LinkedHashMap<String,String>();
				
				//here j is starting from 1st column i.e Country column so it should be less than lastCellNum
				//If we take less than equal to than we will get NullPointerException
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
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static String accessTestData(String testCaseName, String fieldName)
	{
		tempMap=new LinkedHashMap<>();
		
		tempMap=outerMap.get(testCaseName);
		
		System.out.println("Field value is "+tempMap.get(fieldName));
		
		String value=tempMap.get(fieldName);
		
		return value;
	}
}