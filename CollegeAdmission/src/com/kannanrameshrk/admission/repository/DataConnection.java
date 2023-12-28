package com.kannanrameshrk.admission.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataConnection {
	private static final String url = "jdbc:mysql://localhost:3306/collegeadmission";
	private static final String name = "root";
	private static final String pass = "15410198";
	static String driver="com.mysql.cj.jdbc.Driver";	
	private static Connection connection;
	
	private DataConnection(){
		
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			try {
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}	
				connection=DriverManager.getConnection(url,name,pass);
			}catch(SQLException e) {
				 System.out.println("Error establishing the database connection: " + e.getMessage());
			}
		}
		return connection;
	}
	 public static void closeConnection() {
	        if (connection != null) {
	            try {
	                connection.close();
	                connection = null;
	            } catch (SQLException e) {
	                System.out.println("Error closing the database connection: " + e.getMessage());
	            }
	        }
	    }
}
