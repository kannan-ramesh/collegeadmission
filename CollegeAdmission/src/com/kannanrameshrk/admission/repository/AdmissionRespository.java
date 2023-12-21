package com.kannanrameshrk.admission.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kannanrameshrk.admission.dto.Student;

public class AdmissionRespository {
	public static AdmissionRespository repository;
	public static JSONObject adminData = new JSONObject(); 
	public static JSONObject studentData = new JSONObject();
	
	private AdmissionRespository() {

	}

	public static AdmissionRespository getInstance() {
		if( repository ==null) {
			repository=new AdmissionRespository();
		}
		return repository;
	}

	@SuppressWarnings("unchecked")
	public boolean insertCource(JSONObject course) {
		 String path = "C:\\Users\\kanna\\git\\collegeadmission\\CollegeAdmission\\src\\adminData.json";
		    try {
		        File file = new File(path);
		        JSONObject existingData;
		        
		        if (file.exists() && file.length() > 0) {
		            JSONParser parser = new JSONParser();
		            existingData = (JSONObject) parser.parse(new FileReader(path));
		        } else {
		            existingData = new JSONObject();
		        }

		        if (existingData.get("admin") == null) {
		            existingData.put("admin", new JSONObject());
		        }

		        adminData = (JSONObject) existingData.get("admin");

		        String courseName = (String) course.get("CourseName");
		        course.remove("CourseName");
		        adminData.put(courseName, course);

		        existingData.put("admin", adminData);

		        FileWriter fileWriter = new FileWriter(path);
		        fileWriter.write(existingData.toJSONString());

		        fileWriter.close();
		        return true;
		    } catch (Exception e) {
		        System.out.println(e);
		        return false;
		    }
	}

	 public static JSONObject loadAdminData() {
	        try {
	            String adminFilePath = "C:\\Users\\kanna\\git\\collegeadmission\\CollegeAdmission\\src\\adminData.json";
	            JSONParser parser = new JSONParser();
	            FileReader reader = new FileReader(adminFilePath);
	            adminData = (JSONObject) parser.parse(reader);
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return adminData;
	    }
	 public static JSONObject loadStudentData() {
	        try {
	        	 String studentFilePath = "C:\\Users\\kanna\\git\\collegeadmission\\CollegeAdmission\\src\\studentData.json";
	             File file = new File(studentFilePath);
	             if (file.exists()) {
	                 JSONParser parser = new JSONParser();
	                 FileReader reader = new FileReader(file);
	                 studentData = (JSONObject) parser.parse(reader);
	                 reader.close();
	             } else {
	                 System.out.println("Student data file does not exist.");
	             }
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	         return studentData;
	    }

	@SuppressWarnings("unchecked")
	public void insertStudent(Student student) {
		String path = "C:\\Users\\kanna\\git\\collegeadmission\\CollegeAdmission\\src\\studentData.json";
	    try {
	        File file = new File(path);
	        JSONObject existingStudentData;

	        if (file.exists() && file.length() > 0) {
	            JSONParser parser = new JSONParser();
	            FileReader fileReader = new FileReader(path);
	            existingStudentData = (JSONObject) parser.parse(fileReader);
	        } else {
	            existingStudentData = new JSONObject();
	        }

	        if (existingStudentData.get("students") == null) {
	            existingStudentData.put("students", new JSONObject());
	        }

	        studentData = (JSONObject) existingStudentData.get("students");

	        JSONObject studentObj = new JSONObject();
	        studentObj.put("Name", student.getName());
	        studentObj.put("Gender", student.getGender());
	        studentObj.put("Course", student.getSelectCourse());
	        studentObj.put("Marks", student.getMarks());
	        studentObj.put("FeesPaid", student.isFeesPaid());

	        studentData.put(String.valueOf(student.getId()), studentObj);

	        existingStudentData.put("students", studentData);

	        FileWriter fileWriter = new FileWriter(path);
	        fileWriter.write(existingStudentData.toJSONString());
	        fileWriter.flush();
	        fileWriter.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
