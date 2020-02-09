package edu.advanceST.selinium;

import static org.junit.Assert.assertTrue;
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
import org.openqa.selenium.chrome.ChromeDriver;

import MAC_Maintenance_System.UtilFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SeleniumTC03 extends MAC_Maintenance_System.AdminFunctions{
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	public static String sAppURL, sSharedUIMapPath, username,password;
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
	    username = prop.getProperty("admin_login_username");
	    password = prop.getProperty("admin_login_password");
	    
	    // Load Shared UI Map
	    prop.load(new FileInputStream(sSharedUIMapPath));
	}
	
	
	  	@Test
		@FileParameters(value="excel/TC03a_test_cases.csv")
	  	public void adminRegistration(String testCaseNo, String username, String password, String role, String firstname, String middlename, String lastname,
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
					zipcodeError, (methodName + " verifyAdminRegisterErrMsg test case " + testCaseNo));
		}
	  
	  @Test
		@FileParameters(value="excel/TC03b_test_cases.csv")
		public void admin_Login(String testCaseNo, String username, String password, String usernameError,
				String PasswordError) throws Exception {
			driver.get(sAppURL + "logout");
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			String screenShotName = methodName +" AdminLogin test case " + testCaseNo;
			util.Login(driver, prop, username, password, screenShotName);
			Thread.sleep(200);
		  	util.verifyLoginErrorMessages(driver, prop, usernameError, PasswordError, (methodName+" verifyAdminLoginErrMsg test case "+testCaseNo));
		}
	  	
	  @Test
		@FileParameters(value="excel/TC03c_test_cases.csv")
		public void adminSearchProfile(String testCaseNo, String uName, String UTAid, String Role, String usernameError, String utaIdError) throws Exception {
			driver.get(sAppURL + "logout");
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			String screenShotName = methodName +" AdminLogin test case " + testCaseNo;
			SearchUserRole(driver, username, password, uName, UTAid, Role, screenShotName);
		}
	  
	  @Test
		@FileParameters(value="excel/TC03d_test_cases.csv")
		public void adminChangeProfile(String testCaseNo, String sRole, String cRole) throws Exception {
			driver.get(sAppURL + "logout");
			String methodName= new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			String screenShotName = methodName +" AdminLogin test case " + testCaseNo;
			ChangeUserRole(driver, username, password, sRole, cRole, screenShotName);
		}
	  
	  @Test
		@FileParameters(value = "excel/TC03f_test_cases.csv")
		public void adminEditUserProfile(String testCaseNo, String adminUsername, String adminPassword, String username,
				String role, String firstname, String middlename, String lastname, String phone, 
				String email, String numericAddress, String streetAddress, String city, String state, String zipcode, 
				String passwordError, String firstnameError, String lastnameError, String phoneError, String emailError, 
				String streetAddressError, String cityError, String zipcodeError) throws Exception {

			String methodName = new Throwable().getStackTrace()[0].getMethodName();
			driver.get(sAppURL);
			String screenShotName = methodName + " UserUpdateProfile test case " + testCaseNo;
			editUserProfile(driver, adminUsername, adminPassword, username, role, firstname, middlename, lastname, phone, email,
					numericAddress, streetAddress, city, state, zipcode, screenShotName);
			Thread.sleep(500);
			verifyEditUserProfileErrorMessages(driver, passwordError, firstnameError, 
				lastnameError, phoneError, emailError, streetAddressError, cityError,
				zipcodeError, (methodName + " verifyEditUserProfileErrorMessages test case " + testCaseNo));

		}
		
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}


}
