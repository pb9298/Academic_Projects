package edu.advanceST.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.advanceST.model.FacilityDetails;
import edu.advanceST.model.FacilityDetailsError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class FacilityDetailsTest {

	FacilityDetails facilityDetails = new FacilityDetails();

	@Before
	public void setUp() throws Exception {

		facilityDetails.setFacilityType("Badminton courts");
		facilityDetails.setName("BMC10");
		facilityDetails.setInterval("30 min");
		facilityDetails.setDuration("Same day");
		facilityDetails.setVenue("Indoor");
		
		new FacilityDetails("BMC10");
		new FacilityDetails("Badminton courts", "BMC10");
		new FacilityDetails("Badminton courts", "BMC10", "30 min", "Same day", "Indoor");
	}

	@Test
	@FileParameters("test/edu/advanceST/test/FacilityDetailsTestCases1.csv")
	public void FacilityDetailsErrorTest(int tcNo, String name, String nameError) {
		FacilityDetailsError error = FacilityDetails.validate(name);
		assertEquals(nameError, error.getNameError());
	}

	@Test
	@FileParameters("test/edu/advanceST/test/FacilityDetailsTestCases2.csv")
	public void ObjectTest(int tcNo, String date, String startTime, String endTime, String dateError,
			String endTimeError) throws ParseException {
		Date dDate = null;
		if (!date.isEmpty())
			dDate = new SimpleDateFormat("YYYY-MM-DD").parse(date);
		FacilityDetailsError error = (FacilityDetailsError) FacilityDetails.validate(dDate, startTime, endTime);
		assertEquals(dateError, error.getDateError());
		assertEquals(endTimeError, error.getEndTimeError());
	}

	@After
	public void tearDown() throws Exception {
		assertEquals("Badminton courts", facilityDetails.getFacilityType());
		assertEquals("BMC10", facilityDetails.getName());
		assertEquals("30 min", facilityDetails.getInterval());
		assertEquals("Same day", facilityDetails.getDuration());
		assertEquals("Indoor", facilityDetails.getVenue());
	}
}
