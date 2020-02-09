package edu.advanceST.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.advanceST.model.Mar;
import edu.advanceST.model.MarError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class MarTest {

	private Mar mar = new Mar();
	private Calendar c = Calendar.getInstance();

	@Before
	public void before() {

		c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		mar.setAssigned(true);
		mar.setDate(c.getTime());
		mar.setDescription("Fix it");
		mar.setFacilityType("Multipurpose Rooms");
		mar.setMarNumber(123L);
		mar.setName("MR2");
		mar.setReportedBy("user123");
		mar.setUrgency("minor");
		
		new Mar(123L, "Multipurpose Rooms", "MR2", "minor", "Fix it", "user123", c.getTime(), true);
	}

	@Test
	@FileParameters("test/edu/advanceST/test/MarTestCases.csv")
	public void testMar(int tcNo, String name, String description, String nameError, String descriptionError)
			throws ParseException {

		MarError error = Mar.validate(name, description);
		assertEquals(nameError, error.getNameError());
		assertEquals(descriptionError, error.getDescriptionError());
	}

	@After
	public void tearDown() throws Exception {
		assertEquals(true, mar.getAssigned());
		assertEquals(c.getTime(), mar.getDate());
		assertEquals("Fix it", mar.getDescription());
		assertEquals("Multipurpose Rooms", mar.getFacilityType());
		assertEquals(123L, mar.getMarNumber().longValue());
		assertEquals("MR2", mar.getName());
		assertEquals("user123", mar.getReportedBy());
		assertEquals("minor", mar.getUrgency());
	}

}