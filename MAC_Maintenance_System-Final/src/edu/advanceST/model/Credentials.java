package edu.advanceST.model;

import java.util.regex.Pattern;

import edu.advanceST.data.LoginDAO;

public class Credentials {
	
	private String username;
	private String password;
	
	public Credentials() {
		
	}
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static CredentialsError validate(String username, String password, boolean creditCheck, boolean isUpdate) {
		CredentialsError error = new CredentialsError();
		Credentials credentials = new Credentials(username, password);
		if (username.length() != 7
				|| !Pattern.compile("^[a-zA-Z0-9]*$").matcher(username).matches()) {
			error.setUsernameError(
					"username is required and it should be alphanumeric and its length should be equal to 7");
		} else if (new LoginDAO().checkUser(credentials, creditCheck, isUpdate)) {
			error.setUsernameError("username already exists");
		}
		if (!Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$")
				.matcher(password).matches()) {
			error.setPasswordError(
					"password is required and its length should be between 8 to 32 and It should have atleast 1 capital letter 1 small letter 1 special charachter and 1 number");
		}
		return error;
	}
	
}
