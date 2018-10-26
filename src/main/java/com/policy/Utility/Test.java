package com.policy.Utility;

import java.util.Scanner;
public class Test
{
	static boolean checkForPrime(int num)
	{
		boolean flag=true;
		
		if(num<=1)
		{
			flag=false;
			return flag;
		}
		else
		{
			for(int i=2;i<=(num/2);i++)
			{
				if(num%i==0)
				{
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
	public static void main(String[] args)
	{
		try(Scanner sc=new Scanner(System.in);)
		{
			System.out.println("Enter the count of prime num");
			
			int num=sc.nextInt();
			
			System.out.println("After which num");
			
			int num2=sc.nextInt();
						
			int counter=1;
			
			int inputNumber=num2;
			
			while(counter<=num)
			{
				if(checkForPrime(inputNumber))
				{
					System.out.println(inputNumber);
					counter++;
					inputNumber++;
				}
				else
				{
					inputNumber++;
				}
			}
		}
	}
}