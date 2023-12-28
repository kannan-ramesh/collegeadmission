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
		try {
			if (rs !=null && rs.next()) {
			    do {
			    	String studentId=rs.getString("studentId");
			        String name =rs.getString("name");
			        String gender =rs.getString("Gender");
			        String course = rs.getString("Course");
			        String feesPaid = rs.getString("FeesPaid");
			       String feesPaidStatus = feesPaid.equals("1") ? "YES" : "NO";

			        System.out.printf("%s     %s     %s     %2s      %s%n", studentId, name, gender, course, feesPaidStatus);
			    }while(rs.next());
			}else {
				adminview.showError("Student not Registor in courses");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return rs;
	}

	public void viewCancelStatus() {
		ResultSet rs = AdmissionRespository.loadCancelData();
		
		try {
			if(rs !=null && rs.next()) {
				do {
					String cancelId=rs.getString("CancellationID");
					String name=rs.getString("Name");
					String gender=rs.getString("Gender");
					String course=rs.getString("Course");
					String feesPaid=rs.getString("FeesPaid");
					String cancelDate=rs.getString("CancelDate");
					String reason=rs.getString("Reason");
					 System.out.printf("%s     %s     %s     %2s      %s     %s       %s%n", cancelId, name, gender, course, feesPaid,cancelDate,reason);
				}while(rs.next());
			}else {
				adminview.showError("Student Not Cancelation in Course...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
