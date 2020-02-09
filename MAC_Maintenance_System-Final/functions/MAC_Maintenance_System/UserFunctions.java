package MAC_Maintenance_System;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UserFunctions{
	
	private UtilFunctions util = new UtilFunctions();
	public WebDriver driver;
	public Properties prop;
	
	public void CreateMar(WebDriver driver,Properties prop, String user_login_username, String user_login_password, String sMarName, String sDesc, String snapShotName) throws InterruptedException {
		util.Login(driver, prop, user_login_username, user_login_password, "Login Create Mar");
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("Txt_ReportProblem"))).click();
		//Enter MAR name
		driver.findElement(By.xpath(prop.getProperty("Input_Mar_name"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Mar_name"))).sendKeys(sMarName);
		Thread.sleep(500);
		//Enter description
		driver.findElement(By.xpath(prop.getProperty("Input_Mar_description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Input_Mar_description"))).sendKeys(sDesc);
		Thread.sleep(1000);
		// Click on Submit Button
	    driver.findElement(By.xpath(prop.getProperty("Btn_Mar_submit"))).click();		
	    util.takeScreenShot(driver, snapShotName);
	    Thread.sleep(500);
	}
	
	public void updateProfile(WebDriver driver, String user_login_username, String user_login_password, 
			String user_update_password, String firstname, String middlename, String lastname,
			String phone, String email, String numericAddress, String streetAddress, String city,
			String state, String zipcode, String snapShotName) throws InterruptedException {
		String updtFirstname, updtLastname, updtPhone, updtEmail, updtNumericAddress, updtStreetAddress, updtCity,
				updtState, updtZipcode;
		util.Login(driver, prop, user_login_username, user_login_password, "User Update Profile");
		Thread.sleep(500);
		//Click on View My Profile link
		if(user_login_username.startsWith("user"))
		{
			driver.findElement(By.xpath(prop.getProperty("Lnk_ViewMyProfile_User"))).click();
		}
		else if (user_login_username.startsWith("fm")) {
			driver.findElement(By.xpath(prop.getProperty("Lnk_ViewMyProfile_Fm"))).click();
		}
		else if (user_login_username.startsWith("repair")) {
			driver.findElement(By.xpath(prop.getProperty("Lnk_ViewMyProfile_Repair"))).click();
		}
		else if (user_login_username.startsWith("admin")) {
			driver.findElement(By.xpath(prop.getProperty("Lnk_ViewMyProfile_Admin"))).click();
		}
		
		//Click on Update profile link
		driver.findElement(By.xpath(prop.getProperty("Lnk_UpdateProfile"))).click();
		
		// Update password
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Password"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Password"))).sendKeys(user_update_password);
	    Thread.sleep(500);
	    // Update firstname
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Firstname"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Firstname"))).sendKeys(firstname);
	    Thread.sleep(300);
	    // Update middleName
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Middlename"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Middlename"))).sendKeys(middlename);
	    Thread.sleep(300);
	    // Update lastname
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Lastname"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Lastname"))).sendKeys(lastname);
	    Thread.sleep(300);
	    // Update phone
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Phone"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Phone"))).sendKeys(phone);
	    Thread.sleep(300);
	    // Update email
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Email"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Email"))).sendKeys(email);
	    Thread.sleep(300);
	    // Update numericAddress
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_NumericAddress"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_NumericAddress"))).sendKeys(numericAddress);
	    Thread.sleep(300);
	    // Update StreetAddress
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_StreetAddress"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_StreetAddress"))).sendKeys(streetAddress);
	    Thread.sleep(300);
	    // Update city
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_City"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_City"))).sendKeys(city);
	    Thread.sleep(300);
	    new Select(driver.findElement(By.xpath(prop.getProperty("Input_Update_State")))).selectByValue(state);;
	    Thread.sleep(300);
	    // Update zipcode
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Zipcode"))).clear();
	    driver.findElement(By.xpath(prop.getProperty("Input_Update_Zipcode"))).sendKeys(zipcode);
	    Thread.sleep(300);
	    // Click on submit Button
	    driver.findElement(By.xpath(prop.getProperty("Btn_Update_submit"))).click();	
	    util.takeScreenShot(driver, snapShotName);

	    List<WebElement> links = driver.findElements(By.tagName("a"));
	    
	    for(WebElement we : links) {
		    if(we.getText().contentEquals("Update profile")) {
			    updtFirstname = driver.findElement(By.id(prop.getProperty("Txt_Updated_Firstname"))).getAttribute("value");
			    updtLastname = driver.findElement(By.id(prop.getProperty("Txt_Updated_Lastname"))).getAttribute("value");
			    updtPhone = driver.findElement(By.id(prop.getProperty("Txt_Updated_Phone"))).getAttribute("value");
			    updtEmail = driver.findElement(By.id(prop.getProperty("Txt_Updated_Email"))).getAttribute("value");
			    updtStreetAddress = driver.findElement(By.id(prop.getProperty("Txt_Updated_StreetAddress"))).getAttribute("value");
			    updtCity = driver.findElement(By.id(prop.getProperty("Txt_Updated_City"))).getAttribute("value");
			    updtState = driver.findElement(By.id(prop.getProperty("Txt_Updated_State"))).getAttribute("value");
			    updtZipcode = driver.findElement(By.id(prop.getProperty("Txt_Updated_Zipcode"))).getAttribute("value");
			    updtNumericAddress = driver.findElement(By.id(prop.getProperty("Txt_Updated_NumericAddress"))).getAttribute("value");
			    
			    assertTrue(updtFirstname.equals(firstname));
			    assertTrue(updtLastname.equals(lastname));
			    assertTrue(updtPhone.equals(phone));
			    assertTrue(updtEmail.equals(email));
			    assertTrue(updtNumericAddress.equals(numericAddress));
			    assertTrue(updtStreetAddress.equals(streetAddress));
			    assertTrue(updtCity.equals(city));
			    assertTrue(updtState.equals(state));
			    assertTrue(updtZipcode.equals(zipcode));
		    }
	    }
	    driver.findElement(By.id(prop.getProperty("Lnk_Update_Home"))).click();	
	    driver.findElement(By.id(prop.getProperty("Lnk_logout"))).click();
	}
	
	public void verifyUpdateProfileErrorMessages(WebDriver driver, String passwordError, String firstnameError, 
			String lastnameError, String phoneError,String emailError,String numericAddressError, String streetAddressError, String cityError,
			String zipcodeError, String snapShotName) throws InterruptedException
	{
		try {
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_PasswordError"))).getAttribute("value").equals(passwordError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_FirstnameError"))).getAttribute("value").equals(firstnameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_LastnameError"))).getAttribute("value").equals(lastnameError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_PhoneError"))).getAttribute("value").equals(phoneError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_EmailError"))).getAttribute("value").equals(emailError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_StreetAddressError"))).getAttribute("value").equals(streetAddressError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_CityError"))).getAttribute("value").equals(cityError));
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_SignUp_ZipcodeError"))).getAttribute("value").equals(zipcodeError));
		} catch (NoSuchElementException e) {}
		
	}
	
	
	public void Logout(WebDriver driver, String user_login_username, String user_login_password, String screenshotname) throws InterruptedException {
		// click on Login Link
		driver.findElement(By.xpath(prop.getProperty("Txt_Login"))).click();
		Thread.sleep(1000);
		util.Login(driver, prop, user_login_username, user_login_password, "Login Logout Scenario");
		Thread.sleep(1000);
		util.takeScreenShot(driver, screenshotname);
		driver.findElement(By.xpath(prop.getProperty("Input_Create_Mar_Logout"))).click();		
	}
}
