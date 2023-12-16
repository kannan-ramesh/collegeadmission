package com.kannanrameshrk;

import java.util.Stack;

import com.kannanrameshrk.admission.admin.AdminView;
import com.kannanrameshrk.admission.student.StudentView;

public class Navigate {
	 public void back(Stack<Object> backStack)  {
	        if (!backStack.isEmpty()) {
	            backStack.pop();
	            if (!backStack.isEmpty()) {
	                Object previousView = backStack.peek();
	                if (previousView instanceof AdminView) {
	                    ((AdminView) previousView).init(backStack);
	                } else if (previousView instanceof StudentView) {
	                    ((StudentView) previousView).init(backStack);
	                }
	            }
	        } else {
	            System.out.println("Cannot go back. Already at the main menu.");
	        }
	    }

}
