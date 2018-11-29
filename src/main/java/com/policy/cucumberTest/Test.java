package com.policy.cucumberTest;

import java.util.Map;
import java.util.HashMap;
public class Test
{
	public static boolean isAnagram(String str1,String str2)
	{
		
		Map<Character,Integer> map=new HashMap<>();
		
		boolean flag=true;
		
		String copyOfS1=str1.replace(" ","").toLowerCase();
		
		String copyOfS2=str2.replace(" ","").toLowerCase();
		
		if(copyOfS1.length()!=copyOfS2.length())
		{
			return false;
		}
		else
		{
			for(int i=0;i<copyOfS1.length();i++)
			{

				
				int charCount=0;
				
				if(map.containsKey(copyOfS1.charAt(i)))
				{
					charCount=map.get(copyOfS1.charAt(i));
				}
				else
				{
					map.put(copyOfS1.charAt(i),++charCount);
				}
				
				charCount=0;
				
				if(map.containsKey(copyOfS2.charAt(i)))
				{
					charCount=map.get(copyOfS2.charAt(i));
				}
				else
				{
					map.put(copyOfS2.charAt(i),--charCount);
				}
				
				
			}
			for(int j:map.values())
			{
				if(j>0)
				{
					return flag;
				}
				else
				{
					flag=false;
					return flag;
				}
			}
			
		}
		
		return flag;
	}
	public static void main(String[] args)
	{
		String str1="Mother In Lao";
		
		String str2="Hitler Woman";
		
		boolean flag=isAnagram(str1,str2);
		
		if(flag)
			System.out.println("String is anagram");
		else
			System.out.println("String is not anagram");
	}
}