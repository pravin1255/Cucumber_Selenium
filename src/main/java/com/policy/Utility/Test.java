package com.policy.Utility;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
 
 
public class Test {
	public static void main(String[] args) {
		String flow="Requester$Approver 1$Approver 2";
		while(flow.length()!=0) {
			if(flow.length()>0) {
				String groupName=flow.split("[$]")[0];		
				System.out.println("GroupName 1"+groupName);
				flow=flow.replaceAll(groupName+"[$]", "");
				//flow=flow.substring(1);
				System.out.println("flow: "+flow);
			}else {
				System.out.println("NO USER TO LOGIN");
				return;
			}	
		}
	}
}