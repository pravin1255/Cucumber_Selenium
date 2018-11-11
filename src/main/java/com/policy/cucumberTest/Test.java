package com.policy.cucumberTest;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
public class Test
{
	public static void main(String[] args)throws IOException
	{
		try(BufferedReader reader1=new BufferedReader(new FileReader("F:\\Java_Programs_Latest\\FileIO\\inputDurga.txt"));
			BufferedReader reader2=new BufferedReader(new FileReader("F:\\Java_Programs_Latest\\FileIO\\delete.txt"));
			PrintWriter pw=new PrintWriter("F:\\Java_Programs_Latest\\FileIO\\outputDurga.txt");)
			{
				String line=reader1.readLine();
				
				while(line!=null)
				{
					boolean flag=false;
					
					String target=reader2.readLine();
					
					while(target!=null)
					{
						if(line.equals(target))
						{
							flag=true;
							break;
						}
						target=reader2.readLine();
					}
					if(flag==false)
					{
						pw.println(line);
					}
					line=reader1.readLine();
				}
			}
	}
}