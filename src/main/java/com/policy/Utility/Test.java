package com.policy.Utility;

import java.util.Scanner;
public class Test
{
	public static void main(String[] args)
	{
		String str="Requester$Approver 1$Approver 2";
		
		String[] s1=str.split("[$]");
		
		for(String s:s1)
		{
			System.out.println(s);
		}
	}
}