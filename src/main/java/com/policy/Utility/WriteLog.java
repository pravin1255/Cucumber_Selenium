package com.policy.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import static com.policy.Utility.Constant.driver;

public class WriteLog {
	
	
	public static int step_counter=0;
	
	public static Date dateobj1;
	
	/*public static String FOLDER_PATH;
	public static String LOG_PATH;
	public static String SCR_PATH;
	public static String ABSOLUTE_PATH;
	*/
	/*public static void writeLogFile(String stepName,String desc,String status)
	{
		BufferedWriter bw=null;
		FileWriter fw=null;
		Global.timer=new Date();
		
		try
		{
			DateFormat df2=new SimpleDateFormat("HH:mm:ss");
			Date dateobj2=new Date();
			df2.format(dateobj2);
			
			//long tempTime=dateobj2.getTime()-dateobj1.getTime(); old line
			long tempTime=dateobj2.getTime()-ScenarioChecking.dateobj1.getTime();//new line
			
			tempTime=tempTime/1000;
			
			int sec=(int)(tempTime%60);
			
			int min=(int)(tempTime/60)%60;
			
			String timeVar=" min "+min+" sec "+sec;
			
			String content=++step_counter+" | "+stepName+" | "+desc+" | "+status.toUpperCase()+" | "+getScreenshot("ScreenshotName "+step_counter)+" | "+timeVar;
			
			fw=new FileWriter(Global.LOG_PATH,true);
			
			bw.write(content);
			
			bw.newLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}*/

	private static String getScreenshot(String name) 
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File scrFile=null;
		
		try
		{
			scrFile=new File(Global.FOLDER_PATH+"//"+name+".png");
			FileUtils.copyFile(src, scrFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return scrFile.getPath();
	}
	
	public static String createReportingFolder(String txtFileName)
	{
		String FileName="";
		String SubFolder="";
		
		FileName=new Date().toString().substring(0,10);
		FileName=FileName+StringUtils.right(new Date().toString(),4);
		
		SubFolder=new Date().toString().substring(0,19);
		SubFolder=SubFolder.replace(":", "_");
		
		System.out.println("SUB_FOLDER "+SubFolder);
		
		File file=new File("target//OUTPUT_HTML//"+FileName);
		
		Global.FOLDER_PATH="target//OUTPUT_HTML//"+FileName+"//"+SubFolder;
		
		Global.LOG_PATH="target//OUTPUT_HTML//"+FileName+"//"+SubFolder+"//"+txtFileName;
		
		if(!file.exists())
		{
			if(file.mkdirs())
			{
				System.out.println("Directory created successfully");
			}
			else
			{
				System.out.println("Error creating directory");
			}
		}
		else
			System.out.println("File is already present");
		
		Global.SCR_FOLDER_PATH="..//"+SubFolder+"//";
		
		File scrFile=new File("target//OUTPUT_HTML//"+FileName+"//"+SubFolder);
		
		if(!scrFile.exists())
		{
			if(scrFile.mkdir())
			{
				System.out.println("Sccreenshot folder created ssuccessfully");
				
				File sourceLocation=new File(System.getProperty("user.dir")+"//target//cucumber-reports");
				File targetLocation = new File("target//OUTPUT_HTML//"+FileName+"//"+SubFolder);
				try {
					FileUtils.copyDirectory(sourceLocation, targetLocation);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("error in creating screenshot folder");
			}
		}
		else
			System.out.println("Screenshot folder already created");
		
		
		
		try
		{
			File files=new File(Global.LOG_PATH);
			
			Boolean fvar=files.createNewFile();
			
			if(fvar)
			{
				System.out.println("OUTPUT text file has been successfully created");
				Global.ABSOLUTE_LOG_PATH=files.getPath();
			}
			else
			{
				System.out.println("Log file already created");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		/*File sourceLocation=new File(System.getProperty("user.dir")+"//target//cucumber-reports");
		File targetLocation = new File("target//OUTPUT_HTML//"+FileName);
		try {
			FileUtils.copyDirectory(sourceLocation, targetLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return FileName;
	}	
}
