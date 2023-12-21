package com.kannanrameshrk.admission.admin;

import org.json.simple.JSONObject;

import com.kannanrameshrk.admission.dto.Course;
import com.kannanrameshrk.admission.repository.AdmissionRespository;

class AdminViewModel {
	private AdminView adminview;

	public AdminViewModel(AdminView adminView) {
		this.adminview= adminView;
	}

	@SuppressWarnings("unchecked")
	public boolean validate(Course c) {

		boolean courseNameIsValid=validCName(c.getCourseName());
		boolean seatCountIsValid=validSeatCount(c.getSeatCount());
		boolean feesIsValid=validFees(c.getFees());

		if(courseNameIsValid && seatCountIsValid && feesIsValid ) {
			 JSONObject courseDetails = new JSONObject();
			 courseDetails.put("CourseName", c.getCourseName());
			 courseDetails.put("SeatCount", c.getSeatCount());
			 courseDetails.put("Fees", c.getFees());
			boolean stored=AdmissionRespository.getInstance().insertCource(courseDetails);
			if(stored) {
				adminview.onSucess("Sucessfully Inserted");
			}
			return true;
		}
		return false;
	}

	private boolean validFees(double fees) {
		if( fees!=0) {
			return true;
		}
		adminview.showError("Please enter correct amount");
		return false;
	}

	private boolean validCName(String courseName) {
		if(courseName.matches("[a-zA-Z\\s]+")) {
			return true;
		}
		adminview.showError("Please enter correct courseName");
		return false;
	}

	private boolean validSeatCount(int totalSeat) {
		if(totalSeat !=0) {
			return true;
		}
		adminview.showError("Please enter valid input Seat");
		return false;
	}

	public void viewStudentStatus() {
		JSONObject map=AdmissionRespository.loadStudentData();
		if (map != null && map.containsKey("students")) {
	        JSONObject studentsData = (JSONObject) map.get("students");

	        for (Object key : studentsData.keySet()) {
	            String studentId = (String) key;
	            JSONObject studentDetails = (JSONObject) studentsData.get(studentId);

	            String name = (String) studentDetails.get("Name");
	            String gender = (String) studentDetails.get("Gender");
	            String course = (String) studentDetails.get("Course");
	            boolean feesPaid = (boolean) studentDetails.get("FeesPaid");
	            String feesPaidStatus = feesPaid ? "YES" : "NO";

	            System.out.printf("%s   %s   %s     %s      %s%n", studentId, name, gender, course, feesPaidStatus);
	        }
	    }else {
	    	adminview.showError("Student not Registor in courses");
	    }
	}

}
