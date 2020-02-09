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

import edu.advanceST.model.AssignMar;
import edu.advanceST.model.AssignMarError;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class AssignMarTest {

	AssignMar assignMar = new AssignMar();
	Calendar c = Calendar.getInstance();

	@Before
	public void setUp() throws Exception {

		c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		assignMar.setMarNumber(22);
		assignMar.setAssignedTo("repair9");
		assignMar.setAssignedDate(c.getTime());
		assignMar.setEtr("One hour");

		new AssignMar(22, "repair9", c.getTime(), "One hour");
	}

	@Test
	@FileParameters("test/edu/advanceST/test/assignMarTestCases.csv")
	public void validate(String assignedTo, String assignedDate, String assignedToError, String assignedDateError)
			throws ParseException, SQLException {

		Date date = null;
		if (!assignedDate.isEmpty())
			date = new SimpleDateFormat("yyyy-MM-dd").parse(assignedDate);
		AssignMarError error = AssignMar.validate(assignedTo, date);
		assertEquals(error.getAssignedToError(), assignedToError);
		assertEquals(error.getAssignedDateError(), assignedDateError);
	}

	
	@After
	public void tearDown() throws Exception {
		assertEquals(22, assignMar.getMarNumber());
		assertEquals("repair9", assignMar.getAssignedTo());
		assertEquals(c.getTime(), assignMar.getAssignedDate());
		assertEquals("One hour", assignMar.getEtr());

	}
}
