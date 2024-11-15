package testCases;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseTest;
import extentReportListener.ExtentReportManager;
import pages.LoginPage;
import pages.NewUserRegistrationPage;
import org.testng.Assert;

public class NewUserRegistrationTest extends BaseTest {

	@Test
	public void ValidateNewUserRegistration() throws IOException {
		test = ExtentReportManager.createTest("New User Registration Test", "Test Adactin Hotel Booking Functionality");
		driver.get("http://adactinhotelapp.com/index.php");
		LoginPage lPage = new LoginPage(driver);
		driver.findElement(lPage.NewUserRegistrationLink).click();
		NewUserRegistrationPage rPage = new NewUserRegistrationPage(driver);
		rPage.nurForm("Ramnaresh", "Ram@123", "Ram@123", "Rameshwar", "ramnaresh@gmail.com");
		Assert.assertEquals(driver.findElement((rPage.rePasswordField)).isDisplayed(), true);

		test.log(LogStatus.INFO, "Validate New User Registration Link");

		try {
			if (driver.findElement(rPage.registerButton).isDisplayed()) {
				test.log(LogStatus.PASS, "New User Registration Functionality Passed");
				captureScreenshot("New User Registration Test");
			} else {
				test.log(LogStatus.FAIL, "New User Registration Functionality Failed - Continue button not displayed");
				captureScreenshot("New User Registration Test");
			}
		} catch (NoSuchElementException e) {
			// Log the failure when the element is not found
			test.log(LogStatus.FAIL, "New User Registration Functionality Failed - Continue button not found");
			captureScreenshot("New User Registration Test");
		} catch (Exception e) {
			// Log any other unexpected errors
			test.log(LogStatus.FAIL, "An unexpected error occurred: " + e.getMessage());
			captureScreenshot("New User Registration Test");
		}
	}
}