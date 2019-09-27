package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

	public boolean login(String userName, String password) {
	      try (Connection conn = ConnectionHandler.getConnection();){
	          PreparedStatement prepStatement = conn.prepareStatement("SELECT CAST(AES_DECRYPT(us_pswd, 'secret') as char(255)) as  `pswd_decrypt` FROM `users` WHERE `us_userName` = ?;");
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
	
}
