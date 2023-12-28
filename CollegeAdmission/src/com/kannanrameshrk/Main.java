package com.kannanrameshrk;

import java.util.Scanner;
import java.util.Stack;

import com.kannanrameshrk.admission.admin.AdminView;
import com.kannanrameshrk.admission.student.StudentView;

public class Main {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Stack<Object> backStack=new Stack();
	public static void main(String[] args) {
		Main main=new Main();
		main.start();
	}

	public void start() {
		@SuppressWarnings("resource")
		Scanner input =new Scanner(System.in);
		boolean loop=true;
		while(loop){
			System.out.println("\t\t---------------------");
			System.out.println("\t\tRK GROUP OF COLLEGE");
			System.out.println("\t\t---------------------");
			System.out.println(" 1.Admin\n 2.Student\n 3.Exit");
			System.out.println("Enter your Option:");
			int choice;
			while(true) {
				if(input.hasNextInt()) {
					choice = input.nextInt();
					break;
				}else {
					input.next();
					System.out.println("Invalid user option,enter number only..");
				}
				
			}
				switch(choice) {
					case 1:{
						AdminView adminview=new AdminView();
						backStack.push(adminview);
						adminview.init(backStack);
						break;
					}
					case 2:{
						StudentView studentview = new StudentView();
						backStack.push(backStack);
						studentview.init(backStack);
						break;
					}
					case 3:{
						System.out.println("Exit the application");
						loop=false;
						break;
					}
					default:{
						System.out.println("invaild Choice");
						break;
					}
				}
		}
	}

}
