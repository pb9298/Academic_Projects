package edu.advanceST.selinium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import MAC_Maintenance_System.UtilFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumTC02 extends MAC_Maintenance_System.FacilityManagerFunctions {

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath, username, password;
	private UtilFunctions util = new UtilFunctions();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		prop = new Properties();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop.load(new FileInputStream("./configuration/MAC_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		prop.load(new FileInputStream("./Login/Login.properties"));
		username = prop.getProperty("fm_login_username");
		password = prop.getProperty("fm_login_password");
		// Load Shared UI Map
		prop.load(new FileInputStream(sSharedUIMapPath));
	}

	@Test
	@FileParameters(value = "excel/fmRegistration.csv")
	public void testResgistration(String username, String password, String role, String firstname, String middlename,
			String lastname, String utaId, String phNumber, String email, String numericAddress, String streetAddress,
			String city, String state, String zipcode) throws Exception {

		driver.get(sAppURL);
		facilityManagerRegistration(username, password, role, firstname, middlename, lastname, utaId, phNumber, email,
				numericAddress, streetAddress, city, state, zipcode);
	}

	@Test
	@FileParameters(value = "excel/fmLogin.csv")
	public void testLogin(String username, String password) throws Exception {

		driver.get(sAppURL);
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		String screenShotName = methodName + " UserLogin test case ";
		util.Login(driver, prop, username, password, screenShotName);
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Home_Logout"))).click();
	}

	@Test
	@FileParameters(value = "excel/searchUnassignedMar.csv")
	public void testSearchUnassignedMar(String marNumber, String facilityType, String name, String urgency,
			String description, String date, String reportedBy) throws Exception {
		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		facilityManagerSearchUnassignedMar();
		verifyMars(marNumber, facilityType, name, urgency, description, date, reportedBy);
	}

	@Test
	@FileParameters(value = "excel/searchAssignedMarByDate.csv")
	public void testSearchAssignedMarByDate(String assignedDate, String marNumber, String facilityType, String name,
			String urgency, String description, String date, String reportedBy) throws Exception {
		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		facilityManagerSearchAssignedMarByDate(assignedDate);
		try {
			verifyMars(marNumber, facilityType, name, urgency, description, date, reportedBy);
		} catch (NoSuchElementException e) {
		} finally {
			driver.findElement(By.xpath("html/body/a[2]")).click();
			driver.findElement(By.xpath("html/body/a[1]")).click();
		}

	}

	@Test
	@FileParameters(value = "excel/reviewUnassignedMar.csv")
	public void reviewUnassignedMar(String expectedMarNumberHeader, String expectedMarNumber,
			String expectedFacilirtTypeHeader, String expectedFacilityType, String expectedNameHeader,
			String expectedName, String expectedUrgencyHeader, String expectedUrgnency, String expectedDateHeader,
			String expectedDate, String expectedDescriptionHeader, String expectedDescription,
			String expectedReportedByHeader, String expectedReportedBy, String expectedAssignedToHeader,
			String expectedassignedTo, String expectedAssignedDateHeader, String expectedAssignedDate,
			String expectedEtrHeader, String expectedEtr) throws InterruptedException {

		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		facilityManagerSearchUnassignedMar();
		facilityMangerReviewUnassignedMar(expectedMarNumberHeader, expectedMarNumber, expectedFacilirtTypeHeader,
				expectedFacilityType, expectedNameHeader, expectedName, expectedUrgencyHeader, expectedUrgnency,
				expectedDateHeader, expectedDate, expectedDescriptionHeader, expectedDescription,
				expectedReportedByHeader, expectedReportedBy, expectedAssignedToHeader, expectedassignedTo,
				expectedAssignedDateHeader, expectedAssignedDate, expectedEtrHeader, expectedEtr);
	}
	
	@Test
	@FileParameters(value = "excel/addFacility.csv")
	public void addFacility(String testCaseNo, String facilityType, String name, String interval, String duration,
			String venue, String nameError1, String nameError2) throws Exception {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_Add_facility"))).click();
		String screenShotName = methodName + " addFacility test case " + testCaseNo;
		AddFacility(driver,testCaseNo, facilityType, name, interval, duration, venue, screenShotName);
		Thread.sleep(500);
		verifyAddFacility(driver, nameError1, nameError2, (methodName + " verifyaddFacilityErrorMessages test case " + testCaseNo));
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Btn_Clk_Home"))).click();
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_FacilityManagerHome_Logout"))).click();
	}
	
	@Test
	@FileParameters("excel/viewSpecificFacility.csv")
	public void viewSpecificFacility(int testCaseNumber, String facilityType, String duration, String venue, String date, String startTime, String endTime,
			String row1, String row2, String row3, String row4, String row5,
			String data1, String data2, String data3, String data4, String data5) throws Exception {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		// Button click search facility
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_Search_Facility"))).click();
		// Values inserted to view a specific facility
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_specific_facility_FacilityType")))).selectByVisibleText(facilityType);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_specific_facility_Duration")))).selectByVisibleText(duration);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_specific_facility_Venue")))).selectByVisibleText(venue);
	    driver.findElement(By.xpath(prop.getProperty("Input_view_specific_facility_Date"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_view_specific_facility_Date"))).sendKeys(date);
	    new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_specific_facility_StartTime")))).selectByVisibleText(startTime);
	    new Select(driver.findElement(By.xpath(prop.getProperty("Input_view__specific_facility_EndTime")))).selectByVisibleText(endTime);
	    Thread.sleep(500);
	    driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_specific_facility_submit"))).click();
	    driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_specific_facility_1st_view"))).click();
		verifyHeadersSpecific(driver, "view_specific_facility_FacilityType_Title", row1,
				"view_specific_facility_Name_Title", row2, "view_specific_facility_Interval_Title", row3,
				"view_specific_facility_Duration_Title", row4, "view_specific_facility_Venue_Title", row5, 
				methodName + " verifyHeaders test case " + testCaseNumber);
		verifyContentsSpecific(driver, "view_specific_facility_FacilityType_Value", data1,
				"view_specific_facility_Name_Value", data2, "view_specific_facility_Interval_Value", data3,
				"view_specific_facility_Duration_Value", data4, "view_specific_facility_Venue_Value", data5,
				methodName + " verifyContents test case " + testCaseNumber);
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_specific_facility_page_home"))).click(); // home
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_specific_facility_logout"))).click(); // logout
		Thread.sleep(500);
	}
	
	
	@Test
	@FileParameters("excel/searchFacility.csv")
	public void viewFacility(int testCaseNumber, String facilityType, String date, String startTime, String endTime,
			String dateError, String endTimeError, String col1, String col2, String col3, String col4, String col5,
			String data1, String data2, String data3, String data4, String data5) throws Exception {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		// Button click search facility
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_facility_search_facility"))).click();
		inputFacilityDetails(driver, facilityType, date, startTime, endTime, methodName + " verify inputs test case " + testCaseNumber);
	    Thread.sleep(500);
		if (dateError.equals("") && endTimeError.equals("")) {
		verifyHeadersSpecific(driver, "view_facility_FacilityType_Title", col1,
				"view_facility_Name_Title", col2, "view_facility_Interval_Title", col3,
				"view_facility_Duration_Title", col4, "view_facility_Venue_Title", col5, 
				methodName + " verifyHeaders test case " + testCaseNumber);
		verifyContentsSpecific(driver, "view_facility_FacilityType_1stRow_value", data1,
				"view_facility_Name_1stRow_value", data2, "view_facility_Interval_1stRow_value", data3,
				"view_facility_Duration_1stRow_value", data4, "view_facility_Venue_1stRow_value", data5,
				methodName + " verifyContents test case " + testCaseNumber);
		}
		else
			verifyFacilityDetails(driver, dateError, endTimeError, methodName + " verify inputs error test case " + testCaseNumber);
		WebElement element = driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_facility_page_home")));
		Actions actions = new Actions(driver);
		Thread.sleep(500);
		actions.moveToElement(element).click().perform();
		Thread.sleep(3_000);
		driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_facility_Logout"))).click(); // logout
	}
	
	
	@Test
	@FileParameters(value = "excel/assignMar.csv")
	public void testAssignMar(String tCaseNum, String assignTo, String date, String etr, String assignedDateError)
			throws Exception {
		driver.get(sAppURL);
		facilityManagerLogin(username, password);
		facilityManagerSearchUnassignedMar();
		facilityManagerAssignMar(tCaseNum, assignTo, date, etr);
		try {
			assertEquals(assignedDateError, facilityManagerGetAssignedDateError());
			driver.findElement(By.xpath(prop.getProperty("Btn_Assign_Mar_Logout"))).click();
		} catch (NoSuchElementException e) {
			// Implemented as instructed by Professor - This is for positive scenario, when
			// we give today's date then selenium moves on to
			// home page after assigning mar and it can't find assignedDateError field and
			// throws exception
			driver.findElement(By.xpath(prop.getProperty("Btn_Home_Logout"))).click();
		}
	}


	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
