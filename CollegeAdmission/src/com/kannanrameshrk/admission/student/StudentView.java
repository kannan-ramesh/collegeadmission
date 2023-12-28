package com.kannanrameshrk.admission.student;

import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

import com.kannanrameshrk.Navigate;
import com.kannanrameshrk.admission.admin.AdminView;
import com.kannanrameshrk.admission.dto.Student;

public class StudentView {
	private StudentViewModel studentviewmodel;

	public StudentView() {
		this.studentviewmodel=new StudentViewModel(this);
	}

	public void init(Stack<Object> backStack) {
		boolean loop=true;
		while(loop) {
			System.out.println("\t\t-----------------");
			System.out.println("\t\tRegistration page");
			System.out.println("\t\t-------------------");
			System.out.println(" 1.View Course\n 2.Apply Course\n 3.Registration Cancel\n 4.Back");
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
				System.out.println("Enter 12th Marks(Min-600,Max-1200):");
				int marks=input.nextInt();
				System.out.println("Fees Paid in Online(Yes/No):");
				String feesPaidInput=input.next().toLowerCase();
				boolean feesPaid = feesPaidInput.equals("yes") || feesPaidInput.equals("No");
	
				Student student=new Student(name,gender,course,marks,feesPaid);
				studentviewmodel.validate(student);
				break;
			}
			case 3:{
				System.out.println("\t\t----------------------");
				System.out.println("\t\tRegistration Cancel");
				System.out.println("\t\t----------------------");
				System.out.println("Show student Data:");
				System.out.println("----------------------");
				System.out.println("Id     Name     Gender  Course  FeesPaid");
				System.out.println("******************************************");
				AdminView adminviewmodel=new AdminView();
				boolean res=adminviewmodel.viewStudentStatus();
				System.out.println("******************************************");
				
				if(res) {
					System.out.println("Enter Student ID:");
			        int studentID =input.nextInt();
		
			        System.out.println("Enter Course ID:");
			        int courseID = input.nextInt();
		
			        System.out.println("Enter Reason:");
			        input.nextLine(); 
			        String reason = input.nextLine();
			        Date currentDate = new Date();
		            Date cancelDate = new Date(currentDate.getTime());
		            studentviewmodel.insertCancellation(studentID, courseID, cancelDate, reason);
				}
				break;
			}
			case 4:{
				loop=false;
				Navigate nav=new Navigate();
				nav.back(backStack);
				break;
			}
			default:{
				System.out.println("Invalid Choice:");
			}
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
