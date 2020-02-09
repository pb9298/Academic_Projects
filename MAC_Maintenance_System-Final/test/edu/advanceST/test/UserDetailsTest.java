package edu.advanceST.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.advanceST.model.UserDetails;
import edu.advanceST.model.UserDetailsError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserDetailsTest {

	private UserDetails userDetails;
	private Calendar c;

	@Before
	public void setUp() throws Exception {

		c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		userDetails = new UserDetails();
		userDetails.setUsername("user123");
		userDetails.setFirstname("User");
		userDetails.setLastname("Userser");
		userDetails.setMiddlename("serser");
		userDetails.setPhone(9102182817L);
		userDetails.setUtaId(1001843892L);
		userDetails.setEmail("userser@gmail.com");
		userDetails.setNumericAddress(999);
		userDetails.setStreetAddress("Greek row");
		userDetails.setCity("Arlington");
		userDetails.setState("Texas");
		userDetails.setZipcode("76010");

		new UserDetails("user123", "User", "serser", 1001843892L);
		new UserDetails("user123", "User", "serser", "Userser", 1001843892L, 9102182817L, "userser@gmail.com", 999,
				"Greek row", "Arlington", "Texas", "76010");

	}

	@Test
	@FileParameters("test/edu/advanceST/test/UserDetailsTestCases.csv")
	public void testCredentials(int tcNo, String username, String firstname, String middlename, String lastname,
			String utaId, String phone, String email, String numericAddress, String streetAddress, String city,
			String state, String zipcode, String usernameError, String firstnameError, String middlenameError,
			String lastnameError, String utaIdError, String phoneError, String emailError, String numericAddressError,
			String streetAddressError, String cityError, String stateError, String zipcodeError) {

		UserDetailsError error = UserDetails.validate(username, firstname, middlename, lastname, utaId, phone, email,
				numericAddress, streetAddress, city, state, zipcode);
		assertEquals(usernameError, (error.getUsernameError()));
		assertEquals(firstnameError, (error.getFirstnameError()));
		assertEquals(lastnameError, (error.getLastnameError()));
		assertEquals(utaIdError, (error.getUtaIdError()));
		assertEquals(phoneError, (error.getPhoneError()));
		assertEquals(emailError, (error.getEmailError()));
		assertEquals(streetAddressError, (error.getStreetAddressError()));
		assertEquals(cityError, (error.getCityError()));
		assertEquals(zipcodeError, (error.getZipcodeError()));
	}

	@After
	public void tearDown() throws Exception {

		assertEquals(userDetails.getUsername(), "user123");
		assertEquals(userDetails.getFirstname(), "User");
		assertEquals(userDetails.getLastname(), "Userser");
		assertEquals(userDetails.getMiddlename(), "serser");
		assertEquals(userDetails.getPhone(), 9102182817L);
		assertEquals(userDetails.getUtaId(), 1001843892L);
		assertEquals(userDetails.getEmail(), "userser@gmail.com");
		assertEquals(userDetails.getNumericAddress(), 999);
		assertEquals(userDetails.getStreetAddress(), "Greek row");
		assertEquals(userDetails.getCity(), "Arlington");
		assertEquals(userDetails.getState(), "Texas");
		assertEquals(userDetails.getZipcode(), "76010");
	}
}
