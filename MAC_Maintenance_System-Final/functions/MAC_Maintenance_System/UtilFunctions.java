package MAC_Maintenance_System;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UtilFunctions {

	
	public void takeScreenShot(WebDriver driver, String screenshotName) {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
			Thread.sleep(1_000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Register(WebDriver driver, Properties prop, String username, String password, String role, String firstname, String middlename, String lastname,
			String utaId, String phone, String email, String numericAddress, String streetAddress, String city,
			String state, String zipcode, String screenshotname) throws InterruptedException
	{
		// Provide Username
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Username"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Username"))).sendKeys(username);
	    // Provide password
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Password"))).sendKeys(password);
	    // Provide role
	    new Select(driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Role")))).selectByVisibleText(role);
	    // Provide firstname
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Firstname"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Firstname"))).sendKeys(firstname);
	    // Provide middleName
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Middlename"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Middlename"))).sendKeys(middlename);
	    // Provide lastname
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Lastname"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Lastname"))).sendKeys(lastname);
	    // Provide utaId
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_UTAId"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_UTAId"))).sendKeys(utaId);
	    // Provide phone
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Phone"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Phone"))).sendKeys(phone);
	    // Provide email
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Email"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Email"))).sendKeys(email);
	    // Provide numericAddress
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_NumericAddress"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_NumericAddress"))).sendKeys(numericAddress);
	    // Provide StreetAddress
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_StreetAddress"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_StreetAddress"))).sendKeys(streetAddress);
	    // Provide city
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_City"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_City"))).sendKeys(city);
	    // Provide State
	    new Select(driver.findElement(By.xpath(prop.getProperty("Input_SignUp_StateAdmin")))).selectByVisibleText(state);
	    // Provide zipcode
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Zipcode"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_SignUp_Zipcode"))).sendKeys(zipcode);
	    // Click on Login Button
	    driver.findElement(By.xpath(prop.getProperty("Button_SignUp_Submit"))).click();	
	    takeScreenShot(driver, screenshotname);
	}
	
	public void verifyRegistrationErrorMessages(WebDriver driver, Properties prop, String usernameError, String passwordError, String firstnameError, String middlenameError,
			String lastnameError, String utaIdError, String phoneError, String emailError, String numericAddressError,
			String streetAddressError, String cityError,String zipcodeError, String snapShotName) throws InterruptedException
	{
		try {
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_UsernameError"))).getAttribute("value").equals(usernameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_PasswordError"))).getAttribute("value").equals(passwordError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_FirstnameError"))).getAttribute("value").equals(firstnameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_LastnameError"))).getAttribute("value").equals(lastnameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_UTAIdError"))).getAttribute("value").equals(utaIdError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_PhoneError"))).getAttribute("value").equals(phoneError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_EmailError"))).getAttribute("value").equals(emailError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_StreetAddressError"))).getAttribute("value").equals(streetAddressError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_CityError"))).getAttribute("value").equals(cityError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_ZipcodeError"))).getAttribute("value").equals(zipcodeError));
		} catch (NoSuchElementException e) {}		
	}
	

	public void Login(WebDriver driver, Properties prop, String repairer_login_username, String repairer_login_password, String screenshotname) throws InterruptedException {

		// click on Login Link
		driver.findElement(By.xpath(prop.getProperty("Txt_Login"))).click();
		//		Thread.sleep(500);
		// Provide Username
		driver.findElement(By.xpath(prop.getProperty("Input_Login_username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Login_username"))).sendKeys(repairer_login_username);
		//	    Thread.sleep(500);
		// Provide Password
		driver.findElement(By.xpath(prop.getProperty("Input_Login_password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Login_password"))).sendKeys(repairer_login_password);
		Thread.sleep(500);
		// Click on Login Button
		takeScreenShot(driver, screenshotname + "BeforeLogin");
		driver.findElement(By.xpath(prop.getProperty("Btn_Login_submit"))).click();	
		takeScreenShot(driver, screenshotname + "AfterLogin");
		Thread.sleep(500);
	}
	
	public void verifyLoginErrorMessages(WebDriver driver, Properties prop, String usernameError, String PasswordError, String snapShotName) throws InterruptedException {	
		try {
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_Username_Error"))).getAttribute("value").equals(usernameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_Password_Error"))).getAttribute("value").equals(PasswordError));
		} catch (NoSuchElementException e) {
			
		}
	}
}
