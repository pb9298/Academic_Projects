package MAC_Maintenance_System;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FacilityManagerFunctions {
	
	private UtilFunctions util = new UtilFunctions();
	public WebDriver driver;
	public Properties prop;

	public void facilityManagerRegistration(String username, String password, String role, String firstname,
			String middlename, String lastname, String utaId, String phNumber, String email, String numericAddress,
			String streetAddress, String city, String state, String zipcode) throws InterruptedException {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.findElement(By.xpath(prop.getProperty("Txt_SignUp"))).click();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Username"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Password"))).sendKeys(password);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Role")))).selectByVisibleText(role);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Firstname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Firstname"))).sendKeys(firstname);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Middlename"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Middlename"))).sendKeys(middlename);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Lastname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Lastname"))).sendKeys(lastname);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_UTAId"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_UTAId"))).sendKeys(utaId);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Phone"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Phone"))).sendKeys(phNumber);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Email"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Email"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_NumericAddress"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_NumericAddress"))).sendKeys(numericAddress);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_StreetAddress"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_StreetAddress"))).sendKeys(streetAddress);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_City"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_City"))).sendKeys(city);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_SignUp_State1")))).selectByVisibleText(state);
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Zipcode"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Zipcode"))).sendKeys(zipcode);
		Thread.sleep(1_000);
		util.takeScreenShot(driver, methodName + "BefofreSignUp");
		driver.findElement(By.xpath(prop.getProperty("Button_SignUp_Submit"))).click();
		Thread.sleep(1_000);
		util.takeScreenShot(driver, methodName + "AfterSignUpPage");
	}

	public void facilityManagerLogin(String userName, String password) throws InterruptedException {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login"))).click();
		driver.findElement(By.xpath(prop.getProperty("Input_Login_username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Login_username"))).sendKeys(userName);
		Thread.sleep(2_000);
		driver.findElement(By.xpath(prop.getProperty("Input_Login_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Login_password"))).sendKeys(password);
		Thread.sleep(2_000);
		util.takeScreenShot(driver, methodName + "BeforeLogin");
		driver.findElement(By.xpath(prop.getProperty("Btn_Login_submit"))).click();
		Thread.sleep(2_000);
		util.takeScreenShot(driver, methodName + "AfterLogin");
	}
	
	public void facilityManagerSearchUnassignedMar() throws InterruptedException {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.findElement(By.xpath(prop.getProperty("Txt_Search_Mar"))).click();
		driver.findElement(By.xpath(prop.getProperty("Input_Search_Mar_Assigned"))).click();
		Thread.sleep(1_000);
		util.takeScreenShot(driver, methodName + "SearchUnAssignedMar");
		driver.findElement(By.xpath(prop.getProperty("Btn_Search_Mar_Submit"))).click();
		Thread.sleep(1_000);
		util.takeScreenShot(driver, methodName + "SearchUnAssignedMarWithList");
	}

	public void facilityManagerAssignMar(String tCaseNumber, String assignTo, String date,
			String estimatedTimeOfRepaier) throws InterruptedException {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.findElement(By.xpath(prop.getProperty("Assign_Mar_Click"))).click();
		driver.findElement(By.xpath(prop.getProperty("Assign_Mar_Button"))).click();
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_Assign_Mar_Assign_To"))))
				.selectByVisibleText(assignTo);
		driver.findElement(By.xpath(prop.getProperty("Input_Assign_Mar_Assigned_Date"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Assign_Mar_Assigned_Date"))).sendKeys(date);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_Assign_Mar_ETR"))))
				.selectByVisibleText(estimatedTimeOfRepaier);
		Thread.sleep(1_000);
		util.takeScreenShot(driver, methodName + "AssignMarWithDate" + tCaseNumber);
		driver.findElement(By.xpath(prop.getProperty("Btn_Assign_Mar_Submit"))).click();
		Thread.sleep(1_000);
		util.takeScreenShot(driver, methodName + "AssigMarWithDateError" + tCaseNumber);
	}

	public String facilityManagerGetAssignedDateError() {

		return driver.findElement(By.xpath(prop.getProperty("Txt_Assigned_Date_Error"))).getText();
	}

	public void facilityManagerSearchAssignedMarByDate(String date) {

		driver.findElement(By.linkText("Search Mar")).click();
		driver.findElement(By.name("assigned")).click();
		driver.findElement(By.name("assignedDate")).clear();
		driver.findElement(By.name("assignedDate")).sendKeys(date);
		driver.findElement(By.xpath(prop.getProperty("Btn_Search_Mar_Submit"))).click();
	}

	public void verifyMars(String marNumber, String facilityType, String name, String urgency,
			String description, String date, String reportedBy) {
				
		String actualMarNumber = driver.findElement(By.xpath(prop.getProperty("Verify_Actual_MarNumber"))).getText();
		String actualFacilityType = driver.findElement(By.xpath(prop.getProperty("Verify_Actual_FacilityType"))).getText();
		String actualName = driver.findElement(By.xpath(prop.getProperty("Verify_Actual_Name"))).getText();
		String actualUrgency = driver.findElement(By.xpath(prop.getProperty("Verify_Actual_Urgency"))).getText();
		String actualDescription = driver.findElement(By.xpath(prop.getProperty("Verify_Actual_Description"))).getText();
		String actualDate = driver.findElement(By.xpath(prop.getProperty("hVerify_Actual_Date"))).getText();
		String actualReportedBy = driver.findElement(By.xpath(prop.getProperty("Verify_Actual_ReportedBy"))).getText();

		assertEquals(marNumber, actualMarNumber);
		assertEquals(facilityType, actualFacilityType);
		assertEquals(name, actualName);
		assertEquals(urgency, actualUrgency);
		assertEquals(description, actualDescription);
		assertEquals(date, actualDate);
		assertEquals(reportedBy, actualReportedBy);
	}

	public void facilityMangerReviewUnassignedMar(String expectedMarNumberHeader, String expectedMarNumber, String expectedFacilirtTypeHeader, String expectedFacilityType,
			String expectedNameHeader,String expectedName, String expectedUrgencyHeader, String expectedUrgnency, String expectedDateHeader, String expectedDate, String expectedDescriptionHeader, String expectedDescription,
			String expectedReportedByHeader,String expectedReportedBy, String expectedAssignedToHeader, String expectedassignedTo, String expectedAssignedDateHeader, String expectedAssignedDate, String expectedEtrHeader, String expectedEtr) {
		
		driver.findElement(By.xpath("html/body/table/tbody/tr[2]/td[8]/a")).click();
		
		String actualMarNumberHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualMarNumberHeader"))).getText();
		String actualFacilityTypeHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualFacilityTypeHeader"))).getText();
		String actualNameHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualNameHeader"))).getText();
		String actualUrgencyHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualUrgencyHeader"))).getText();
		String actualDescriptionHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualDescriptionHeader"))).getText();
		String actualDateHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualDateHeader"))).getText();
		String actualReportedByHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualReportedByHeader"))).getText();
		String actualAssignedToHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualAssignedToHeader"))).getText();
		String actualAssignedDateHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualAssignedDateHeader"))).getText();
		String actualEtrHeader = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualEtrHeader"))).getText();
		String actualMarNumber = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualMarNumber"))).getText();
		String actualFacilityType = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualFacilityType"))).getText();
		String actualName = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualName"))).getText();
		String actualUrgency = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualUrgency"))).getText();
		String actualDescription = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualDescription"))).getText();
		String actualDate = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualDate"))).getText();
		String actualReportedBy = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualReportedBy"))).getText();
		String actualAssignedTo = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualAssignedTo"))).getText();
		String actualAssignedDate = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualAssignedDate"))).getText();
		String actualEtr = driver.findElement(By.xpath(prop.getProperty("UnAssign_ActualEtr"))).getText();
		
		assertEquals(expectedMarNumberHeader, actualMarNumberHeader);
		assertEquals(expectedMarNumber, actualMarNumber);
		assertEquals(expectedFacilirtTypeHeader, actualFacilityTypeHeader);
		assertEquals(expectedFacilityType, actualFacilityType);
		assertEquals(expectedNameHeader, actualNameHeader);
		assertEquals(expectedName, actualName);
		assertEquals(expectedUrgencyHeader, actualUrgencyHeader);
		assertEquals(expectedUrgnency, actualUrgency);
		assertEquals(expectedDescriptionHeader, actualDescriptionHeader);
		assertEquals(expectedDescription, actualDescription);
		assertEquals(expectedDateHeader, actualDateHeader);
		assertEquals(expectedDate, actualDate);
		assertEquals(expectedReportedByHeader, actualReportedByHeader);
		assertEquals(expectedReportedBy, actualReportedBy);
		assertEquals(expectedAssignedToHeader, actualAssignedToHeader);
		assertEquals(expectedassignedTo, actualAssignedTo);
		assertEquals(expectedAssignedDateHeader, actualAssignedDateHeader);
		assertEquals(expectedAssignedDate, actualAssignedDate);
		assertEquals(expectedEtrHeader, actualEtrHeader);
		assertEquals(expectedEtr, actualEtr);
	}

	public void AddFacility(WebDriver driver, String tCaseNumber, String facilityType, String name, String interval, String duration, String venue, String screenShotName)
			throws InterruptedException {
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Facility_Type")))).selectByVisibleText(facilityType);
		driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Name"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Name"))).sendKeys(name);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Interval")))).selectByVisibleText(interval);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Duration")))).selectByVisibleText(duration);
		new Select(driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Venue")))).selectByVisibleText(venue);
		driver.findElement(By.xpath(prop.getProperty("Input_Add_facility_Btn_Clk_Submit"))).click();
		util.takeScreenShot(driver, screenShotName);
		Thread.sleep(1_000);
	}
	
	
	
	public void verifyAddFacility(WebDriver driver, String nameError1,String nameError2, String screenShotName)
			throws InterruptedException {
		if (nameError1.equals(""))
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Add_facility_nameError2"))).getAttribute("value").equals(nameError2));
		else
		assertTrue(driver.findElement(By.xpath(prop.getProperty("Add_facility_nameError1"))).getAttribute("value").equals(nameError1));
		util.takeScreenShot(driver, screenShotName);
	}
	
	public void verifyFacilityDetails(WebDriver driver, String dateError, String endTimeError, String screenShotName) {
		if (dateError.equals(""))
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_endTimeError"))).getAttribute("value").equals(endTimeError));
		if (endTimeError.equals(""))
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_dateError"))).getAttribute("value").equals(dateError));
		if (dateError.equals("") && endTimeError.equals("")) {
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_endTimeError"))).getAttribute("value").equals(endTimeError));
			assertTrue(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_dateError"))).getAttribute("value").equals(dateError));
		}
		
		util.takeScreenShot(driver, screenShotName);
		
	}
	
	
	
	public void verifyContentsSpecific(WebDriver driver, String row1ValueOnpage, String expectedrow1Value, String row2ValueOnpage, String expectedrow2Value, String row3ValueOnpage, String expectedrow3Value, 
			 String row4ValueOnpage, String expectedrow4Value, String row5ValueOnpage, String expectedrow5Value, String snapShotName) {

		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row1ValueOnpage))).getText().equals(expectedrow1Value));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row2ValueOnpage))).getText().equals(expectedrow2Value));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row3ValueOnpage))).getText().equals(expectedrow3Value));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row4ValueOnpage))).getText().equals(expectedrow4Value));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row5ValueOnpage))).getText().equals(expectedrow5Value));
		 util.takeScreenShot(driver, snapShotName);
	 }
	 
	 
	 
	 public void verifyHeadersSpecific(WebDriver driver, String row1TitleOnpage, String expectedrow1Title, String row2TitleOnpage, String expectedrow2Title, String row3TitleOnpage, String expectedrow3Title, 
			 String row4TitleOnpage, String expectedrow4Title, String row5TitleOnpage, String expectedrow5Title, String snapShotName) {
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row1TitleOnpage))).getText().equals(expectedrow1Title));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row2TitleOnpage))).getText().equals(expectedrow2Title));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row3TitleOnpage))).getText().equals(expectedrow3Title));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row4TitleOnpage))).getText().equals(expectedrow4Title));
		 assertTrue(driver.findElement(By.xpath(prop.getProperty(row5TitleOnpage))).getText().equals(expectedrow5Title));
		 util.takeScreenShot(driver, snapShotName);
	 }

	 
	 public void inputFacilityDetails(WebDriver driver, String facilityType, String date, String startTime, String endTime, String screenShotName) {
		 new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_FacilityType")))).selectByVisibleText(facilityType);
		 driver.findElement(By.xpath(prop.getProperty("Input_view_facility_Date"))).clear();
		 driver.findElement(By.xpath(prop.getProperty("Input_view_facility_Date"))).sendKeys(date);
		 new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_StartTime")))).selectByVisibleText(startTime);
		 new Select(driver.findElement(By.xpath(prop.getProperty("Input_view_facility_EndTime")))).selectByVisibleText(endTime);
		 driver.findElement(By.xpath(prop.getProperty("Btn_Clk_view_facility_submit"))).click();
		 util.takeScreenShot(driver, screenShotName);
	}
}
