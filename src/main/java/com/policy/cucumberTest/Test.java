package com.policy.cucumberTest;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
public class Test
{
	public static void main(String[] args)
	{
		String flow="HeadOfHR(DONE)>Manager>TeamLeader";
		
		if(flow.lastIndexOf("(DONE)")>0)
		{
			flow=flow.substring(flow.lastIndexOf("(DONE)")+7);
		}
		
		String groupName=flow.split(">")[0];
		
		System.out.println(groupName);
		flow=groupName+"(DONE)"+flow.replaceAll(groupName, "");
		System.out.println(flow);
		
		try {
			fisacLogin(groupName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void fisacLogin(String groupName) throws Exception
	{
		HashMap<String,String> getUserInfor=getUserData();
		
		String userinfo=getUserInfor.get(groupName);
		
		String username=userinfo.split(";")[0];
		String UID=userinfo.split(";")[1];
		String password=userinfo.split(";")[2];
		System.out.println("USERNAME "+username);
		System.out.println("UID "+UID);
		System.out.println("PASSWORD "+password);
	}
	
	public static HashMap<String,String> getUserData()throws Exception
	{
		HashMap<String,String> map=new HashMap<>();
		
		FileInputStream fis=new FileInputStream("TestData.xls");
		HSSFWorkbook wk=new HSSFWorkbook(fis);
		HSSFSheet sheet=wk.getSheet("User2");
		int rowNum=sheet.getPhysicalNumberOfRows();
		
		for(int i=1;i<rowNum;i++)
		{
			String userInfo=sheet.getRow(i).getCell(0).getStringCellValue();
			String userName=sheet.getRow(i).getCell(1).getStringCellValue();
			String UID=sheet.getRow(i).getCell(2).getStringCellValue();
			String pass=sheet.getRow(i).getCell(3).getStringCellValue();
			
			map.put(userInfo, userName+";"+UID+";"+pass);			
		}
		return map;
	}
}