package com.kannanrameshrk.admission.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.kannanrameshrk.admission.dto.Course;
import com.kannanrameshrk.admission.dto.Student;

public class AdmissionRespository {
	public static AdmissionRespository repository;
	
	
	private AdmissionRespository() {

	}

	public static AdmissionRespository getInstance() {
		if( repository ==null) {
			repository=new AdmissionRespository();
		}
		return repository;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean insertCource(Course c) {
		  Connection con = null;
		    PreparedStatement pstmt = null;

		    String sql = "INSERT INTO Courses (CourseName, SeatCount, Fees) VALUES (?, ?, ?)";

		    try {
		        con = DataConnection.getConnection();
		        pstmt = con.prepareStatement(sql);
		        
		        pstmt.setString(1, c.getCourseName());
		        pstmt.setInt(2, c.getSeatCount());
		        BigDecimal fees = BigDecimal.valueOf(c.getFees());
		        pstmt.setBigDecimal(3, fees);

		        pstmt.executeUpdate();
		        return true;
		    } catch (SQLException e) {
		        System.out.println("Error in database operation: " + e.getMessage());
		        return false;
		    } finally {
		        DataConnection.closeConnection();
		    }
	}

	 public static ResultSet loadAdminData() {
		 	Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        con = DataConnection.getConnection();
		        String sql = "SELECT * FROM Courses";
		        pstmt = con.prepareStatement(sql);
		        rs = pstmt.executeQuery();
		    } catch (SQLException e) {
		        System.out.println("Error retrieving admin data: " + e.getMessage());
		    }

		    return rs;
	    }
	 @SuppressWarnings("unchecked")
	public static ResultSet loadStudentData() {
		    Connection con = null;
		    java.sql.Statement stmt = null;
		    ResultSet rs = null;

		    try {
		        con = DataConnection.getConnection();
		        stmt = con.createStatement();
		        String sql = "SELECT * FROM Students"; 

		        rs = stmt.executeQuery(sql);
		    } catch (SQLException e) {
		        System.out.println("Error retrieving student data: " + e.getMessage());
		    }
		    return rs;
	    }

	@SuppressWarnings("unchecked")
	public void insertStudent(Student student) {
		
		 Connection con = null;
		    PreparedStatement pstmt = null;
		    String sql = "INSERT INTO Students (Name, Gender, Course, Marks, FeesPaid) VALUES (?, ?, ?, ?, ?)";

		    try {
		        con = DataConnection.getConnection();
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, student.getName());
		        pstmt.setString(2, student.getGender());

		        int courseId = getCourseID(student.getSelectCourse(), con); 

		        pstmt.setInt(3, courseId); 
		        pstmt.setInt(4, student.getMarks());
		        pstmt.setBoolean(5, student.isFeesPaid());

		        pstmt.executeUpdate();
		        updateSeatCount(courseId, con);
		    } catch (SQLException e) {
		        System.out.println("Error in database operation: " + e.getMessage());
		    } finally {
		        DataConnection.closeConnection();
		    }
	} 
	private void updateSeatCount(int courseId, Connection con) throws SQLException {
		String query="UPDATE Coures SET SeatCount=SeatCount-1 WHERE CourseId=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1, courseId);
		ps.executeUpdate();
	}

	private int getCourseID(String courseName, Connection con) throws SQLException {
	    String query = "SELECT CourseID FROM Courses WHERE CourseName = ?";
	    PreparedStatement pstmt = con.prepareStatement(query);
	    pstmt.setString(1, courseName);

	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("CourseID");
	    }
	    return 0; 
	}
}
