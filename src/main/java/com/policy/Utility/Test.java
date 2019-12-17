package com.policy.Utility;

import java.util.Scanner;
import java.util.HashSet;
public class Test extends Normal_Methods
{
	public static void main(String[] args)
	{
		String s1="₹ 1,091 monthly";
		String s2="₹ 1,410 monthly";
		
		int policy1=getInteger(s1);
		int policy2=getInteger(s2);
		int diff=getDifference(policy1, policy2);
		System.out.println("The diff is "+diff);
		
	}
	
	public static int getDifference(int i, int j) {
		return i-j;
	}

	public static int getInteger(String text){
		String s1=text.substring(text.indexOf("₹")+2, text.indexOf("monthly")-1).replace(",", "");
		int num=Integer.valueOf(s1);
		return num;
	}
}