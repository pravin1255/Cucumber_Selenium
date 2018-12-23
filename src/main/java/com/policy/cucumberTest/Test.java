package com.policy.cucumberTest;

import java.util.Map;
import java.util.LinkedHashMap;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.policy.Utility.Normal_Methods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
public class Test extends Normal_Methods
{
	
	public LinkedHashMap<String,String> m1() throws Exception
	{
		readDataFromExcel("Workflow Name");
		String flow2=accessTestData("WK3", "WorkFlow");
		//System.out.println("FLOW 2"+flow2);
		String arg1=flow2.substring(flow2.indexOf(">")+1);
		String[] arg2=arg1.split(">");
		//System.out.println(Arrays.toString(arg2));
		LinkedHashMap<Integer,String> map=new LinkedHashMap<>();
		LinkedHashMap<String,String> innerMap=new LinkedHashMap<>();
		for(int i=0;i<arg2.length;i++)
		{
			map.put(i+1, arg2[i]);
			System.out.println(map);
			
			readDataFromExcel("User2");
			
			System.out.println("APPROVER "+(i+1)+" "+accessTestData(map.get(i+1), "Username"));		
			
			
			
			innerMap.put("APPROVER "+(i+1), accessTestData(map.get(i+1), "Username"));
		}
		System.out.println(innerMap);
		return innerMap;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		/*String flow="HeadOfHR(DONE)>Manager>TeamLeader";
		
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
			e.printStackTrace();
		}*/
		
		Test t=new Test();
		
		LinkedHashMap<String,String> map1=t.m1();
		
		System.out.println(map1.get("APPROVER 2"));
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
			String profileName=sheet.getRow(i).getCell(1).getStringCellValue();
			String username=sheet.getRow(i).getCell(2).getStringCellValue();
			String pass=sheet.getRow(i).getCell(3).getStringCellValue();
			
			map.put(userInfo, profileName+";"+username+";"+pass);			
		}
		return map;
	}
}