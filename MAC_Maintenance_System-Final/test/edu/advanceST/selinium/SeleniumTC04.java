package edu.advanceST.selinium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import MAC_Maintenance_System.UtilFunctions;
import edu.advanceST.model.Mar;
import edu.advanceST.model.Reservation;
import edu.advanceST.util.SQLConnection;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC04 extends MAC_Maintenance_System.RepairerFunctions {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	private StringBuffer verificationErrors = new StringBuffer();
	private UtilFunctions util = new UtilFunctions();
	public String sAppURL, sSharedUIMapPath, testDelay;
	public static String repairer_login_username, repairer_username;
	public String repairer_login_password, repairer_password;
	private boolean acceptNextAlert = true;

	@Before
	public void setUp() throws Exception {
		//	MAGIC CODE GOES HERE 
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		prop = new Properties();	  
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop.load(new FileInputStream("./configuration/MAC_Configuration.properties"));
		sAppURL = prop.getProperty("sAppURL");
		sSharedUIMapPath = prop.getProperty("SharedUIMap");
		prop.load(new FileInputStream("./Login/Login.properties"));
		repairer_login_username = prop.getProperty("repairer_login_username");
		repairer_login_password = prop.getProperty("repairer_login_password");
		repairer_username = prop.getProperty("repairer_username");
		repairer_password = prop.getProperty("repairer_password");
		testDelay=prop.getProperty("testDelay");
		prop.load(new FileInputStream(sSharedUIMapPath));
	}
	
	private static ArrayList<Mar> ReturnMatchingMarList() {
		ArrayList<Mar> MarListInDB = new ArrayList<Mar>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet MarList = stmt.executeQuery("Select * from mar where mar_number IN (\n" + 
					"SELECT mar_number FROM mac_maintenance_system.assign_mar where assigned_to =" + "\'" + repairer_username + "\') order by mar_number DESC");
			while (MarList.next()) {
				Mar mar = new Mar(); 
				mar.setMarNumber(MarList.getLong("mar_number"));
				mar.setFacilityType(MarList.getString("facility_type"));
				mar.setName(MarList.getString("name"));
				mar.setUrgency(MarList.getString("urgency"));
				mar.setDescription(MarList.getString("description"));
				mar.setDate(MarList.getDate("date"));
				mar.setReportedBy(MarList.getString("reportedBy"));
				MarListInDB.add(mar);	
			}
		} catch (SQLException e) {}
		return MarListInDB;
	}

	private String [][] getTableContentsFromPage(int listSize) { // this method gets the list mar table contents from the web page
		String [][] MarArray = new String[listSize - 1][7];
		for (int i=0; i<listSize - 1; i++) {
			MarArray[i][0]=  driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_MarNumberCol"))).getText();
			MarArray[i][1] = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_FacilityTypeCol"))).getText();
			MarArray[i][2] = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_NameCol"))).getText();
			MarArray[i][3] = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_UrgencyCol"))).getText();
			MarArray[i][4] = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_DescriptionCol"))).getText();
			MarArray[i][5] = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_DateCol"))).getText();
			MarArray[i][6] = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_PrefixTable")+(i+2)+
					prop.getProperty("Repairer_ListMar_ReportedByCol"))).getText();
		}
		return MarArray;
	}

	private String [][] getMarListFromDB(int listSize) throws SQLException { // this method gets the list mar table contents from the DB
		ArrayList<Mar> fromDB= ReturnMatchingMarList();
		String [][] arrayDB = new String [listSize - 1][7];
		System.out.println(arrayDB.length);
		int i=0;
		for (Mar p:fromDB) {
			arrayDB[i][0]= Long.toString(p.getMarNumber());
			arrayDB[i][1]=p.getFacilityType();
			arrayDB[i][2]=p.getName();
			arrayDB[i][3]=p.getUrgency();
			arrayDB[i][4]=p.getDescription();
			arrayDB[i][5]= new SimpleDateFormat("yyyy-MM-dd").format(p.getDate());
			arrayDB[i][6]=p.getReportedBy();
			i++;
		}
		return arrayDB;
	}

	private Boolean arraysDiff (String [][] array1, String [][] array2) { // this method compares the contents of the two tables
		Boolean diff=false || (array1.length!=array2.length);
		System.out.println(array1.length);
		System.out.println(array2.length);
		for (int i=0;i<array1.length && !diff;i++) {
			diff  = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1]) || 
					!array1[i][2].equals(array2[i][2]) || !array1[i][3].equals(array2[i][3]) ||
					!array1[i][4].equals(array2[i][4]) || !array1[i][5].equals(array2[i][5]) ||
					!array1[i][6].equals(array2[i][6]);
		}
		return diff;
	}
	
	@Test
	@FileParameters(value="excel/TC04a_test_cases.csv")
	public void aRepairerRegistration(String testCaseNo, String username, String password, String role, String firstname, String middlename, String lastname,
			String utaId, String phone, String email, String numericAddress, String streetAddress, String city,
			String state, String zipcode, String usernameError, String passwordError, String firstnameError, String middlenameError,
			String lastnameError, String utaIdError, String phoneError, String emailError, String numericAddressError,
			String streetAddressError, String cityError, String stateError, String zipcodeError) throws Exception {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL + "registration");
		String screenShotName = methodName + " Repairer_Registration " + testCaseNo;
		util.Register(driver, prop, username, password, role, firstname, middlename, lastname, utaId, phone,
				email, numericAddress, streetAddress, city, state, zipcode, screenShotName);

		Thread.sleep(500);
		util.verifyRegistrationErrorMessages(driver, prop, usernameError, passwordError, firstnameError, middlenameError,
				lastnameError, utaIdError, phoneError, emailError, numericAddressError, streetAddressError, cityError,
				zipcodeError, (methodName + " verifyRepairerRegisterErrMsg test case " + testCaseNo));
	}

	@Test
	@FileParameters(value="excel/TC04b_test_cases.csv")
	public void bRepairerLogin(String testCaseNo, String username, String password, String usernameError,
			String PasswordError) throws Exception {
		driver.get(sAppURL + "logout");
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName+ "Repairer_Login " + testCaseNo;
		util.Login(driver, prop, username, password, screenShotName);
		Thread.sleep(200);
		util.verifyLoginErrorMessages(driver, prop, usernameError, PasswordError, (methodName+" verifyRepairerLoginErrMsg test case "+testCaseNo));
	}

	@Test
	@FileParameters("excel/TC04c_test_cases.csv")
	public void cRepairerListAssignedRepairs(int testCaseNumber, String col1, String col2, String col3, String col4, String col5, String col6, String col7) throws Exception {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		String screenShotName =methodName + "Repairer_Login " + testCaseNumber;
		driver.get(sAppURL);
		util.Login(driver, prop, repairer_username, repairer_password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Clk_ListMyAssignedRepairs"))).click();
		verifyHeaders(driver,"Repairer_ListMar_Col1InListMarTableHeader",col1,"Repairer_ListMar_Col2InListMarTableHeader",col2,
				"Repairer_ListMar_Col3InListMarTableHeader",col3,"Repairer_ListMar_Col4InListMarTableHeader",col4,
				"Repairer_ListMar_Col5InListMarTableHeader",col5, "Repairer_ListMar_Col6InListMarTableHeader", col6, 
				"Repairer_ListMar_Col7InListMarTableHeader", col7, methodName+" verifyHeaders test case "+testCaseNumber);
		WebElement MarTable = driver.findElement(By.xpath(prop.getProperty("Repairer_ListMar_Table")));
		int rows = MarTable.findElements(By.tagName("tr")).size(); //find the number of rows in the table including the header
		assertFalse(arraysDiff(getMarListFromDB(rows),getTableContentsFromPage(rows)));
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("Clk_Logout_FromAssignedRepairsPage"))).click(); //logout
		Thread.sleep(500);
	}

	@Test
	@FileParameters("excel/TC04d_test_cases.csv")
	public void dRepairerViewSpecificRepair(int testCaseNumber, String path, String row1, String row2, String row3, String row4, String row5, String row6, String row7,String row8, String row9, String row10,
			String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8, String data9, String data10) throws Exception {
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		String screenShotName =methodName + "Repairer_Login " + testCaseNumber;
		driver.get(sAppURL);
		util.Login(driver, prop, repairer_username, repairer_password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Clk_ListMyAssignedRepairs"))).click();
		driver.findElement(By.xpath(path)).click();
		verifyHeadersSpecific(driver,"Repairer_ListMar_Specific_MarNumber_Title",row1,"Repairer_ListMar_Specific_FacilityType_Title",row2,
				"Repairer_ListMar_Specific_Name_Title",row3,"Repairer_ListMar_Specific_Urgency_Title",row4,"Repairer_ListMar_Specific_Date_Title",row5,
				"Repairer_ListMar_Specific_Description_Title",row6,"Repairer_ListMar_Specific_ReportedBy_Title",row7,"Repairer_ListMar_Specific_AssignedTo_Title",row8,
				"Repairer_ListMar_Specific_AssignedDate_Title",row9,"Repairer_ListMar_Specific_Etr_Title",row10,
				methodName+" verifyHeaders test case "+testCaseNumber);
		verifyContentsSpecific(driver,"Repairer_ListMar_Specific_MarNumber_Value",data1,"Repairer_ListMar_Specific_FacilityType_Value",data2,
				"Repairer_ListMar_Specific_Name_Value",data3,"Repairer_ListMar_Specific_Urgency_Value",data4,"Repairer_ListMar_Specific_Date_Value",data5,
				"Repairer_ListMar_Specific_Description_Value",data6,"Repairer_ListMar_Specific_ReportedBy_Value",data7,"Repairer_ListMar_Specific_AssignedTo_Value",data8,
				"Repairer_ListMar_Specific_AssignedDate_Value",data9,"Repairer_ListMar_Specific_Etr_Value",data10,
				methodName+" verifyContents test case "+testCaseNumber);
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("Clk_Logout_FromSpecificAssignedRepairsPage"))).click(); //logout
		Thread.sleep(500);
	}

	@Test
	@FileParameters("excel/TC04h_test_cases.csv")
	public void listMyReservations(int testCaseNumber, String col0, String col3, String col6) throws Exception {
		driver.get(sAppURL + "logout");
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName + "Repairer_List_My_Reservation " + testCaseNumber;
		ListMyReservation(testCaseNumber, methodName, driver, repairer_login_username, repairer_login_password, col0, col3, col6);
	}
	@Test
    @FileParameters(value="excel/TC04g_test_cases.csv")
	public void requestRepairReservation(String testCaseNo, String startTime, String endTime, 
			String startTimeError, String endTimeError, String successMessage) throws Exception {
		driver.get(sAppURL + "logout");
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName +" Repairer" + testCaseNo;
		RequestReservation(driver, repairer_login_username, repairer_login_password, startTime, endTime, 
				startTimeError, endTimeError, successMessage, screenShotName);
	}
	
	@Test
	@FileParameters(value="excel/TC04e_test_cases.csv")
	public void repairerCancelResRepairs(String testCaseNo, String statusPending, String statusApproved, 
			String statusDecline, String statusCancelled, String statusComplete) throws Exception {
		driver.get(sAppURL + "logout");
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName +" Repairer" + testCaseNo;
		CancelReservedRepairers(driver, repairer_login_username, repairer_login_password, statusPending, 
				statusApproved, statusDecline, statusCancelled, statusComplete, screenShotName);
	}
	
	@Test
	@FileParameters(value="excel/TC04f_test_cases.csv")
	public void repairerModifyResRepairs(String testCaseNo, String statusPending, String statusApproved, 
			String statusDecline, String statusCancelled, String statusComplete, String startTime, String endTime, 
			String startTimeError, String endTimeError, String successMessage, String modifyStartTime, String modifyEndTime) throws Exception {
		driver.get(sAppURL + "logout");
		String methodName= new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName +" Repairer" + testCaseNo;
		ModifyReservedRepairers(driver, repairer_login_username, repairer_login_password, statusPending, 
				statusApproved, statusDecline, statusCancelled, statusComplete, startTime, endTime, startTimeError, endTimeError, 
				successMessage, modifyStartTime, modifyEndTime, screenShotName);
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