package edu.advanceST.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.advanceST.data.RegistrationDAO;
import edu.advanceST.model.Credentials;
import edu.advanceST.model.CredentialsError;
import edu.advanceST.model.SystemUser;
import edu.advanceST.model.UserDetails;
import edu.advanceST.model.UserDetailsError;

@WebServlet(urlPatterns = { "/registration", "/viewProfile", "/changeRole", "/searchProfiles", "/updateProfile" })
public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equalsIgnoreCase("/registration")) {
			getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/viewProfile")) {
			postViewProfiles(request, response);
			getServletContext().getRequestDispatcher("/viewProfile.jsp").forward(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/searchProfiles")) {
			getServletContext().getRequestDispatcher("/searchProfiles.jsp").forward(request, response);
		} else {
			request.setAttribute("profile", new RegistrationDAO().viewProfile(request.getParameter("username")));
			request.setAttribute("password", new RegistrationDAO().getPassword(request.getParameter("username")));
			getServletContext().getRequestDispatcher("/updateProfile.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().equalsIgnoreCase("/registration")) {
			postRegistration(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/searchProfiles")) {
			postSearchProfile(request, response);
		} else if (request.getServletPath().equalsIgnoreCase("/changeRole")) {
			postChangeRole(request, response);
		} else {
			postUpdateProfile(request, response);
		}
	}

	private void postUpdateProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object object1 = getRegistrationForm(request);
		Object object2 = getCredentials(request, true);
		if (object1 instanceof UserDetails && object2 instanceof Credentials) {
			UserDetails userDetails = (UserDetails) object1;
			Credentials credentails = (Credentials) object2;
			new RegistrationDAO().updateUserDetails(userDetails, credentails);
			request.setAttribute("Message", "Profile successfully updated");
			request.setAttribute("profile", userDetails);
			getServletContext().getRequestDispatcher("/viewProfile.jsp").forward(request, response);
		} else {
			if (object1 instanceof UserDetailsError)
				request.setAttribute("userDetailsError", (UserDetailsError) object1);
			if (object2 instanceof CredentialsError)
				request.setAttribute("credentialsError", (CredentialsError) object2);
			request.setAttribute("profile", new RegistrationDAO().viewProfile(request.getParameter("username")));
			request.setAttribute("password", new RegistrationDAO().getPassword(request.getParameter("username")));
			getServletContext().getRequestDispatcher("/updateProfile.jsp").forward(request, response);
		}
	}

	private void postSearchProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String utaId = request.getParameter("utaId");
		String role = request.getParameter("role");
		boolean isValid = true;
		if (!username.isEmpty()
				&& (username.length() != 7 || !Pattern.compile("^[a-zA-Z0-9]*$").matcher(username).matches())) {
			isValid = false;
			request.setAttribute("usernameError", "length of username should be equal to 7");
		}
		if (!utaId.isEmpty() && (utaId.length() != 10 || !Pattern.compile("^[0-9]*$").matcher(utaId).matches())) {
			isValid = false;
			request.setAttribute("utaIdError", "length of uta id  should be equal to 10");
		}
		if (isValid) {
			RegistrationDAO registrationDAO = new RegistrationDAO();
			List<UserDetails> profilesList = registrationDAO.getProfilesList(username, utaId);
			LinkedHashMap<UserDetails, String> profileMap = new LinkedHashMap<UserDetails, String>();
			profilesList.forEach(user -> {
				String dbRole = registrationDAO.getRole(user.getUsername(), role);
				if (!dbRole.isEmpty())
					profileMap.put(user, dbRole);
			});
			request.setAttribute("profileMap", profileMap);
			getServletContext().getRequestDispatcher("/searchProfiles.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/searchProfiles.jsp").forward(request, response);
		}
	}

	private void postViewProfiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = getProfile(request);
		UserDetails profile = new RegistrationDAO().viewProfile(username);
		request.setAttribute("profile", profile);
		String role = new RegistrationDAO().getRole(username, "");
		request.setAttribute("role", role);
	}

	private String getProfile(HttpServletRequest request) {
		String username = request.getParameter("username");
		return username;
	}

	private void postChangeRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new RegistrationDAO().changeRole(request.getParameter("username"), request.getParameter("role"));
		getServletContext().getRequestDispatcher("/searchProfiles.jsp").forward(request, response);
	}

	private void postRegistration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object object1 = getRegistrationForm(request);
		Object object2 = getCredentials(request, false);
		if (object1 instanceof UserDetails && object2 instanceof Credentials) {
			UserDetails userDetails = (UserDetails) object1;
			Credentials credentails = (Credentials) object2;
			SystemUser systemUser = getSystemUser(request);
			new RegistrationDAO().addUserDetails(userDetails, systemUser, credentails);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			if (object1 instanceof UserDetailsError)
				request.setAttribute("userDetailsError", (UserDetailsError) object1);
			if (object2 instanceof CredentialsError)
				request.setAttribute("credentialsError", (CredentialsError) object2);
			getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
		}
	}

	private SystemUser getSystemUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		String role = request.getParameter("role");
		SystemUser sysUser = new SystemUser();
		sysUser.setUsername(username);
		sysUser.setRole(role);
		return sysUser;
	}

	private Object getCredentials(HttpServletRequest request, boolean isUpdate) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		CredentialsError error = Credentials.validate(username, password, false, isUpdate);
		if (error.getUsernameError().isEmpty() && error.getPasswordError().isEmpty()) {
			return new Credentials(username, password);
		} else {
			return error;
		}
	}

	private Object getRegistrationForm(HttpServletRequest request) {
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String middlename = request.getParameter("middlename");
		String lastname = request.getParameter("lastname");
		String utaId = request.getParameter("utaId");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String numericAddress = request.getParameter("numericAddress");
		String streetAddress = request.getParameter("streetAddress");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		UserDetailsError error = UserDetails.validate(username, firstname, middlename, lastname, utaId, phone, email,
				numericAddress, streetAddress, city, state, zipcode);
		if (error.getCityError().isEmpty() && error.getEmailError().isEmpty() && error.getFirstnameError().isEmpty()
				&& error.getLastnameError().isEmpty() && error.getPhoneError().isEmpty()
				&& error.getStreetAddressError().isEmpty() && error.getUsernameError().isEmpty()
				&& error.getUtaIdError().isEmpty() && error.getZipcodeError().isEmpty()) {

			UserDetails userDetails = new UserDetails();
			userDetails.setUsername(username);
			userDetails.setFirstname(firstname);
			userDetails.setMiddlename(middlename);
			userDetails.setLastname(lastname);
			userDetails.setUtaId(Long.valueOf(utaId));
			userDetails.setPhone(Long.valueOf(phone));
			userDetails.setEmail(email);
			userDetails.setNumericAddress(Integer.valueOf(numericAddress));
			userDetails.setStreetAddress(streetAddress);
			userDetails.setCity(city);
			userDetails.setState(state);
			userDetails.setZipcode(zipcode);
			return userDetails;
		} else {
			return error;
		}
	}
}
