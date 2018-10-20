package com.policy.cucumberTest;

import java.util.Scanner;

public class Test {
	static boolean checkForPrime(int num) {
		boolean flag = true;

		if (num <= 1) {
			flag = false;
			return flag;
		}
		else
		{
			for (int i = 2; i <= (num / 2); i++)
			{
				if ((num % i) == 0)
				{
					flag = false;
					break;
				}
			}
		}		
		return flag;
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);) {
			System.out.println("Enter a num");

			int num = sc.nextInt();

			boolean isPrime = checkForPrime(num);

			if (isPrime)
				System.out.println(num + " is Prime");
			else
				System.out.println(num + " is not Prime");
		}
	}
}