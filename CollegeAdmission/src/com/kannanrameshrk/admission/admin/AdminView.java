package com.kannanrameshrk.admission.admin;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Stack;

import com.kannanrameshrk.Main;
import com.kannanrameshrk.Navigate;
import com.kannanrameshrk.admission.dto.Course;

public class AdminView {
	private AdminViewModel adminviewmodel;
	String userName="kannanramesh";
	String pwd ="12345";
	Main main = new Main();

	public AdminView() {
		this.adminviewmodel = new AdminViewModel(this);
	}

	public void init(Stack<Object> backStack) {
		Scanner input = new Scanner(System.in);
		System.out.println("Admin page");
		System.out.println("-----------");
		System.out.println("Enter UserName:");
		String uName=input.next();
		System.out.println("ENter Password:");
		String password = input.next();
		
		if(uName.equals(userName) && password.equals(pwd)) {
			boolean loop=true;
			while(loop){
				System.out.println(" 1.AddCourse\n 2.Application Status\n 3.Back");
				System.out.println("Enter your option");
				int choice = input.nextInt();
	
				switch (choice) {
				case 1: {
					System.out.println("Add Course");
					System.out.println("-----------");
					System.out.println("Enter Course Name:");
					String cName = input.next();
					System.out.println("ENter total seat(Number Only):");
					int totalSeat = input.nextInt();
					System.out.println("Enter course Fess:");
					double fees = input.nextDouble();
					Course c = new Course(cName, totalSeat, fees);
					adminviewmodel.validate(c);
					break;
				}
				case 2: {
					System.out.println("Registered Application");
					System.out.println("-----------------------");
					System.out.println("Id     Name     Gender  Course  FeesPaid");
					System.out.println("-----------------------------------------");
					adminviewmodel.viewStudentStatus();
					break;
				}
				case 3: {
					loop=false;
					Navigate nav = new Navigate();
					nav.back(backStack);
					break;
				}
				default: {
					System.out.println("Invalid Choice:");
				}
				}
			}
		}else {
			System.out.println("Invalid UserName password...");
			return;
		}
		
	}

	public void showError(String errMessage) {
		System.out.println(errMessage);
	}

	public void onSucess(String succMessage) {
		System.out.println(succMessage);
	}
}
