package com.kannanrameshrk.admission.admin;

import java.sql.ResultSet;
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
		System.out.println("\t\t-----------");
		System.out.println("\t\tAdmin page");
		System.out.println("\t\t-----------");
		System.out.println("Enter UserName:");
		String uName=input.next();
		System.out.println("Enter Password:");
		String password = input.next();
		
		if(uName.equals(userName) && password.equals(pwd)) {
			boolean loop=true;
			while(loop){
				System.out.println(" 1.AddCourse\n 2.Application Status\n 3.Cancelation Status\n 4.Back");
				System.out.println("Enter your option");
				int choice = input.nextInt();
	
				switch (choice) {
				case 1: {
					System.out.println("Add Course");
					System.out.println("-----------");
					System.out.println("Enter Course Name:");
					String cName = input.next();
					System.out.println("Enter total seat(Number Only):");
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
					System.out.println("-----------------------------------------");
					break;
				}
				case 3:{
					System.out.println("Cancelation Status");
					System.out.println("-----------------------");
					System.out.println("Id     Name     Gender  Course  FeesPaid  CancelDate           Reason");
					System.out.println("------------------------------------------------------------------");
					adminviewmodel.viewCancelStatus();
					System.out.println("------------------------------------------------------------------");
					break;
				}
				case 4: {
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

	public boolean viewStudentStatus() {
		ResultSet rs=adminviewmodel.viewStudentStatus();
		try {
			if(!rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
