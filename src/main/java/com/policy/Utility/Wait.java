package com.policy.Utility;

import static com.policy.Utility.Constant.driver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

public class Wait 
{
	static int count;
	
	public static void highlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid red');", element);
	}
	
	public static void waitAndTypeFluentWaitAndExplicitWait(final String xpathName, String value)
	{	
		count=1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									 .pollingEvery(1, TimeUnit.SECONDS)
									 .withTimeout(30, TimeUnit.SECONDS)
									 .ignoring(NoSuchElementException.class)
									 .ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and typing" + xpathName + " " + count);
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).sendKeys(value);
	}
	
	public static void waitAndDoActionFluentWaitAndExplicitWait(final String xpathName) 
	{
		count = 1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									 .pollingEvery(1, TimeUnit.SECONDS)
									 .withTimeout(30, TimeUnit.SECONDS)
									 .ignoring(NoSuchElementException.class)
									 .ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting and clicking" + xpathName + " " + count);
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName))).click();
	}
	
	public static void waitTillVisibleFluentWaitAndExplicitWait(final String xpathName)
	{	
		count=1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									 .pollingEvery(1, TimeUnit.SECONDS)
									 .withTimeout(30, TimeUnit.SECONDS)
									 .ignoring(NoSuchElementException.class)
									 .ignoring(StaleElementReferenceException.class).ignoring(Exception.class);

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver input) {
				System.out.println("Before waiting till visible" + xpathName + " " + count);
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathName)));
	}
}