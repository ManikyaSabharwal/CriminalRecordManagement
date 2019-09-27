package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeration {

	public static int register (String userName, String password) {
		int rows = 0 ;
		
		try {
			Connection conn = ConnectionHandler.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `users` (`us_userName`, `us_pswd`) VALUES (?, AES_ENCRYPT(?, 'secret'));");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			rows = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public boolean findByUserName(String userName) {
	      try{
	    	  Connection conn = ConnectionHandler.getConnection();
	          PreparedStatement prepStatement = conn.prepareStatement("select count(*) from users where us_userName = ?");
	          prepStatement.setString(1, userName);   
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {   
	              while (result.next()) {
	                  if (result.getInt(1) == 1) {
	                      return true;
	                  }               
	              }
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return false;
	  }
	
	public boolean findByLogin(String userName, String password) {
	      try{
	    	  Connection conn = ConnectionHandler.getConnection();
	          PreparedStatement prepStatement = conn.prepareStatement("select us_pswd from users where us_userName = ?");
	          prepStatement.setString(1, userName);           
	          
	          ResultSet result = prepStatement.executeQuery();
	          if (result != null) {
	              while (result.next()) {
	                  if (result.getString(1).equals(password)) {
	                      return true;
	                  }
	              }               
	          }           
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      return false;
	  }
	
	public boolean verifyPassword(String password) {
		String PASSWORD_REQUIREMENT1 = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{6,20})";
		Pattern pattern = Pattern.compile(PASSWORD_REQUIREMENT1);
		Matcher matcher = pattern.matcher(password);

		Pattern pattern2 = Pattern.compile("[0-9\\s]");
		Matcher matcher2 = pattern2.matcher(password);
		return (matcher.matches()&&(!matcher2.find()));
	}
}
