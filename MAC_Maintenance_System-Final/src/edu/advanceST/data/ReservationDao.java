package edu.advanceST.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import edu.advanceST.model.Reservation;
import edu.advanceST.util.SQLConnection;

public class ReservationDao {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public TreeMap<Double, Boolean> getAvailableFacilityReport(String facilityName, Date date) {

		Connection conn = SQLConnection.getDBConnection();
		TreeMap<Double, Boolean> timeLine = new TreeMap<Double, Boolean>();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(
					"select start_time, end_time from reservation where facility_name = ? and date = ? and status = ?");
			prepareStatement.setString(1, facilityName);
			prepareStatement.setDate(2, new java.sql.Date(date.getTime()));
			prepareStatement.setString(3, "approved");
			ResultSet resultSet = prepareStatement.executeQuery();
			double oStart = 6;
			double oEnd = 24; 
			while (oStart < oEnd) {
				timeLine.put(oStart, false);
				oStart += 0.5;
			}
			conn.close();
		} catch (SQLException e) {
		}
		return timeLine;
	}

	public boolean requestReservation(Reservation reservation, String marNumber) {

		Connection conn = SQLConnection.getDBConnection();
		boolean requested = false;
		try {
			PreparedStatement prepareStatement = conn
					.prepareStatement("INSERT INTO reservation VALUES (null, ?, ?, ?, ?, ?, ?, ?);");
			prepareStatement.setString(1, reservation.getName());
			prepareStatement.setString(2, reservation.getUsername());
			prepareStatement.setDate(3, new java.sql.Date(reservation.getDate().getTime()));
			prepareStatement.setString(4, reservation.getStartTime());
			prepareStatement.setString(5, reservation.getEndTime());
			prepareStatement.setString(6, reservation.getStatus());
			prepareStatement.setString(7, marNumber);
			prepareStatement.executeUpdate();
			requested = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return requested;
	}

	public List<Reservation> getAllReservationRequests(String username) {

		Connection conn = SQLConnection.getDBConnection();
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			PreparedStatement prepareStatement = null;
			prepareStatement = conn.prepareStatement(
					"select reservation_id, date, status from reservation where username = ? order by reservation_id desc");
			prepareStatement.setString(1, username);
			ResultSet results = prepareStatement.executeQuery();
			while (results.next()) {
				reservations.add(new Reservation(results.getInt(1), null, null, results.getDate(2), null, null,
						results.getString(3)));
			}
			conn.close();
		} catch (SQLException e) {
		}
		return reservations;
	}

	public int getMarNumbers(int reservationId) {

		Connection conn = SQLConnection.getDBConnection();
		int marNumber = -1;
		try {
			PreparedStatement prepareStatement = conn
					.prepareStatement("select mar_number from reservation where reservation_id = ? ");
			prepareStatement.setInt(1, reservationId);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			marNumber = results.getInt(1);
			conn.close();
		} catch (SQLException e) {
		}
		return marNumber;
	}

	public Reservation getReservationById(int reservationId) {

		Connection conn = SQLConnection.getDBConnection();
		Reservation reservation = null;
		try {
			PreparedStatement prepareStatement = conn
					.prepareStatement("select * from reservation where reservation_id = ?");
			prepareStatement.setInt(1, reservationId);
			ResultSet results = prepareStatement.executeQuery();
			results.next();
			reservation = new Reservation(results.getInt(1), results.getString(2), results.getString(3),
					results.getDate(4), results.getString(5), results.getString(6), results.getString(7));
			conn.close();
		} catch (SQLException e) {
		}
		return reservation;
	}

	public boolean decisionReservation(int reservationId, String status, Integer marNumber) {

		Connection conn = SQLConnection.getDBConnection();
		boolean isUpdated = false;
		try {
			PreparedStatement prepareStatement = conn
					.prepareStatement("update reservation set status = ? where reservation_id = ?;");
			prepareStatement.setString(1, status);
			prepareStatement.setInt(2, reservationId);
			prepareStatement.executeUpdate();
			prepareStatement = conn.prepareStatement("delete from assign_mar where mar_number = ?");
			prepareStatement.setInt(1, marNumber);
			prepareStatement.executeUpdate();
			conn.commit();
			isUpdated = true;
			conn.close();
		} catch (SQLException e) {
		}
		return isUpdated;
	}

	public boolean modifyReservation(Reservation reservation) {

		Connection conn = SQLConnection.getDBConnection();
		boolean requested = false;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(
					"update reservation set date = ?, start_time = ?, end_time = ?, status = ? where reservation_id = ?;");
			prepareStatement.setDate(1, new java.sql.Date(reservation.getDate().getTime()));
			prepareStatement.setString(2, reservation.getStartTime());
			prepareStatement.setString(3, reservation.getEndTime());
			prepareStatement.setString(4, reservation.getStatus());
			prepareStatement.setInt(5, reservation.getReservationId());
			prepareStatement.executeUpdate();
			requested = true;
			conn.commit();
			conn.close();
		} catch (SQLException e) {
		}
		return requested;
	}
}
