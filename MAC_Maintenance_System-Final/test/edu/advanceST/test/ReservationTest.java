package edu.advanceST.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.advanceST.model.Reservation;
import edu.advanceST.model.ReservationError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ReservationTest {

	private Reservation reservation;
	private Calendar c;

	@Before
	public void setUp() throws Exception {

		c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		reservation = new Reservation();
		reservation.setUsername("user123");
		reservation.setDate(c.getTime());
		reservation.setName("MR1");
		reservation.setReservationId(11);
		reservation.setStartTime("12");
		reservation.setEndTime("13");
		reservation.setStatus("pending");
		
		new Reservation(123, "MR1", "user123", c.getTime(), "12", "13", "pending");
	}

	@Test
	@FileParameters("test/edu/advanceST/test/reservationTestCases.csv")
	public void validate(String date, String startTime, String endTime, String facilityName, String dateError,
			String startTimeError, String endTimeError) throws ParseException, SQLException {

		Date actualDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		ReservationError error = Reservation.validateReservation(actualDate, startTime, endTime, facilityName);
		assertEquals(dateError, error.getDateError());
		assertEquals(startTimeError, error.getStartTimeError());
		assertEquals(endTimeError, error.getEndTimeError());
	}

	@After
	public void tearDown() throws Exception {
		
		assertEquals(reservation.getUsername(),"user123");
		assertEquals(reservation.getDate(),c.getTime());
		assertEquals(reservation.getName(),"MR1");
		assertEquals(reservation.getReservationId().intValue(),11);
		assertEquals(reservation.getStartTime(),"12");
		assertEquals(reservation.getEndTime(),"13");
		assertEquals(reservation.getStatus(),"pending");
	}
}
