package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete {

	public static synchronized int removeAllByName(String name) {

		int rows = 0;
		PreparedStatement preparedStatement = null;

		try (Connection conn = ConnectionHandler.getConnection();) {
			preparedStatement = conn.prepareStatement("delete from records where rec_fullName = ?");
			preparedStatement.setString(1, name);
			rows = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	public static synchronized int removeLastByName(String name) {

		int rows = 0;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try (Connection conn = ConnectionHandler.getConnection();) {
			preparedStatement = conn.prepareStatement(
					"select records.rec_id from records where records.rec_fullName = ? order by rec_date_time desc;");
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				preparedStatement = conn.prepareStatement("delete from records where rec_id = ?;");
				preparedStatement.setInt(1, resultSet.getInt(1));
				rows = preparedStatement.executeUpdate();
				// Clearing crime table
				preparedStatement = conn.prepareStatement(
						"DELETE FROM crime WHERE NOT EXISTS( SELECT * FROM records AS T1 WHERE T1.rec_crime_id = crime.crime_id);");
				preparedStatement.executeUpdate();
				// clearing jail table
				preparedStatement = conn.prepareStatement(
						"DELETE FROM jail WHERE NOT EXISTS( SELECT * FROM records AS T1 WHERE T1.rec_crime_id = jail.jail_id);");
				preparedStatement.executeUpdate();
				// Clearing facePhoto table
				preparedStatement = conn.prepareStatement(
						"DELETE FROM facePhoto WHERE NOT EXISTS( SELECT * FROM records AS T1 WHERE T1.rec_crime_id = facePhoto.fp_id);");
				preparedStatement.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
}
