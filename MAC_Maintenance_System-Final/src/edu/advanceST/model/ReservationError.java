package edu.advanceST.model;

public class ReservationError {

	private String dateError = "";
	private String startTimeError = "";
	private String endTimeError = "";

	public ReservationError() {

	}

	public String getDateError() {
		return dateError;
	}

	public void setDateError(String dateError) {
		this.dateError = dateError;
	}

	public String getStartTimeError() {
		return startTimeError;
	}

	public void setStartTimeError(String startTimeError) {
		this.startTimeError = startTimeError;
	}

	public String getEndTimeError() {
		return endTimeError;
	}

	public void setEndTimeError(String endTimeError) {
		this.endTimeError = endTimeError;
	}

}
