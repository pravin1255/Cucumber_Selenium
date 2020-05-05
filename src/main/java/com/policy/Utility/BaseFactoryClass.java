/**
 * 
 */
package com.policy.Utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.policy.cucumberTest.ChromeDriverEx;

import static com.policy.Utility.Constant.driver;

/**
 * @author PUNITH
 *
 */
public class BaseFactoryClass {
	static JavascriptExecutor javascriptExecutor;
	static ChromeDriverEx driver;
	
	public static JavascriptExecutor getJavascriptExecutor(){
		if(javascriptExecutor==null) {
			javascriptExecutor=(JavascriptExecutor)driver;			
		}
		return javascriptExecutor;
	}
	
	public static ChromeDriverEx getChromeDriver() {
		if(driver==null) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("disable-infobars");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//java//com/policy//resources//chromedriver.exe");
	    	try {
				driver=new ChromeDriverEx(options);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}	
}