package com.policy.cucumberTest;

class Person
{
	String name;
	int age;
	
	Person()
	{
		
	}
	
	Person(String name,int age)
	{
		this.name=name;
		this.age=age;
	}
	
	public void setName()
	{
		System.out.println(name+" "+age);
	}
}


class Student
{
	String subject;
	int rollNo;
	
	Student()
	{
		
	}
	
	Student(String subject,int rollNo)
	{
		this.subject=subject;
		this.rollNo=rollNo;
	}
	
	public void setName()
	{
		System.out.println(subject+" "+rollNo);
	}
	
	public String getDetails()
	{
		return subject+" "+rollNo;
	}
}
public class Book
{
	public static <T> void m1(T t)
	{
		System.out.println(t.getClass().getName());
		if(t instanceof Person)
		{
			System.out.println(new Student().getDetails());
		}
		else if (t instanceof Student)
		{
			((Student) t).setName();
		}
	}
	public static void main(String[] args) {
		
		Person p=new Person("Pravin", 32);
		
		m1(p);
		
		
	}
}