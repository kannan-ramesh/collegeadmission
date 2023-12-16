package com.kannanrameshrk.admission.student;

import java.util.Scanner;
import java.util.Stack;

import org.json.simple.JSONObject;

import com.kannanrameshrk.Navigate;
import com.kannanrameshrk.admission.dto.Course;
import com.kannanrameshrk.admission.dto.Student;

public class StudentView {
	private StudentViewModel studentviewmodel;
	
	public StudentView() {
		this.studentviewmodel=new StudentViewModel(this);
	}

	public void init(Stack<Object> backStack) {
		System.out.println("Registraction page");
		System.out.println("-----------");
		System.out.println(" 1.View Course\n 2.Apply Course\n 3.Back");
		Scanner input=new Scanner(System.in);
		System.out.println("Enter your option");
		int choice=input.nextInt();
		
		switch(choice) {
		case 1 :{
			System.out.println("view Course");
			System.out.println("-----------");
			studentviewmodel.viewCourse();
			break;
		}
		case 2:{
			System.out.println("Registor form");
			System.out.println("---------------");
			System.out.println("Enter Name:");
			String name=input.next();
			System.out.println("Enter Gender(Male/Female):");
			String gender=input.next().toLowerCase();
			System.out.println("select Course:");
			String course=input.next();
			System.out.println("Enter 12th Marks(ex-> 1050/1200):");
			int marks=input.nextInt();
			System.out.println("Fees Paid in Online(Yes/No):");
			String feesPaidInput=input.next().toLowerCase();
			boolean feesPaid = feesPaidInput.equals("yes") || feesPaidInput.equals("No");
			
			Student student=new Student(name,gender,course,marks,feesPaid);
			studentviewmodel.validate(student);
			break;
		}
		case 3:{
			Navigate nav=new Navigate();
			nav.back(backStack);
			break;
		}
		default:{
			System.out.println("Invalid Choice:");
		}
		}
	}

	public void showErr(String errMessage) {
		System.out.println(errMessage);
	}
	public void onSucess(String onSuccess) {
		System.out.println(onSuccess);
	}
}
