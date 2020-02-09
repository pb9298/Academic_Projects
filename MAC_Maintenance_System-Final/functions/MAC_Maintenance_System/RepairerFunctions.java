package MAC_Maintenance_System;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import edu.advanceST.model.Reservation;
import edu.advanceST.util.SQLConnection;

public class RepairerFunctions {

	private UtilFunctions util = new UtilFunctions();
	public WebDriver driver;
	public Properties prop;

	public void verifyHeaders(WebDriver driver, String header1OnPage, String expectedHeader1Name, String header2OnPage,
			String expectedHeader2Name, String header3OnPage, String expectedHeader3Name, String header4OnPage,
			String expectedHeader4Name, String header5OnPage, String expectedHeader5Name, String header6OnPage,
			String expectedHeader6Name, String header7OnPage, String expectedHeader7Name, String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header1OnPage))).getText().equals(expectedHeader1Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header2OnPage))).getText().equals(expectedHeader2Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header3OnPage))).getText().equals(expectedHeader3Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header4OnPage))).getText().equals(expectedHeader4Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header5OnPage))).getText().equals(expectedHeader5Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header6OnPage))).getText().equals(expectedHeader6Name));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(header7OnPage))).getText().equals(expectedHeader7Name));
		util.takeScreenShot(driver, snapShotName);
	}

	public void verifyHeadersRes(WebDriver driver, String headerReservationId, String expectedHeaderReservationId,
			String headerDate, String expectedHeaderDate, String headerStatus, String expectedHeaderStatus) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty(headerReservationId))).getText()
				.equals(expectedHeaderReservationId));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(headerDate))).getText().equals(expectedHeaderDate));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(headerStatus))).getText().equals(expectedHeaderStatus));
	}

	public void verifyContentsSpecific(WebDriver driver, String row1ValueOnpage, String expectedrow1Value,
			String row2ValueOnpage, String expectedrow2Value, String row3ValueOnpage, String expectedrow3Value,
			String row4ValueOnpage, String expectedrow4Value, String row5ValueOnpage, String expectedrow5Value,
			String row6ValueOnpage, String expectedrow6Value, String row7ValueOnpage, String expectedrow7Value,
			String row8ValueOnpage, String expectedrow8Value, String row9ValueOnpage, String expectedrow9Value,
			String row10ValueOnpage, String expectedrow10Value, String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row1ValueOnpage))).getText().equals(expectedrow1Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row2ValueOnpage))).getText().equals(expectedrow2Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row3ValueOnpage))).getText().equals(expectedrow3Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row4ValueOnpage))).getText().equals(expectedrow4Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row5ValueOnpage))).getText().equals(expectedrow5Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row6ValueOnpage))).getText().equals(expectedrow6Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row7ValueOnpage))).getText().equals(expectedrow7Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row8ValueOnpage))).getText().equals(expectedrow8Value));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row9ValueOnpage))).getText().equals(expectedrow9Value));
		assertTrue(
				driver.findElement(By.xpath(prop.getProperty(row10ValueOnpage))).getText().equals(expectedrow10Value));
		util.takeScreenShot(driver, snapShotName);
	}

	public void verifyHeadersSpecific(WebDriver driver, String row1TitleOnpage, String expectedrow1Title,
			String row2TitleOnpage, String expectedrow2Title, String row3TitleOnpage, String expectedrow3Title,
			String row4TitleOnpage, String expectedrow4Title, String row5TitleOnpage, String expectedrow5Title,
			String row6TitleOnpage, String expectedrow6Title, String row7TitleOnpage, String expectedrow7Title,
			String row8TitleOnpage, String expectedrow8Title, String row9TitleOnpage, String expectedrow9Title,
			String row10TitleOnpage, String expectedrow10Title, String snapShotName) {
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row1TitleOnpage))).getText().equals(expectedrow1Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row2TitleOnpage))).getText().equals(expectedrow2Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row3TitleOnpage))).getText().equals(expectedrow3Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row4TitleOnpage))).getText().equals(expectedrow4Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row5TitleOnpage))).getText().equals(expectedrow5Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row6TitleOnpage))).getText().equals(expectedrow6Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row7TitleOnpage))).getText().equals(expectedrow7Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row8TitleOnpage))).getText().equals(expectedrow8Title));
		assertTrue(driver.findElement(By.xpath(prop.getProperty(row9TitleOnpage))).getText().equals(expectedrow9Title));
		assertTrue(
				driver.findElement(By.xpath(prop.getProperty(row10TitleOnpage))).getText().equals(expectedrow10Title));
		util.takeScreenShot(driver, snapShotName);
	}

	private static ArrayList<Reservation> ReturnMatchingResList(String uName) {
		ArrayList<Reservation> ReservationListInDB = new ArrayList<Reservation>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet reserveList = stmt.executeQuery("Select reservation_id, date, status"
					+ " from reservation where username =" + "\'" + uName + "\' order by reservation_id DESC");
			while (reserveList.next()) {
				Reservation res = new Reservation();
				res.setReservationId(reserveList.getInt("reservation_id"));
				res.setDate(reserveList.getDate("date"));
				res.setStatus(reserveList.getString("status"));
				ReservationListInDB.add(res);
			}
		} catch (SQLException e) {
		}
		return ReservationListInDB;
	}

	private String[][] getReservationListFromDB(int listSize, String uName) throws SQLException { // this method gets
																									// the list mar
																									// table contents
																									// from the DB
		ArrayList<Reservation> fromDB = ReturnMatchingResList(uName);
		String[][] arrayDB = new String[listSize - 1][3];
		System.out.println(arrayDB.length);
		int i = 0;
		for (Reservation p : fromDB) {
			arrayDB[i][0] = Long.toString(p.getReservationId());
			arrayDB[i][1] = p.getDate().toString();
			arrayDB[i][2] = p.getStatus();
			i++;
		}
		return arrayDB;
	}

	private Boolean arraysDiff1(String[][] array1, String[][] array2) { // this method compares the contents of the two
																		// tables
		Boolean diff = false || (array1.length != array2.length);
		System.out.println(array1.length);
		System.out.println(array2.length);
		for (int i = 0; i < array1.length && !diff; i++) {
			diff = !array1[i][0].equals(array2[i][0]) || !array1[i][1].equals(array2[i][1])
					|| !array1[i][2].equals(array2[i][2]);
		}
		return diff;
	}

	private String[][] getResTableContentsFromPage(int listSize) { // this method gets the list mar table contents from
																	// the web page
		String[][] ResArray = new String[listSize - 1][3];
		int a = 0;
		int b = 0;
		List<WebElement> rows = driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")))
				.findElements(By.tagName("tr"));
		for (int j = 2; j <= rows.size(); j++) {
			for (int i = 1; i <= 3; i++) {
				ResArray[a][b] = driver.findElement(By.xpath(
						prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j + "]/td[" + i + "]"))
						.getText();
				b++;
			}
			a++;
			b = 0;
		}
		return ResArray;
	}

	public void ListMyReservation(int testCaseNumber, String methodName, WebDriver driver,
			String repairer_login_username, String repairer_login_password, String col0, String col3, String col6)
			throws InterruptedException {

		util.Login(driver, prop, repairer_login_username, repairer_login_password, "Repair List My Reservation");
		driver.findElement(By.xpath(prop.getProperty("Lnk_List_My_Reservation"))).click();
		verifyHeadersRes(driver, "Txt_Lst_ResID", col0, "Txt_Lst_Date", col3, "Txt_Lst_Status", col6);
		WebElement ResTable = driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")));
		int rows = ResTable.findElements(By.tagName("tr")).size(); // find the number of rows in the table including the
																	// header

		try {
			assertFalse(arraysDiff1(getReservationListFromDB(rows, repairer_login_username),
					getResTableContentsFromPage(rows)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(prop.getProperty("Lnk_Cancel_Res_Repairs_Home"))).click();
		driver.findElement(By.xpath(prop.getProperty("Lnk_Logout"))).click();
	}

	public void RequestReservation(WebDriver driver, String repairer_login_username, String repairer_login_password,
			String startTime, String endTime, String startTimeError, String endTimeError, String successMessage,
			String screenShotName) throws InterruptedException {
		util.Login(driver, prop, repairer_login_username, repairer_login_password, "Request Repair Reservation");
		Thread.sleep(1000);
		// Click on the Search User Role link
		driver.findElement(By.xpath(prop.getProperty("Lnk_List_Assigned_MAR"))).click();
		Thread.sleep(2500);

		List<WebElement> rows = driver.findElement(By.xpath(prop.getProperty("Tbl_Search_Assign_MAR_Table")))
				.findElements(By.tagName("tr"));
		outerloop: for (int j = 2; j < rows.size(); j++) {
			for (int i = 1; i <= 8; i++) {
				driver.findElement(By.xpath(
						prop.getProperty("Tbl_Search_Assign_MAR_Table") + "/tbody/tr[" + j + "]/td[" + "8" + "]/a"))
						.click();
				driver.findElement(By.xpath(prop.getProperty("Btn_Request_Reservation"))).click();

				Thread.sleep(2000);
				util.takeScreenShot(driver, screenShotName);
				new Select(driver.findElement(By.xpath(prop.getProperty("Slt_Start_Time"))))
						.selectByVisibleText(startTime);
				new Select(driver.findElement(By.xpath(prop.getProperty("Slt_End_Time")))).selectByVisibleText(endTime);
				util.takeScreenShot(driver, screenShotName);
				Thread.sleep(2000);
				driver.findElement(By.xpath(prop.getProperty("Btn_Reservation_Submit"))).click();
				assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_StartTime_Error"))).getAttribute("value")
						.equalsIgnoreCase(startTimeError));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_EndTime_Error"))).getAttribute("value")
						.equalsIgnoreCase(endTimeError));
				assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_Success"))).getText()
						.equalsIgnoreCase(successMessage));
				util.takeScreenShot(driver, screenShotName);
				Thread.sleep(1000);
				driver.findElement(By.xpath(prop.getProperty("Lnk_Cancel_Res_Repairs_Home"))).click();
				driver.findElement(By.xpath(prop.getProperty("Lnk_Logout"))).click();
				break outerloop;
			}
		}
	}

	public void CancelReservedRepairers(WebDriver driver, String repairer_login_username,
			String repairer_login_password, String statusPending, String statusApproved, String statusDecline,
			String statusCancelled, String statusComplete, String screenShotName) throws InterruptedException {
		util.Login(driver, prop, repairer_login_username, repairer_login_password, "Cancel Reserved Repairers");
		driver.findElement(By.xpath(prop.getProperty("Lnk_List_My_Reservations"))).click();
		Thread.sleep(2500);

		List<WebElement> rows = driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")))
				.findElements(By.tagName("tr"));
		outerloop: for (int j = 2; j < rows.size(); j++) {
			for (int i = 1; i <= 4; i++) {
				String str = driver.findElement(By.xpath(
						prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j + "]/td[" + "3" + "]"))
						.getText();

				if (str.equalsIgnoreCase(statusPending) || str.equalsIgnoreCase(statusApproved)) {
					util.takeScreenShot(driver, screenShotName);
					Thread.sleep(5000);
					driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j
							+ "]/td[" + "4" + "]/a")).click();
					Thread.sleep(5000);
					WebElement element = driver.findElement(By.xpath(prop.getProperty("Btn_Reservation_Cancel")));
					driver.findElement(By.xpath(prop.getProperty("Btn_Reservation_Cancel"))).click();
					Thread.sleep(2500);
					String str1 = driver.findElement(By.xpath(
							prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j + "]/td[" + "3" + "]"))
							.getText();
					assertTrue(driver.findElement(By.xpath(
							prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j + "]/td[" + "3" + "]"))
							.getText().equalsIgnoreCase(statusCancelled));
					util.takeScreenShot(driver, screenShotName);
					Thread.sleep(2500);

					driver.findElement(By.xpath(prop.getProperty("Lnk_Cancel_Res_Repairs_Home"))).click();
					driver.findElement(By.xpath(prop.getProperty("Lnk_Logout"))).click();
					break outerloop;
				} else {
					util.takeScreenShot(driver, screenShotName);
					continue;
				}
			}
		}
	}

	public void ModifyReservedRepairers(WebDriver driver, String repairer_login_username,
			String repairer_login_password, String statusPending, String statusApproved, String statusDecline,
			String statusCancelled, String statusComplete, String startTime, String endTime, String startTimeError,
			String endTimeError, String modifySuccessMessage, String modifyStartTime, String modifyEndTime,
			String screenShotName) throws InterruptedException {

		String regID, initStartTime, initEndTime;

		util.Login(driver, prop, repairer_login_username, repairer_login_password, "Modify Reserved Repairers");
		driver.findElement(By.xpath(prop.getProperty("Lnk_List_My_Reservations"))).click();
		Thread.sleep(2500);

		List<WebElement> rows = driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")))
				.findElements(By.tagName("tr"));
		outerloop: for (int j = 2; j < rows.size(); j++) {
			for (int i = 1; i <= 4; i++) {
				String str = driver.findElement(By.xpath(
						prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j + "]/td[" + "3" + "]"))
						.getText();

				if (str.equalsIgnoreCase(statusPending) || str.equalsIgnoreCase(statusApproved)) {
					util.takeScreenShot(driver, screenShotName);
					Thread.sleep(1000);
					driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table") + "/tbody/tr[" + j
							+ "]/td[" + "4" + "]/a")).click();
					Thread.sleep(1000);

					regID = driver.findElement(By.xpath(prop.getProperty("Txt_Modify_Res_ID"))).getAttribute("value");
					initStartTime = driver.findElement(By.xpath(prop.getProperty("Txt_Modify_Start_Time")))
							.getAttribute("value");
					initEndTime = driver.findElement(By.xpath(prop.getProperty("Txt_Modify_End_Time")))
							.getAttribute("value");

					driver.findElement(By.xpath(prop.getProperty("Btn_Modify"))).click();
					Thread.sleep(1000);
					util.takeScreenShot(driver, screenShotName);

					new Select(driver.findElement(By.xpath(prop.getProperty("Slt_Start_Time"))))
							.selectByVisibleText(startTime);
					new Select(driver.findElement(By.xpath(prop.getProperty("Slt_End_Time"))))
							.selectByVisibleText(endTime);
					util.takeScreenShot(driver, screenShotName);
					Thread.sleep(1000);
					driver.findElement(By.xpath(prop.getProperty("Btn_Reservation_Submit"))).click();
					assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_StartTime_Error")))
							.getAttribute("value").equalsIgnoreCase(startTimeError));
					assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_EndTime_Error"))).getAttribute("value")
							.equalsIgnoreCase(endTimeError));
					String message = driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_Success")))
							.getText();
					assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Reservation_Success"))).getText()
							.equalsIgnoreCase(modifySuccessMessage));
					util.takeScreenShot(driver, screenShotName);
					Thread.sleep(500);
					driver.findElement(By.xpath(prop.getProperty("Lnk_Modify_Home"))).click();

					driver.findElement(By.xpath(prop.getProperty("Lnk_List_My_Reservations"))).click();

					for (int a = 2; a < rows.size(); a++) {
						for (int b = 1; b <= 4; b++) {
							System.out
									.print(driver
											.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")
													+ "/tbody/tr[" + a + "]/td[" + b + "]"))
											.getAttribute("value") + "\t");
							String modResIDCheck = driver
									.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")
											+ "/tbody/tr[" + a + "]/td[" + "1" + "]"))
									.getText();
							if (modResIDCheck.equalsIgnoreCase(regID)) {
								driver.findElement(By.xpath(prop.getProperty("Tbl_List_My_Reservation_Table")
										+ "/tbody/tr[" + a + "]/td[" + "4" + "]/a")).click();
								Thread.sleep(2000);
								String str11 = driver.findElement(By.xpath(prop.getProperty("Txt_Modify_Start_Time")))
										.getAttribute("value");
								assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Modify_Start_Time")))
										.getAttribute("value").equalsIgnoreCase(modifyStartTime));
								Thread.sleep(1000);
								String str12 = driver.findElement(By.xpath(prop.getProperty("Txt_Modify_End_Time")))
										.getAttribute("value");
								assertTrue(driver.findElement(By.xpath(prop.getProperty("Txt_Modify_End_Time")))
										.getAttribute("value").equalsIgnoreCase(modifyEndTime));
								Thread.sleep(1000);
								driver.findElement(By.xpath(prop.getProperty("Lnk_Modify_Homepage"))).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath(prop.getProperty("Lnk_Logout"))).click();
								break outerloop;
							} else {
								continue;
							}
						}
					}
				} else {
					continue;
				}
			}
		}
	}
}