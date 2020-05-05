/**
 * 
 */
package com.policy.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * @author PUNITH
 *
 */
public class javascriptMethods extends Normal_Methods{
	static JavascriptExecutor jsExecutor=getJavascriptExecutor();
	
	public static void highlight(WebElement element){
		jsExecutor.executeScript("arguments[0].setAttribute('style','border:2px solid red');", element);
	}
	
	public static void jsClick(String xpath) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
	}
}