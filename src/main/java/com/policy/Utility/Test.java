package com.policy.Utility;

public class Test {

	public static String reverseString(String str)
	{
		if(str==null || str.length()<=1)
			return str;
		return reverseString(str.substring(1))+str.charAt(0);
	}
	public static void main(String[] args)
	{
		String str="Pravin";
		
		String s=reverseString(str);
		
		System.out.println(s);
	}
}