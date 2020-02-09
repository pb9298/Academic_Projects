package edu.advanceST.model;

import java.util.Date;
import java.util.regex.Pattern;

public class Mar {

	private Long marNumber;
	private String facilityType;
	private String name;
	private String urgency;
	private String description;
	private String reportedBy;
	private Date date;
	private Boolean assigned;
	
	public Mar() {
		
	}
	
	public Mar(Long marNumber, String facilityType, String name, String urgency, String description, String reportedBy,
			Date date, Boolean assigned) {
		this.marNumber = marNumber;
		this.facilityType = facilityType;
		this.name = name;
		this.urgency = urgency;
		this.description = description;
		this.reportedBy = reportedBy;
		this.date = date;
		this.assigned = assigned;
	}

	public Long getMarNumber() {
		return marNumber;
	}

	public void setMarNumber(Long marNumber) {
		this.marNumber = marNumber;
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

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getAssigned() {
		return assigned;
	}

	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}

	public static MarError validate(String name, String description) {

		MarError error = new MarError();
		if (name.length() < 3 || name.length() > 10
				|| !Pattern.compile("^[A-Z0-9]*$").matcher(name).matches()) {
			error.setNameError(
					"name is required and length should be in range of 3 and 10 (inclusive) and should have only Capital letters and numbers");
		}
		if (description.length() > 100) {
			error.setDescriptionError("description limit is 100");
		}
		return error;
	}
}
