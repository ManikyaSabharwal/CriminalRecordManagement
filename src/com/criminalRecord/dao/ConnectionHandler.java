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
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		/*Creating a connection object and establishing 
		a connection to the dataBase . Returning the conncection
		established */
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CriminalRecordManagementDB", "root", "manikya16");
		return conn;
	}
}
