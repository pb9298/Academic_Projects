package edu.advanceST.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.advanceST.model.Credentials;
import edu.advanceST.model.SystemUser;
import edu.advanceST.model.UserDetails;
import edu.advanceST.util.SQLConnection;

public class RegistrationDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public boolean addUserDetails(UserDetails userDetails, SystemUser systemUser, Credentials credentials) {
		boolean isValid = false;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement1 = conn
					.prepareStatement("insert into credentials (username, password) values (?,?)");
			prepareStatement1.setString(1, credentials.getUsername());
			prepareStatement1.setString(2, credentials.getPassword());
			int row1 = prepareStatement1.executeUpdate();
			PreparedStatement prepareStatement2 = conn
					.prepareStatement("insert into system_users (username, role) values (?,?)");
			prepareStatement2.setString(1, systemUser.getUsername());
			prepareStatement2.setString(2, systemUser.getRole());
			int row2 = prepareStatement2.executeUpdate();
			PreparedStatement prepareStatement3 = conn.prepareStatement(
					"insert into user_details (username, firstname, middlename, lastname, uta_id, phone, email, numeric_address, street_address, city, state, zipcode) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			prepareStatement3.setString(1, userDetails.getUsername());
			prepareStatement3.setString(2, userDetails.getFirstname());
			prepareStatement3.setString(3, userDetails.getMiddlename());
			prepareStatement3.setString(4, userDetails.getLastname());
			prepareStatement3.setLong(5, userDetails.getUtaId());
			prepareStatement3.setLong(6, userDetails.getPhone());
			prepareStatement3.setString(7, userDetails.getEmail());
			prepareStatement3.setLong(8, userDetails.getNumericAddress());
			prepareStatement3.setString(9, userDetails.getStreetAddress());
			prepareStatement3.setString(10, userDetails.getCity());
			prepareStatement3.setString(11, userDetails.getState());
			prepareStatement3.setString(12, userDetails.getZipcode());
			int row3 = prepareStatement3.executeUpdate();
			isValid = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}

	public boolean changeRole(String username, String role) {

		boolean changed = false;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("update system_users set role = ? where username = ?");
			preparedStatement.setString(1, role);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			changed = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return changed;
	}

	public String getRole(String username, String role) {
		String frole = "";
		Connection conn = SQLConnection.getDBConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "select * from system_users ";
			if (role.isEmpty()) {
				query += "where username = '" + username + "'";
			} else {
				query += "where role = '" + role + "'";
				query += " and username = '" + username + "'";
			}
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				frole = result.getString(2);
			}
			conn.close();
		} catch (SQLException e) {
		}
		return frole;
	}

	public List<UserDetails> getProfilesList(String username, String utaId) {

		List<UserDetails> userList = new ArrayList<UserDetails>();
		Connection conn = SQLConnection.getDBConnection();
		try {
			Statement statement = conn.createStatement();
			String query = "select * from user_details ";
			if (!utaId.isEmpty()) {
				query += "where uta_Id = " + Long.valueOf(utaId);
				query += " and username = '" + username + "'";
			} else if (!username.isEmpty()) {
				query += "where username = '" + username + "'";
			}
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				userList.add(new UserDetails(result.getString(1), result.getString(2), result.getString(4),
						result.getLong(5)));
			}
			conn.close();
		} catch (SQLException e) {
		}
		return userList;
	}

	public UserDetails viewProfile(String username) {

		UserDetails user = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from user_details where username = ?");
			prepareStatement.setString(1, username);
			ResultSet result = prepareStatement.executeQuery();
			result.next();
			user = new UserDetails(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
					result.getLong(5), result.getLong(6), result.getString(7), result.getInt(8), result.getString(9),
					result.getString(10), result.getString(11), result.getString(12));
			conn.close();
		} catch (SQLException e) {
		}
		return user;
	}

	public String getPassword(String username) {

		String password = "";
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from credentials where username = ?");
			prepareStatement.setString(1, username);
			ResultSet result = prepareStatement.executeQuery();
			result.next();
			password = result.getString(2);
			conn.close();
		} catch (SQLException e) {
		}
		return password;
	}

	public boolean updateUserDetails(UserDetails userDetails, Credentials credentials) {

		boolean isValid = false;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement1 = conn
					.prepareStatement("update credentials set password = ? where username= ?");
			prepareStatement1.setString(2, credentials.getUsername());
			prepareStatement1.setString(1, credentials.getPassword());
			int row1 = prepareStatement1.executeUpdate();
			PreparedStatement prepareStatement3 = conn.prepareStatement(
					"update user_details set firstname = ?, middlename =?, lastname = ?, uta_id = ?, phone = ?, email = ?, numeric_address = ?, street_address = ?, city = ?, state = ?, zipcode = ? where username = ?");
			prepareStatement3.setString(12, userDetails.getUsername());
			prepareStatement3.setString(1, userDetails.getFirstname());
			prepareStatement3.setString(2, userDetails.getMiddlename());
			prepareStatement3.setString(3, userDetails.getLastname());
			prepareStatement3.setLong(4, userDetails.getUtaId());
			prepareStatement3.setLong(5, userDetails.getPhone());
			prepareStatement3.setString(6, userDetails.getEmail());
			prepareStatement3.setLong(7, userDetails.getNumericAddress());
			prepareStatement3.setString(8, userDetails.getStreetAddress());
			prepareStatement3.setString(9, userDetails.getCity());
			prepareStatement3.setString(10, userDetails.getState());
			prepareStatement3.setString(11, userDetails.getZipcode());
			int row3 = prepareStatement3.executeUpdate();
			isValid = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}
}
