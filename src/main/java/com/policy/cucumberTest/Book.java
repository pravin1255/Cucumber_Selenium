package com.policy.cucumberTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class Book
{
	LinkedHashMap<String,LinkedHashMap<String,String>> outerMap;
	LinkedHashMap<String,String> innerMap;
	LinkedHashMap<String,String> tempMap;
	
	public void readDataFromExcel(String sheetName)
	{
		try
		{
			FileInputStream fis=new FileInputStream("TestData.xls");
			
			try(HSSFWorkbook wk=new HSSFWorkbook(fis);)
			{
				HSSFSheet sheet=wk.getSheet(sheetName);
				
				outerMap=new LinkedHashMap<>();
				
				for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					innerMap=new LinkedHashMap<>();
					
					for(int j=1;j<sheet.getRow(i).getLastCellNum();j++)
					{
						innerMap.put(sheet.getRow(0).getCell(j).getStringCellValue(), sheet.getRow(i).getCell(j).getStringCellValue());
					}
					
					outerMap.put(sheet.getRow(i).getCell(0).getStringCellValue(), innerMap);
				}
				
				System.out.println(outerMap);
			}
		}
		catch(IOException e)
		{
			
		}
	}
	
	public void accessTestData(String testCaseName,String fieldName)
	{
		tempMap=new LinkedHashMap<>();
		
		tempMap=outerMap.get(testCaseName);
		
		String value=tempMap.get(fieldName);
		
		System.out.println(fieldName+" "+value);
	}
	
	public static void main(String[] args) {
		Book b=new Book();
		b.readDataFromExcel("Data");
		b.accessTestData("TC1","Country");
		
	}
}