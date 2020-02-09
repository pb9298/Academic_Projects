package edu.advanceST.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.advanceST.model.Credentials;
import edu.advanceST.model.CredentialsError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class CredentialsTest {

	private Credentials credentials;
	private Calendar c;

	@Before
	public void setUp() throws Exception {

		c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		credentials = new Credentials();
		credentials.setUsername("user123");
		credentials.setPassword("Password1$");
		
		new Credentials("user123", "Password1$");
	}
	
	@Test
	@FileParameters("test/edu/advanceST/test/CredentialsTestCases.csv")
	public void testCredentials(String username, String password, String usernameError,
			String PasswordError) {
		
		CredentialsError error = Credentials.validate(username, password, false, false);
		assertEquals(error.getUsernameError(), usernameError);
		assertEquals(error.getPasswordError(), PasswordError);
	}
	
	@After
	public void tearDown() throws Exception {
		
		assertEquals(credentials.getUsername(),"user123");
		assertEquals(credentials.getPassword(),"Password1$");
		
	}
}
