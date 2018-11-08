package com.policy.cucumberTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

class Student
{
	String name;
	int marks;
	
	Student(String name, int marks)
	{
		this.name=name;
		this.marks=marks;
	}
}

class nameCompare implements Comparator<Student>
{
	public int compare(Student s1,Student s2)
	{
		return s1.name.compareTo(s2.name);
	}
}

class marksCompare implements Comparator<Student>
{
	public int compare(Student s1,Student s2)
	{
		return s2.marks-s1.marks;
	}
}

public class Test
{
	public static void main(String[] args)throws Exception
	{
		try(BufferedReader reader=new BufferedReader(new FileReader("F:\\Java_Programs_Latest\\FileIO\\input1.txt"));
				PrintWriter pw=new PrintWriter("F:\\Java_Programs_Latest\\FileIO\\output1.txt");)
		{
			ArrayList<Student> studentRecords=new ArrayList<>();
			
			String currentLine=reader.readLine();
			
			while(currentLine!=null)
			{
				String[] studentDetail=currentLine.split(" ");
				
				String name=studentDetail[0];
				
				//int marks=Integer.valueOf(studentDetail[1]);
				
				int marks=Integer.parseInt(studentDetail[1]);
				
				studentRecords.add(new Student(name,marks));
				
				currentLine=reader.readLine();
			}
			
			//Collections.sort(studentRecords, new nameCompare());
			
			//Collections.sort(studentRecords, new marksCompare());//old approach
			Collections.sort(studentRecords, (a,b)->-(a.marks-b.marks));//using lambda expression
			
			for(Student student:studentRecords)
			{
				pw.println(student.name+" "+student.marks);
			}
		}
	}
}