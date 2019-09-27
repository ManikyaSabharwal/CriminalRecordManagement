package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.criminalRecord.model.Record;

public class Add implements Runnable {

	@Override
	public void run() {		
	}
	
	public static boolean addRecord(Record record) {
		
		Thread thread = new Thread();
		thread.start();
		
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isAdded = true;
		int crimeId = 0;
		int jailId = 0;
		int facePhotoId = 0;
		
		synchronized(thread) {
			try {
				conn = ConnectionHandler.getConnection();
				
				//take crime details
				preparedStatement = conn.prepareStatement(
							"insert into crime(crime_type, crime_details) value(?, ?);");
				preparedStatement.setString(1, record.getCrimeType());
				preparedStatement.setString(2, record.getCrimeDetails());
				preparedStatement.executeUpdate();
				
				preparedStatement = conn.prepareStatement(
						"select crime_id from crime where crime_type = ? and crime_details = ?;");
				preparedStatement.setString(1, record.getCrimeType());
				preparedStatement.setString(2, record.getCrimeDetails());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					crimeId = (int)resultSet.getInt(1);
				}
				
				//Insert Jail details
				preparedStatement = conn.prepareStatement(
						"insert into jail(jail_no,  jail_cell_no) values(?, ?);");
				preparedStatement.setInt(1, record.getJailNo());
				preparedStatement.setInt(2, record.getJailCell());
				preparedStatement.executeUpdate();
				
				preparedStatement = conn.prepareStatement(
						"select jail_id from jail where jail_no = ? and jail_cell_no = ?");
				preparedStatement.setInt(1, record.getJailNo());
				preparedStatement.setInt(2, record.getJailCell());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					jailId = (int)resultSet.getInt(1);
				}
				
				//Insert face photos
				preparedStatement = conn.prepareStatement(
						"insert into facePhoto(fp_front, fp_right, fp_left) values (?, ?, ?);");
				preparedStatement.setString(1, record.getFrontImageURL());
				preparedStatement.setString(2, record.getRightImageURL());
				preparedStatement.setString(3, record.getLeftImageURL());
				preparedStatement.executeUpdate();
				
				preparedStatement = conn.prepareStatement(
						"select fp_id from facePhoto where fp_front=?");
				preparedStatement.setString(1, record.getFrontImageURL());			
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					facePhotoId = (int)resultSet.getInt(1);
				}
				
				//Insert record
				preparedStatement = conn.prepareStatement(
						"insert into records(rec_fullName, rec_crime_id, rec_jail_id, rec_face_photos_id, rec_bloodGroup, rec_fingerPrint, rec_retineScan, rec_dnaInfo, rec_date_time) \r\n" + 
						"	values (?, ?, ?, ?, ?, ?, ?, ?, ?) ;");
				preparedStatement.setString(1, record.getFullName());
				preparedStatement.setInt(2, crimeId);
				preparedStatement.setInt(3, jailId);
				preparedStatement.setInt(4, facePhotoId);
				preparedStatement.setString(5, record.getBloodGroup());
				preparedStatement.setString(6, record.getFingerPrintURL());
				preparedStatement.setString(7, record.getRetinaScanURL());
				preparedStatement.setString(8, record.getDNAURL());
				preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
				preparedStatement.executeUpdate();
				System.out.println("Added sucessfully.");
				
			} catch (ClassNotFoundException | SQLException e) {
				isAdded = false;
			} finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return isAdded;
	}

}
