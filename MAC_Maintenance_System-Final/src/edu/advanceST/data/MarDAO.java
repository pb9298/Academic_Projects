package edu.advanceST.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.advanceST.model.AssignMar;
import edu.advanceST.model.Mar;
import edu.advanceST.util.SQLConnection;

public class MarDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public boolean addAssignMar(AssignMar assignMar) {
		boolean isValid = false;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(
					"insert into assign_mar (mar_number, assigned_to, assigned_date, etr) values (?,?,?,?)");
			prepareStatement.setLong(1, assignMar.getMarNumber());
			prepareStatement.setString(2, assignMar.getAssignedTo());
			prepareStatement.setDate(3, new Date(assignMar.getAssignedDate().getTime()));
			prepareStatement.setString(4, assignMar.getEtr());
			prepareStatement.executeUpdate();
			isValid = true;
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}

	public AssignMar getAssignMarByNumber(long marNumber) {

		Connection conn = SQLConnection.getDBConnection();
		AssignMar asssignMar = null;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from assign_mar where mar_number = ?");
			prepareStatement.setLong(1, marNumber);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			asssignMar = new AssignMar(Long.valueOf(results.getString(1)), results.getString(2), results.getDate(3),
					results.getString(4));
			conn.close();
		} catch (SQLException e) {
		}
		return asssignMar;
	}

	public Mar getMarByNumber(long marNumber) {

		Connection conn = SQLConnection.getDBConnection();
		Mar mar = null;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from mar where mar_number = ?");
			prepareStatement.setLong(1, marNumber);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			mar = new Mar(results.getLong(1), results.getString(2), results.getString(3), results.getString(4),
					results.getString(5), results.getString(7), results.getDate(6), false);
			conn.close();
		} catch (SQLException e) {
		}
		return mar;
	}

	public boolean addMARdetails(Mar MAR_details) {
		boolean isValid = false;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement prepareStatement1 = conn.prepareStatement(
					"insert into mar (facility_type, name, urgency, description, date, reportedBy) values (?,?,?,?,?,?)");
			prepareStatement1.setString(1, MAR_details.getFacilityType());
			prepareStatement1.setString(2, MAR_details.getName());
			prepareStatement1.setString(3, MAR_details.getUrgency());
			prepareStatement1.setString(4, MAR_details.getDescription());
			prepareStatement1.setDate(5, new Date(new java.util.Date().getTime()));
			prepareStatement1.setString(6, MAR_details.getReportedBy());
			prepareStatement1.executeUpdate();
			isValid = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}

	public List<Mar> getMars(String assignedTo) {

		Connection conn = SQLConnection.getDBConnection();
		List<Mar> unAssiMarList = new ArrayList<Mar>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(
					"select * from mar m1 inner join assign_mar m2 on m1.mar_number = m2.mar_number where assigned_to = ? order by m1.mar_number desc");
			prepareStatement.setString(1, assignedTo);
			ResultSet results = prepareStatement.executeQuery();
			while (results.next()) {
				unAssiMarList.add(new Mar(results.getLong(1), results.getString(2), results.getString(3),
						results.getString(4), results.getString(5), results.getString(7), results.getDate(6), true));
			}
			conn.close();
		} catch (SQLException e) {
		}
		return unAssiMarList;
	}

	public List<Mar> searchMars(String assigned, Date assignedDate) {

		Connection conn = SQLConnection.getDBConnection();
		List<Mar> marList = new ArrayList<Mar>();
		PreparedStatement prepareStatement = null;
		Boolean isAssigned = null;
		try {
			ResultSet results = null;
			if (assigned.equalsIgnoreCase("yes")) {
					prepareStatement = conn.prepareStatement(
							"select * from mar inner join assign_mar on mar.mar_number = assign_mar.mar_number where date = ? order by mar.mar_number desc");
					prepareStatement.setDate(1, assignedDate);
				isAssigned = true;
			} else {
				prepareStatement = conn.prepareStatement(
						"select * from mar where mar_number not in (select mar_number from assign_mar) order by mar_number desc");
				isAssigned = false;
			}
			results = prepareStatement.executeQuery();
			while (results.next()) {
				marList.add(
						new Mar(results.getLong(1), results.getString(2), results.getString(3), results.getString(4),
								results.getString(5), results.getString(7), results.getDate(6), isAssigned));
			}
			conn.close();
		} catch (SQLException e) {
		}
		return marList;
	}
}
