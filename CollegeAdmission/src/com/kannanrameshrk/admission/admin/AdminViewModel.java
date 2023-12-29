package com.kannanrameshrk.admission.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
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
			boolean stored=AdmissionRespository.getInstance().insertCource(c);
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

	public ResultSet viewStudentStatus()  {
		ResultSet rs=AdmissionRespository.loadStudentData();
		return rs;
	}

	public ResultSet viewCancelStatus() {
		ResultSet rs = AdmissionRespository.loadCancelData();
		try {
			if(rs ==null && !rs.next()) {
				adminview.showError("Student Not Cancelation in Course...");
				return null;
			}else {
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}