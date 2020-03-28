package com.policy.Utility;

import static com.policy.Utility.Constant.driver;

import java.io.FileInputStream;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class General_Methods {
	LinkedHashMap<String,LinkedHashMap<String,String>> outerMap;
	LinkedHashMap<String,String> innerMap;
	LinkedHashMap<String,String> tempMap;
	int rowCount;
	int colCount;
	HSSFSheet sheet;
	
	public void readDataFromExcel(String sheetName){
		try{
			FileInputStream fis=new FileInputStream("TestData.xls");
			try(HSSFWorkbook workbook=new HSSFWorkbook(fis);){
				outerMap=new LinkedHashMap<>();
				//innerMap=new LinkedHashMap<>();//The outermap output is not right when we initialize innerMap here
				sheet=workbook.getSheet(sheetName);
				rowCount=sheet.getLastRowNum();
				colCount=sheet.getRow(0).getLastCellNum();
				for(int i=1;i<=sheet.getLastRowNum();i++){
					innerMap=new LinkedHashMap<>();
					for(int j=1;j<sheet.getRow(0).getLastCellNum();j++){
						System.out.println("sheet.getRow(0).getCell("+j+").getStringCellValue()"+sheet.getRow(0).getCell(j).getStringCellValue());
						System.out.println("sheet.getRow("+i+").getCell("+j+").getStringCellValue()"+sheet.getRow(i).getCell(j).getStringCellValue());
						innerMap.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());						
					}
					outerMap.put(sheet.getRow(i).getCell(0).getStringCellValue(), innerMap);
				}	
			}
			System.out.println("The outerma is "+outerMap);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void waitUntilElementIsClickable(String text, long time) {
		if (text.contains("//*")) {
			System.out.println("Before waiting");
			new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(text))).click();
			System.out.println("After wait");
		} else {
			System.out.println("Before waiting");
			new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.cssSelector(text)))
					.click();
			System.out.println("After wait");
		}
	}
	
	public static void waitUntilElementIsVisible(String text, long time){
		if(text.contains("//*")){
			System.out.println("Before waiting ");
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(text)));
			System.out.println("After waiting");
		}else{
			System.out.println("Before waiting ");
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(text)));
			System.out.println("After waiting");
		}		
	}
	
	public static void waitAndClick(String text, long time){
		if(text.contains("//*")){
			System.out.println("Before waiting ");
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(text))).click();;
			System.out.println("After waiting");
		}else{
			System.out.println("Before waiting ");
			new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(text))).click();;
			System.out.println("After waiting");
		}		
	}
	
	public static <T>  void testMethod(T t){
		if(t instanceof By){
			System.out.println("String method");			
		}
		else if(t instanceof Integer){
			System.out.println("Integer method");
		}
	}
}