package edu.advanceST.model;

import java.util.Date;
import java.util.regex.Pattern;

public class AssignMar {

	private long marNumber;
	private String assignedTo;
	private Date assignedDate;
	private String etr;
	
	public AssignMar() {
		
	}
	
	public AssignMar(long marNumber, String assignedTo, Date assignedDate, String etr) {
		this.marNumber = marNumber;
		this.assignedTo = assignedTo;
		this.assignedDate = assignedDate;
		this.etr = etr;
	}

	public long getMarNumber() {
		return marNumber;
	}

	public void setMarNumber(long marNumber) {
		this.marNumber = marNumber;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getEtr() {
		return etr;
	}

	public void setEtr(String etr) {
		this.etr = etr;
	}
	
	@SuppressWarnings("deprecation")
	public static AssignMarError validate(String assignedTo, Date assignedDate){
		AssignMarError error = new AssignMarError();
		if (assignedTo.length() != 7 || !Pattern.compile("^[a-zA-Z0-9]*$").matcher(assignedTo).matches()) {
			error.setAssignedToError("assignedTo is required and it should be alphanumeric and its length should be equal to 7");
		}
		if (assignedDate == null || assignedDate.getDate() != new Date().getDate()) {
			error.setAssignedDateError("assignedDate is required and it should be today's date");
		}
		return error;
	}
	
}
