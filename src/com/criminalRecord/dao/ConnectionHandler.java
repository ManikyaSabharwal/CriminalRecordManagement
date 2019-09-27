package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionHandler {
	private static Connection conn = null;
	
	
	//Static block so that it is loaded only one 
	static {
		//Loading the driver File
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Couldnt load the drivers.");
			e.printStackTrace();
		}
	}
	
	//static method because we don't need to create an object of class to call this method
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		/*
		 * Checking if the connection is present(i.e not null) or not
		 * if null then create a new connection
		*/
		conn = null;
		if(conn == null) {
			/*Creating a connection object and establishing 
			a connection to the dataBase . Returning the conncection
			established */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/criminalRecordDB", "root", "manikya16");
			System.out.println("Connection established successfully");
		}
		//Returning the connection made
		return conn;
	}
}
