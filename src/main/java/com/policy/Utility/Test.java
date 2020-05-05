package com.policy.Utility;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
 
 
public class Test {
	public static void main(String[] args) {
		String flow="Requester$Approver 1$Approver 2$Approver 3$Approver 4$Approver 5";
		if(flow.lastIndexOf("(DONE)")>0)
		{
			flow=flow.substring(flow.lastIndexOf("(DONE)")+7);
		}
		String groupName=flow.split("[$]")[0];
		flow=groupName+"(DONE)"+flow.replaceAll(groupName, "");
		System.out.println(flow);
		
		int indexOf=flow.lastIndexOf("(DONE)");
		System.out.println(indexOf);
		
		if(flow.lastIndexOf("(DONE)")>0)
		{
			flow=flow.substring(flow.lastIndexOf("(DONE)")+6);
		}
		groupName=flow.split("[$]")[0];
		flow=groupName+"(DONE)"+flow.replaceAll(groupName, "");
		System.out.println(flow);
		
	}
}