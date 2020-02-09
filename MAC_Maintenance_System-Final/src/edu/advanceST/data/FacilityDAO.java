package edu.advanceST.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.advanceST.model.FacilityDetails;
import edu.advanceST.util.SQLConnection;

public class FacilityDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public boolean checkFacility(String name) {

		Connection conn = SQLConnection.getDBConnection();
		boolean isValid = false;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from facility where name = ?");
			prepareStatement.setString(1, name);
			ResultSet results = prepareStatement.executeQuery();
			if (results.next()) {
				isValid = true;
			}
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}

	public Map<String, String> getDurationAndInterval(String facilityName) {

		Connection conn = SQLConnection.getDBConnection();
		Map<String, String> map = new HashMap<String, String>();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from facility where name = ?");
			prepareStatement.setString(1, facilityName);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			map.put("interval", results.getString(3));
			map.put("duration", results.getString(4));
			conn.close();
		} catch (SQLException e) {
		}
		return map;
	}

	public List<String> getAllFacilityName() {

		Connection conn = SQLConnection.getDBConnection();
		List<String> names = new ArrayList<String>();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select name from facility");
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			names.add(results.getString(1));
			conn.close();
		} catch (SQLException e) {
		}
		return names;
	}

	private ArrayList<FacilityDetails> ReturnMatchingFacilityList(String queryString) {
		ArrayList<FacilityDetails> facilityListInDB = new ArrayList<FacilityDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				FacilityDetails facility = new FacilityDetails();
				facility.setFacilityType(facilityList.getString(1));
				facility.setName(facilityList.getString(2));
				facility.setInterval(facilityList.getString(3));
				facility.setDuration(facilityList.getString(4));
				facility.setVenue(facilityList.getString(5));
				facilityListInDB.add(facility);
			}
			conn.close();
		} catch (SQLException e) {
		}
		return facilityListInDB;
	}

	public FacilityDetails getSpecificFacilitiy(String name) {
		Connection conn = SQLConnection.getDBConnection();
		FacilityDetails facility = null;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from facility where name = ?");
			prepareStatement.setString(1, name);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			facility = new FacilityDetails(results.getString(1), results.getString(2), results.getString(3),
					results.getString(4), results.getString(5));
			conn.close();
		} catch (SQLException e) {
		}
		return facility;
	}

	public boolean addFacilitydetails(FacilityDetails facilityDetails) {

		Connection conn = SQLConnection.getDBConnection();
		boolean isValid = false;
		System.out.println(facilityDetails.getFacilityType());
		System.out.println(facilityDetails.getName());
		System.out.println(facilityDetails.getInterval());
		System.out.println(facilityDetails.getDuration());
		System.out.println(facilityDetails.getVenue());
		try {
			PreparedStatement prepareStatement5 = conn.prepareStatement("insert into facility values (?,?,?,?,?)");
			prepareStatement5.setString(1, facilityDetails.getFacilityType());
			prepareStatement5.setString(2, facilityDetails.getName());
			prepareStatement5.setString(3, facilityDetails.getInterval());
			prepareStatement5.setString(4, facilityDetails.getDuration());
			prepareStatement5.setString(5, facilityDetails.getVenue());
			int row5 = prepareStatement5.executeUpdate();
				isValid = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return isValid;
	}

	public ArrayList<FacilityDetails> searchFacility(String facilityType, String duration, String venue) {
		return ReturnMatchingFacilityList(" SELECT * from facility WHERE facility_type LIKE '%" + facilityType
				+ "%' AND duration LIKE '%" + duration + "%' AND venue LIKE '%" + venue + "%'");
	}
}
