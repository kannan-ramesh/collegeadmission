package com.kannanrameshrk.admission.dto;

public class Student {
	private static int idCounter = 1000;
	private int id;
	private String name;
	private String gender;
	private String selectCourse;
	private int marks;
	private boolean feesPaid;
	
	public Student(String name, String gender, String course, int marks, boolean feesPaid) {
		this.id = generateID();
		this.name=name;
		this.gender=gender;
		this.selectCourse=course;
		this.marks=marks;
		this.feesPaid=feesPaid;
	}
	private int generateID() {
		
		return idCounter++;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSelectCourse() {
		return selectCourse;
	}
	public void setSelectCourse(String selectCourse) {
		this.selectCourse = selectCourse;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public boolean isFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(boolean feesPaid) {
		this.feesPaid = feesPaid;
	}
}
