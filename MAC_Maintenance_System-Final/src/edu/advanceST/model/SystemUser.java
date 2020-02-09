
package edu.advanceST.model;

import java.util.regex.Pattern;

public class SystemUser {

	private String username;
	private String role;
	
	public SystemUser() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static SystemUserError validate(String role) {

		SystemUserError error = new SystemUserError();
		if (role.length() < 4 || role.length() > 10 || !Pattern.compile("^[a-zA-Z]*$").matcher(role).matches()) {
			error.setRole("Role should have a length of atleast 3 and atmost 10 and it should contain only alphabates");
		}
		return error;
	}

}
