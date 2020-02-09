 package edu.advanceST.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import edu.advanceST.data.FacilityDAO;

public class Reservation {

	private Integer reservationId;
	private String name;
	private String username;
	private Date date;
	private String startTime;
	private String endTime;
	private String status;

	public Reservation(Integer reservationId, String name, String username, Date date, String startTime, String endTime,
			String status) {
		this.reservationId = reservationId;
		this.name = name;
		this.username = username;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
	
	public Reservation() {
	}
	
	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static ReservationError validateReservation(Date date, String startTime, String endTime,
			String facilityName) {

		double timeDiff = Double.valueOf(endTime) - Double.valueOf(startTime);
		ReservationError reservationError = new ReservationError();
		Calendar c = Calendar.getInstance();
		Map<String, String> durationAndInterval = new FacilityDAO().getDurationAndInterval(facilityName);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		if (date.compareTo(c.getTime()) < 0) {
			reservationError.setDateError("Date should be today's date or future date");
		} else if (Days.daysBetween(LocalDate.now(), LocalDate.fromDateFields(date)).getDays() != 0
				&& durationAndInterval.get("duration").equalsIgnoreCase("same day")) {
			reservationError.setDateError("Date should be today's date for this " + facilityName + " facility");
		} else if (Days.daysBetween(LocalDate.now(), LocalDate.fromDateFields(date)).getDays() > 7) {
			reservationError.setDateError(
					"Date should be with in 1 week from today's date for this " + facilityName + " facility");
		}
		if (date.compareTo(c.getTime()) == 0
				&& Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= Double.valueOf(startTime)) {
			reservationError.setStartTimeError("Start time should be after current time");
		} else if (timeDiff <= 0) {
			reservationError.setEndTimeError("End time should be greater than Start time");
		} else if (durationAndInterval.get("interval").equalsIgnoreCase("1 hour") && (timeDiff != 1)) {
			reservationError.setEndTimeError("you have to book this " + facilityName + " facility for 1 hour");
		} else if (durationAndInterval.get("interval").equalsIgnoreCase("2 hours") && (timeDiff != 2)) {
			reservationError.setEndTimeError("you have to book this " + facilityName + " facility for 2 hours");
		} else if (durationAndInterval.get("interval").equalsIgnoreCase("30 min") && (timeDiff != 0.5)) {
			reservationError.setEndTimeError("you have to book this " + facilityName + " for 30 mins");
		}
		return reservationError;
	}
}
