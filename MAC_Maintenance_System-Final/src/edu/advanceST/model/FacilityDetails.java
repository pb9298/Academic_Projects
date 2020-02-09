package edu.advanceST.model;

import java.util.Date;
import java.util.regex.Pattern;

public class FacilityDetails {
	
	private String facilityType;
	private String name;
	private String interval;
	private String duration;
	private String venue;
	
	public FacilityDetails(String facilityType, String name, String interval, String duration, String venue) {
		this.facilityType = facilityType;
		this.name = name;
		this.interval = interval;
		this.duration = duration;
		this.venue = venue;
	}
	
	public FacilityDetails(String facilityType, String name) {
		this.facilityType = facilityType;
		this.name = name;
	}


	public FacilityDetails(String name) {
		this.name = name;
	}
	
	public FacilityDetails() {
		
	}


	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public static FacilityDetailsError validate(String name) {

		FacilityDetailsError error = new FacilityDetailsError();
		if (name.length() < 3 || name.length() > 10
				|| !Pattern.compile("^[A-Z0-9]*$").matcher(name).matches()) {
			error.setNameError(
					"name is required and length should be in range of 3 and 10 (inclusive) and should have only Capital letters and numbers");
		}
		return error;
	}
	
	public static FacilityDetailsError validate(Date date, String startTime, String endTime) {

		FacilityDetailsError facilityError = new FacilityDetailsError();
		if (date == null) {
			facilityError.setDateError("Date should not be empty");
		}
		double timeDiff = Double.valueOf(endTime) - Double.valueOf(startTime);
		if (timeDiff <= 0) {
			facilityError.setEndTimeError(
					"End time should be greater than Start time");
		}
		return facilityError;
	}
}
