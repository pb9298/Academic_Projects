package edu.advanceST.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.advanceST.model.Credentials;
import edu.advanceST.util.SQLConnection;

public class LoginDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public String getRole(String username) {

		String role = "";
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from system_users where username = ?");
			prepareStatement.setString(1, username);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			role = results.getString(2);
		} catch (Exception e) {
		}
		return role;
	}

	public List<String> getAllRepairers() {

		List<String> repairers = new ArrayList<String>();
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from system_users where role = ?");
			prepareStatement.setString(1, "repairer");
			ResultSet results = prepareStatement.executeQuery();
			while (results.next()) {
				repairers.add(results.getString(1));
			}
			conn.close();
		} catch (Exception e) {
		}
		return repairers;
	}

	public boolean checkUser(Credentials credentials, boolean credCheck, boolean isUpdate) {

		Connection conn = SQLConnection.getDBConnection();
		boolean isValid = false;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from credentials where username = ?");
			prepareStatement.setString(1, credentials.getUsername());
			ResultSet results = prepareStatement.executeQuery();
			if (results.next()) {
				if (credCheck) {
					if (credentials.getPassword().equals(results.getString(2))) {
						isValid = true;
					}
				} else {
					if (isUpdate) {
						isValid = false;
					} else {
						isValid = true;
					}
				}
			}
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}
}
