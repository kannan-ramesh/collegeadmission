package com.kannanrameshrk.admission.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
		boolean marksIsValid=marksValid(student.getMarks());

		if(nameIsValid && genderIsValid &&  marksIsValid ) {
			boolean insert=AdmissionRespository.getInstance().insertStudent(student);
			if(insert) {
				studentview.onSucess("Sucessfully Registered");
			}else {
				studentview.showErr("Not Registered,Course Not available..");
			}
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

	public ResultSet viewCourse(){
		 ResultSet rs=AdmissionRespository.loadAdminData();
		 return rs;
	}

	public void insertCancellation(int studentID, int courseID, Date cancelDate, String reason) {
		boolean reasons=reasonValid(reason);
		if(reasons) {
			boolean cancel=AdmissionRespository.getInstance().insertCancel(studentID,courseID,cancelDate,reason);
			if(cancel) {
				studentview.onSucess("Successfully Remove Your Data...");
			}else {
				studentview.showErr("No Success Remove Your Data...");
			}
		}else {
			studentview.showErr("Your Reason is min 20 letters ");
		}
	}

	private boolean reasonValid(String reason) {
		if(reason.length()>20) {
			return true;
		}
		return false;
	}
}
