package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.criminalRecord.model.Record;

public class View {
	
	public boolean view(Date date) {
		
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Record record;
		boolean isFound = true;
		
		try {
			conn = ConnectionHandler.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("select records.rec_fullName as FullName, crime.crime_type as CrimeType, crime.crime_details as CrimDetails,\r\n" + 
					"	jail.jail_no, jail.jail_cell_no, records.rec_bloodGroup, records.rec_fingerPrint,\r\n" + 
					"    records.rec_retineScan, records.rec_dnaInfo, records.rec_date_time,\r\n" + 
					"    facePhoto.fp_front, facePhoto.fp_right, facePhoto.fp_left\r\n" + 
					"from records\r\n" + 
					"inner join crime \r\n" + 
					"ON records.rec_crime_id = crime.crime_id\r\n" + 
					"inner join jail\r\n" + 
					"on jail.jail_id = records.rec_jail_id\r\n" + 
					"inner join facePhoto \r\n" + 
					"on facePhoto.fp_id = records.rec_face_photos_id\r\n" + 
					"where date(rec_date_time) = '"+ date +
					"' order by rec_date_time desc;");
			if(resultSet.next()) {
				System.out.println(String.format("%-20s %-20s %-20s %-150s  %-150s  %-150s %-10s %-150s  %-150s  %-150s %-6s %-9s %-20s",
						"fullName", "crimeType", "crimeDetails", "frontImageURL", "leftImageURL", "rightImageURL", "bloodGroup",
						"fingerPrintURL", "retinaScanURL", "DNAURL", "jailNo", "jailCell", "date of Launch"));
				do {
					String fullName = resultSet.getString(1);
					String crimeType = resultSet.getString(2);
					String crimeDetails = resultSet.getString(3);
					int jailNo = resultSet.getInt(4);
					int jailCell = resultSet.getInt(5);
					String bloodGroup = resultSet.getString(6);
					String fingerPrintURL = resultSet.getString(7);
					String retinaScanURL  = resultSet.getString(8);
					String DNAURL = resultSet.getString(9);
	//				java.sql.Timestamp dateOfLaunch = resultSet.getTimestamp(10);
					java.util.Date dateOfLaunch = new java.util.Date(resultSet.getTimestamp(10).getTime());
					String frontImageURL = resultSet.getString(11);
					String leftImageURL = resultSet.getString(12);
					String rightImageURL = resultSet.getString(13);
					
					record = new Record(fullName, crimeType, crimeDetails, frontImageURL, leftImageURL,
							rightImageURL, bloodGroup, fingerPrintURL, retinaScanURL, DNAURL,
							jailNo, jailCell);
					
					System.out.print(record.toString());
					System.out.println(dateOfLaunch);
				}while(resultSet.next());
			}else {
				isFound = false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isFound;
	}
	
	public boolean view(String name) {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Record record;
		boolean isFound = true;
		
		try {
			Connection conn = ConnectionHandler.getConnection();
			preparedStatement = conn.prepareStatement("select records.rec_fullName as FullName, crime.crime_type as CrimeType, crime.crime_details," + 
					" jail.jail_no, jail.jail_cell_no, records.rec_bloodGroup, records.rec_fingerPrint," + 
					" records.rec_retineScan, records.rec_dnaInfo, records.rec_date_time," + 
					" facePhoto.fp_front, facePhoto.fp_right, facePhoto.fp_left" + 
					" from records" + 
					" inner join crime" + 
					" ON records.rec_crime_id = crime.crime_id" + 
					" inner join jail" + 
					" on jail.jail_id = records.rec_jail_id" + 
					" inner join facePhoto" + 
					" on facePhoto.fp_id = records.rec_face_photos_id" + 
					" where records.rec_fullName = ?" + 
					" order by rec_date_time desc;");
			preparedStatement.setString(1,name);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.println(String.format("%-20s %-20s %-20s %-150s  %-150s  %-150s %-10s %-150s  %-150s  %-150s %-6s %-9s %-20s",
						"fullName", "crimeType", "crimeDetails", "frontImageURL", "leftImageURL", "rightImageURL", "bloodGroup",
						"fingerPrintURL", "retinaScanURL", "DNAURL", "jailNo", "jailCell", "date of Launch"));
				do {
					String fullName = resultSet.getString(1);
					String crimeType = resultSet.getString(2);
					String crimeDetails = resultSet.getString(3);
					int jailNo = resultSet.getInt(4);
					int jailCell = resultSet.getInt(5);
					String bloodGroup = resultSet.getString(6);
					String fingerPrintURL = resultSet.getString(7);
					String retinaScanURL  = resultSet.getString(8);
					String DNAURL = resultSet.getString(9);
	//				java.sql.Timestamp dateOfLaunch = resultSet.getTimestamp(10);
					java.util.Date dateOfLaunch = new java.util.Date(resultSet.getTimestamp(10).getTime());
					String frontImageURL = resultSet.getString(11);
					String leftImageURL = resultSet.getString(12);
					String rightImageURL = resultSet.getString(13);
					
					record = new Record(fullName, crimeType, crimeDetails, frontImageURL, leftImageURL,
							rightImageURL, bloodGroup, fingerPrintURL, retinaScanURL, DNAURL,
							jailNo, jailCell);
					
					System.out.print(record.toString());
					System.out.println(dateOfLaunch);
				}while(resultSet.next());
			}else {
				isFound = false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isFound;
	}
	
	public void view() {
		
		Thread viewName = new Thread();
		viewName.start();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Record record;
		
		try {
			Connection conn = ConnectionHandler.getConnection();
			preparedStatement = conn.prepareStatement("select records.rec_fullName as FullName, crime.crime_type as CrimeType, crime.crime_details," + 
					" jail.jail_no, jail.jail_cell_no, records.rec_bloodGroup, records.rec_fingerPrint," + 
					" records.rec_retineScan, records.rec_dnaInfo, records.rec_date_time," + 
					" facePhoto.fp_front, facePhoto.fp_right, facePhoto.fp_left" + 
					" from records" + 
					" inner join crime" + 
					" ON records.rec_crime_id = crime.crime_id" + 
					" inner join jail" + 
					" on jail.jail_id = records.rec_jail_id" + 
					" inner join facePhoto" + 
					" on facePhoto.fp_id = records.rec_face_photos_id" + 
					" order by rec_fullName DESC , rec_date_time desc;");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.println(String.format("%-20s %-20s %-20s %-150s  %-150s  %-150s %-10s %-150s  %-150s  %-150s %-6s %-9s %-20s",
						"fullName", "crimeType", "crimeDetails", "frontImageURL", "leftImageURL", "rightImageURL", "bloodGroup",
						"fingerPrintURL", "retinaScanURL", "DNAURL", "jailNo", "jailCell", "date of Launch"));
				do {
					String fullName = resultSet.getString(1);
					String crimeType = resultSet.getString(2);
					String crimeDetails = resultSet.getString(3);
					int jailNo = resultSet.getInt(4);
					int jailCell = resultSet.getInt(5);
					String bloodGroup = resultSet.getString(6);
					String fingerPrintURL = resultSet.getString(7);
					String retinaScanURL  = resultSet.getString(8);
					String DNAURL = resultSet.getString(9);
	//				java.sql.Timestamp dateOfLaunch = resultSet.getTimestamp(10);
					java.util.Date dateOfLaunch = new java.util.Date(resultSet.getTimestamp(10).getTime());
					String frontImageURL = resultSet.getString(11);
					String leftImageURL = resultSet.getString(12);
					String rightImageURL = resultSet.getString(13);
					
					record = new Record(fullName, crimeType, crimeDetails, frontImageURL, leftImageURL,
							rightImageURL, bloodGroup, fingerPrintURL, retinaScanURL, DNAURL,
							jailNo, jailCell);
					
					System.out.print(record.toString());
					System.out.println(dateOfLaunch);
				}while(resultSet.next());
			}else {
				System.out.println("No records found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
