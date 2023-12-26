package com.kannanrameshrk.admission.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.kannanrameshrk.admission.dto.Student;
import com.kannanrameshrk.admission.repository.AdmissionRespository;

 class StudentViewModel {
	private StudentView studentview;

	public StudentViewModel(StudentView studentView) {
		this.studentview=studentView;
	}

	public void validate(Student student) {
		boolean nameIsValid=nameValid(student.getName());
		boolean genderIsValid=genderValid(student.getGender());
		//boolean courseIsValid=courseValid(student.getSelectCourse());
		boolean marksIsValid=marksValid(student.getMarks());

		if(nameIsValid && genderIsValid &&  marksIsValid ) {
			studentview.onSucess("Sucessfully Registered");
			AdmissionRespository.getInstance().insertStudent(student);
		}else {
			studentview.showErr("Not Registered,Invalid Input");
		}
	}

	private boolean marksValid(int marks) {
		if(marks>600 && marks<=1200) {
			return true;
		}
		studentview.showErr("Possible marks is min 600 Max 1200");
		return false;
	}

	private boolean courseValid(String selectCourse) {
		AdmissionRespository.getInstance();
		return false;
		

		
	}

	private boolean genderValid(String gender) {
		if(gender.equals("male") || gender.equals("female")) {
			return true;
		}
		studentview.showErr("Invalid gender possible(male / female)");
		return false;
	}

	private boolean nameValid(String name) {
		if(name.length()>=3 && name.length()<=50 && name.matches("[a-zA-Z]+")) {
			return true;
		}
		studentview.showErr("Invali name, name length is min->3 max->50 only a-z");
		return false;
	}

	public void viewCourse(){
		 ResultSet rs=AdmissionRespository.loadAdminData();
		 try {
			if (rs != null && rs.next()) {
			        System.out.println("Courses  Seat   Fees");
			        System.out.println("------------------------");

			        do {
			            String courseName = rs.getString("CourseName");
			            int seatCount = rs.getInt("SeatCount");
			            double fees = rs.getDouble("Fees");

			            System.out.printf("%s\t%d\t%.1f%n", courseName, seatCount, fees);
			        } while (rs.next());
			    } else {
			        studentview.showErr("No Data Available...");
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
