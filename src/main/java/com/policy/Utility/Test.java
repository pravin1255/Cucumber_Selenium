package com.policy.Utility;

import java.util.HashMap;
import java.util.Set;
public class Test
{
	static void duplicateCharCount(String inputString)
	{
		HashMap<Character,Integer> charCountMap=new HashMap<>();
		
		for(char c:inputString.toCharArray())
		{
			if(charCountMap.containsKey(c))
			{
				charCountMap.put(c,charCountMap.get(c)+1);
			}
			else
			{
				charCountMap.put(c,1);
			}
		}
		
		Set<Character> charInString=charCountMap.keySet();	
		
		for(char ch:charInString)
		{
			if(charCountMap.get(ch)>1)
			{
				System.out.println(ch+" "+charCountMap.get(ch));
			}
		}
	}
	public static void main(String[] args)
	{
		duplicateCharCount("Better Butter");
	}
}