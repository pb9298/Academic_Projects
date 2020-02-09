package edu.advanceST.selinium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import MAC_Maintenance_System.UtilFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class SeleniumTC01 extends MAC_Maintenance_System.UserFunctions {

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
		prop.load(new FileInputStream("./login/Login.properties"));
		username = prop.getProperty("user_login_username");
		password = prop.getProperty("user_login_password");

		// Load Shared UI Map
		prop.load(new FileInputStream(sSharedUIMapPath));
	}

	@Test
	@FileParameters(value = "excel/TC01a_test_cases.csv")
	public void UserRegistration(String testCaseNo, String username, String password, String role, String firstname,
			String middlename, String lastname, String utaId, String phone, String email, String numericAddress,
			String streetAddress, String city, String state, String zipcode, String usernameError, String passwordError,
			String firstnameError, String middlenameError, String lastnameError, String utaIdError, String phoneError,
			String emailError, String numericAddressError, String streetAddressError, String cityError,
			String zipcodeError) throws Exception {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL + "registration");
		String screenShotName = methodName + " UserReg test case " + testCaseNo;
		util.Register(driver, prop, username, password, role, firstname, middlename, lastname, utaId, phone, email,
				numericAddress, streetAddress, city, state, zipcode, screenShotName);
		Thread.sleep(500);
		util.verifyRegistrationErrorMessages(driver, prop, usernameError, passwordError, firstnameError, middlenameError,
				lastnameError, utaIdError, phoneError, emailError, numericAddressError, streetAddressError, cityError,
				zipcodeError, (methodName + " verifyUserRegisterErrMsg test case " + testCaseNo));

	}

	@Test
	@FileParameters(value = "excel/TC01b_test_cases.csv")
	public void UserLogin(String testCaseNo, String username, String password, String usernameError,
			String PasswordError) throws Exception {
		driver.get(sAppURL + "logout");
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName + " UserLogin test case " + testCaseNo;
		util.Login(driver, prop, username, password, screenShotName);
		Thread.sleep(200);
		util.verifyLoginErrorMessages(driver, prop, usernameError, PasswordError,
				(methodName + " verifyUserLoginErrMsg test case " + testCaseNo));

	}

	@Test
	@FileParameters(value = "excel/TC01c_test_cases.csv")
	public void UserCreateMar(String testCaseNo, String marname, String desc, String marnameError, String descError)
			throws Exception {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);

		String screenShotName = methodName + " UserCreateMAR test case " + testCaseNo;
		CreateMar(driver, prop, username, password, marname, desc, screenShotName);
		Thread.sleep(200);
		try {
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Marname_Error"))).getAttribute("value")
					.equals(marnameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Desc_Error"))).getAttribute("value")
					.equals(descError));
		} catch (NoSuchElementException e) {
		}
	}

	
	@Test
	@FileParameters(value = "excel/TC01f_test_cases.csv")
	public void UserUpdateProfile(String testCaseNo, String username, String password, String updatePassword, 
			String firstname, String middlename, String lastname, String phone, String email, String numericAddress,
			String streetAddress, String city, String state, String zipcode, String passwordError,
			String firstnameError, String lastnameError, String phoneError, String emailError, String numericAddressError, String streetAddressError, String cityError,
			String zipcodeError) throws Exception {

		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		driver.get(sAppURL);
		String screenShotName = methodName + " UserUpdateProfile test case " + testCaseNo;
		updateProfile(driver, username, password, updatePassword, firstname, middlename, lastname, phone, email,
				numericAddress, streetAddress, city, state, zipcode, screenShotName);
		Thread.sleep(500);
		verifyUpdateProfileErrorMessages(driver, passwordError, firstnameError, 
			lastnameError, phoneError, emailError, numericAddressError, streetAddressError, cityError,
			zipcodeError, (methodName + " verifyUpdateProfileErrorMessages test case " + testCaseNo));

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
