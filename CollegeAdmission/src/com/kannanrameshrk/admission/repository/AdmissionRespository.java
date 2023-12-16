package com.kannanrameshrk.admission.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kannanrameshrk.admission.dto.Student;

public class AdmissionRespository {
	public static AdmissionRespository repository;
	public static JSONObject adminData,studentData;
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
		 String path = "C:\\Users\\kanna\\eclipse-workspace\\webapp\\CollegeAdmission\\src\\com\\kannanrameshrk\\admission\\repository\\data.json";
		 try {
		        File file = new File(path);
		        JSONObject existingData;
		        if (file.exists() && file.length() > 0) {
		            JSONParser parser = new JSONParser();
		            existingData = (JSONObject) parser.parse(new FileReader(path));
		        } else {
		            existingData = new JSONObject();
		            existingData.put("admin", new JSONObject());
		        }

		        JSONObject adminData = (JSONObject) existingData.get("admin");

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
	            String adminFilePath = "C:\\Users\\kanna\\eclipse-workspace\\webapp\\CollegeAdmission\\src\\com\\kannanrameshrk\\admission\\repository\\data.json"; 
	            JSONParser parser = new JSONParser();
	            FileReader reader = new FileReader(adminFilePath);
	            adminData = (JSONObject) parser.parse(reader);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return adminData;
	    }
	 public static JSONObject loadStudentData() {
	        try {
	            String studentFilePath = "C:\\Users\\kanna\\eclipse-workspace\\webapp\\CollegeAdmission\\src\\com\\kannanrameshrk\\admission\\repository\\studentData.json"; 
	            JSONParser parser = new JSONParser();
	            FileReader reader = new FileReader(studentFilePath);
	            studentData = (JSONObject) parser.parse(reader);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return studentData;
	    }

	@SuppressWarnings("unchecked")
	public void insertStudent(Student student) {
		 String path = "C:\\Users\\kanna\\eclipse-workspace\\webapp\\CollegeAdmission\\src\\com\\kannanrameshrk\\admission\\repository\\studentData.json";
		 try {
		        File file = new File(path);
		        JSONObject existingData;

		        if (file.exists() && file.length() > 0) {
		            JSONParser parser = new JSONParser();
		            existingData = (JSONObject) parser.parse(new FileReader(path));
		        } else {
		            existingData = new JSONObject();
		            existingData.put("students", new JSONObject());
		        }

		        JSONObject studentsData = (JSONObject) existingData.get("students");

		        JSONObject studentObj = new JSONObject();
		        studentObj.put("Name", student.getName());
		        studentObj.put("Gender", student.getGender());
		        studentObj.put("Course", student.getSelectCourse());
		        studentObj.put("Marks", student.getMarks());
		        studentObj.put("FeesPaid", student.isFeesPaid());

		        studentsData.put(String.valueOf(student.getId()), studentObj);

		        existingData.put("students", studentsData);

		        FileWriter fileWriter = new FileWriter(path);
		        fileWriter.write(existingData.toJSONString());
		        fileWriter.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
}
