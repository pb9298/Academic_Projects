package edu.advanceST.model;

import java.util.regex.Pattern;

public class UserDetails {

	private String username;
	private String firstname;
	private String middlename;
	private String lastname;
	private long utaId;
	private long phone;
	private String email;
	private int numericAddress;
	private String streetAddress;
	private String city;
	private String state;
	private String zipcode;
	
	public UserDetails() {
		
	}
	
	public UserDetails(String username, String firstname, String lastname, long utaId) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.utaId = utaId;
	}

	public UserDetails(String username, String firstname, String middlename, String lastname, long utaId, long phone,
			String email, int numericAddress, String streetAddress, String city, String state, String zipcode) {
		this.username = username;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.utaId = utaId;
		this.phone = phone;
		this.email = email;
		this.numericAddress = numericAddress;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getUtaId() {
		return utaId;
	}

	public void setUtaId(long utaId) {
		this.utaId = utaId;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumericAddress() {
		return numericAddress;
	}

	public void setNumericAddress(int numericAddress) {
		this.numericAddress = numericAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public static UserDetailsError validate(String username, String firstname, String middlename, String lastname, String utaId,
			String phone, String email, String numericAddress, String streetAddress, String city, String state,
			String zipcode) {
		
		UserDetailsError error = new UserDetailsError();
		if (username.length() != 7
				|| !Pattern.compile("^[a-zA-Z0-9]*$").matcher(username).matches()) {
			error.setUsernameError(
					"username is required and it should be alphanumeric and its length should be equal to 7");
		}
		if (firstname.length() > 32 || firstname.length() == 0
				|| !Pattern.compile("^[a-zA-Z]*$").matcher(firstname).matches()) {
			error.setFirstnameError(
					"firstname is required and its length should be less than 32 and can contain only alphabates");
		}
		if (lastname.length() > 32 || lastname.length() == 0 || !Pattern.compile("^[a-zA-Z]*$").matcher(lastname).matches()) {
			error.setLastnameError(
					"lastname is required and its length should be less than 32 and can contain only alphabates");
		}
		if (utaId.length() != 10 || !Pattern.compile("^[0-9]*$").matcher(utaId).matches()) {
			error.setUtaIdError("utaId is a number and is required and its length should be equal to 10");
		}
		if (phone.length() != 10 || !Pattern.compile("^[0-9]*$").matcher(phone).matches()) {
			error.setPhoneError("phone number is required and its should be equal to 10 digits");
		}
		if (email.length() < 8 || email.length() > 32
				|| !Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$").matcher(email).matches()) {
			error.setEmailError(
					"email is required and its length should be between 8 to 32 and it should be of email format");
		}
		if (streetAddress.length() > 32 || streetAddress.length() == 0) {
			error.setStreetAddressError("streetAddress is required and its length should less than 32");
		}
		if (city.length() > 32 || city.length() == 0 || !Pattern.compile("^[a-zA-Z]*$").matcher(city).matches()) {
			error.setCityError("city is required and it contains only alphabets and its length should be less than 32");
		}
		if (zipcode.length() != 5 || !Pattern.compile("^[0-9]*$").matcher(zipcode).matches()) {
			error.setZipcodeError("zipcode is a number required and its length should be equal to 5");
		}
		return error;
	}
}
