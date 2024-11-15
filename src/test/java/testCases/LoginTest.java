package testCases;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseTest;
import extentReportListener.ExtentReportManager;
import pages.LoginPage;
import pages.SearchHotelPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {
	@Test
	// Valid Credentials
	public void Login() throws IOException {
		test = ExtentReportManager.createTest("Login with Valid Data", "Adactin Hotel Booking");
		driver.get(ConfigReader.getProperty("app.url"));
		//driver.get("http://adactinhotelapp.com/index.php");
		LoginPage lPage = new LoginPage(driver);
		//Assert.assertEquals(driver.findElement((lPage.loginButton)).isDisplayed(), true);
		lPage.doLogin(ConfigReader.getProperty("Username"), ConfigReader.getProperty("password"));
		//lPage.doLogin("rashmikhedkar", "Rashmi@123");
		SearchHotelPage searchPage = new SearchHotelPage(driver);

		test.log(LogStatus.INFO, "Validate User Login with Valid Credentials");

//		try {
//			Assert.assertTrue(driver.findElement(searchPage.searchButton).isDisplayed());
//				test.log(LogStatus.PASS, "Login with Valid Credintials Passed");
//				captureScreenshot(test.getDescription());
//			}  catch (Exception e) {
//			// Log any other unexpected errors
//			test.log(LogStatus.FAIL, "An unexpected error occurred: " + e.getMessage());
//			captureScreenshot(test.getDescription());
//		throw e;
//		}
		try {
			Assert.assertTrue(driver.findElement(searchPage.searchButton).isDisplayed());
			test.log(LogStatus.PASS, "User logged in successfully");
			captureScreenshot(test.getDescription());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "User Login failed : " + e.getMessage());
			captureScreenshot(test.getDescription());
			throw e;
		}
	}
	

	@Test
	// Invalid Credentials
	public void LoginInvalid() throws IOException {
		test = ExtentReportManager.createTest("Login with Invalid Credintials Test",
				"Test Adactin Hotel Booking Functionality");
		driver.get("http://adactinhotelapp.com/index.php");
		LoginPage lPage = new LoginPage(driver);
		Assert.assertEquals(driver.findElement((lPage.loginButton)).isDisplayed(), true);
		lPage.doLogin("rashmikhedkar28", "47HP27");
		Assert.assertEquals(driver.findElement((lPage.clickHereLink)).isDisplayed(), true);
		test.log(LogStatus.INFO,
				"Validate User Login with Invalid Credentials (should give error message with click here link)");

		try {
			if (driver.findElement(lPage.clickHereLink).isDisplayed()) {
				test.log(LogStatus.PASS, "Login with Invalid Credintials Test passed");
				captureScreenshot("Login with Invalid Credintials Test");
			} else {
				test.log(LogStatus.FAIL, "Login with Invalid Credintials Test failed - Continue button not displayed");
				captureScreenshot("Login with Invalid Credintials Test");
			}
		} catch (NoSuchElementException e) {
			// Log the failure when the element is not found
			test.log(LogStatus.FAIL, "Login with Invalid Credintials Test failed - Continue button not found");
			captureScreenshot("Login with Invalid Credintials Test");
		} catch (Exception e) {
			// Log any other unexpected errors
			test.log(LogStatus.FAIL, "An unexpected error occurred: " + e.getMessage());
			captureScreenshot("Login with Invalid Credintials Test");
		}
	}
}
