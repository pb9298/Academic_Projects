package edu.advanceST.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.advanceST.model.SystemUser;
import edu.advanceST.model.SystemUserError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SystemUserTest {

	private SystemUser systemUser;
	private Calendar c;

	@Before
	public void setUp() throws Exception {

		c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		systemUser = new SystemUser();
		systemUser.setUsername("user123");
		systemUser.setRole("user");
	}
	
	@Test
	@FileParameters("test/edu/advanceST/test/SystemUserTestCases.csv")
	public void testMar(String role, String roleError)
			throws ParseException {

		SystemUserError error = SystemUser.validate(role);
		assertEquals(roleError, error.getRole());
	}
	
	@After
	public void tearDown() throws Exception {
		
		assertEquals(systemUser.getUsername(),"user123");
		assertEquals(systemUser.getRole(),"user");
		
	}
}
