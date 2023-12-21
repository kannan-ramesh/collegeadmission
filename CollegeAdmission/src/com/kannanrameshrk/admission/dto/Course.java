package com.kannanrameshrk.admission.dto;

public class Course {
	private String courseName;
	private int seatCount;
	private double fees;


	public Course(String cName, int totalSeat, double fees) {
		this.courseName=cName;
		this.seatCount=totalSeat;
		this.fees=fees;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
}
