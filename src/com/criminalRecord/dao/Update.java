package com.criminalRecord.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {

	public static synchronized int transfer(String name, int oldJailNo, int oldCellNo, int newJailNo, int newCellNo) {

		PreparedStatement preparedStatement = null;
		ResultSet rset = null;
		int rows = 0;

		try (Connection conn = ConnectionHandler.getConnection()) {
			preparedStatement = conn.prepareStatement(
					"select rec_jail_id from records inner join jail ON records.rec_jail_id = jail.jail_id where records.rec_fullName = ? and jail.jail_no = ?  and  jail.jail_cell_no = ?;");
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, oldJailNo);
			preparedStatement.setInt(3, oldCellNo);
			rset = preparedStatement.executeQuery();
			if (rset.next()) {
				preparedStatement = conn
						.prepareStatement("UPDATE jail SET jail_cell_no = ? , jail_no = ? WHERE jail_id = ? ;");
				preparedStatement.setInt(1, newCellNo);
				preparedStatement.setInt(2, newJailNo);
				preparedStatement.setInt(3, rset.getInt(1));
				rows = preparedStatement.executeUpdate();
			}

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

	public static synchronized int transfer(int oldJailNo, int newJailNo) {

		PreparedStatement preparedStatement = null;
		int rows = 0;

		try (Connection conn = ConnectionHandler.getConnection()) {
			preparedStatement = conn.prepareStatement("UPDATE jail SET jail_no = ? WHERE jail_no = ?;");
			preparedStatement.setInt(1, newJailNo);
			preparedStatement.setInt(2, oldJailNo);
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

}
