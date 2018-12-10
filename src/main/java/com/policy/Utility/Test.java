package com.policy.Utility;

import java.util.Scanner;
import java.util.HashSet;
public class Test
{
	public static void main(String[] args)
	{
		try(Scanner sc=new Scanner(System.in);)
		{
			System.out.println("Enter String ");
			
			String str=sc.nextLine();
			
			boolean flag=isUnique(str);
			
			if(flag)
				System.out.println(str+" is unique ");
			else
				System.out.println(str+" is not unique ");
		}
	}
	
	public static boolean isUnique(String str)
	{
		HashSet<String> set=new HashSet<>();
		
		boolean flag=true;
		
		for(String s:str.split(" "))
		{
			flag=set.add(s);
			
			if(flag==false)
			{
				return flag;
			}				
		}
		return flag;
	}
}