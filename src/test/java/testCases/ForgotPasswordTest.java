package testCases;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseTest;
import extentReportListener.ExtentReportManager;
import pages.LoginPage;
import pages.ForgotPasswordPage;

public class ForgotPasswordTest extends BaseTest {
	@Test
	public void ForgotPassword() throws IOException {
		test = ExtentReportManager.createTest("Forgot Password Link Test", "Test Adactin Hotel Booking Functionality");
		driver.get("http://adactinhotelapp.com/index.php");
		LoginPage lPage = new LoginPage(driver);
		driver.findElement(lPage.ForgetPasswordLink).click();
		ForgotPasswordPage fPage = new ForgotPasswordPage(driver);
		fPage.fillForgotPasswordForm("rashmikhedkar@gmail.com");
		Assert.assertEquals(driver.findElement(fPage.errorText).isDisplayed(), true);

		test.log(LogStatus.INFO, "Validate Forgot Password Link with Valid Credentials");

		try {
			if (driver.findElement(fPage.errorText).isDisplayed()) {
				test.log(LogStatus.PASS, "Forgot Password Functionality Passed");
				captureScreenshot("Forgot Password Test");
			} else {
				test.log(LogStatus.FAIL, "Forgot Password Functionality Failed - Continue button not displayed");
				captureScreenshot("Forgot Password Test");
			}
		} catch (NoSuchElementException e) {
			// Log the failure when the element is not found
			test.log(LogStatus.FAIL, "Forgot Password Functionality Failed - Continue button not found");
			captureScreenshot("Forgot Password Test");
		} catch (Exception e) {
			// Log any other unexpected errors
			test.log(LogStatus.FAIL, "An unexpected error occurred: " + e.getMessage());
			captureScreenshot("Forgot Password Test");
		}
	}

	@Test
	public void EmailIdValidation() throws IOException {
		test = ExtentReportManager.createTest("Email Id Validation Test", "Test Adactin Hotel Booking Functionality");
		driver.get("http://adactinhotelapp.com/index.php");
		LoginPage lPage = new LoginPage(driver);
		driver.findElement(lPage.ForgetPasswordLink).click();
		ForgotPasswordPage fPage = new ForgotPasswordPage(driver);
		fPage.fillForgotPasswordForm("rashmikhedkar");
		Assert.assertEquals(driver.findElement(fPage.errorText).isDisplayed(), true);
//		driver.findElement(fPage.backToLoginPage).click();
//		Assert.assertEquals(driver.findElement(lPage.loginButton).isDisplayed(), true);

		test.log(LogStatus.INFO, "Validate Forgot Password Link with Invalid Email Id (should give error message)");

		try {
			if (driver.findElement(fPage.errorText).isDisplayed()) {
				test.log(LogStatus.PASS, "Email Id Validation Functionality Passed");
				captureScreenshot("Email Id Validation Test");
			} else {
				test.log(LogStatus.FAIL, "Email Id Validation Functionality Failed - Continue button not displayed");
				captureScreenshot("Email Id Validation Test");
			}
		} catch (NoSuchElementException e) {
			// Log the failure when the element is not found
			test.log(LogStatus.FAIL, "Email Id Validation Functionality Failed - Continue button not found");
			captureScreenshot("Email Id Validation Test");
		} catch (Exception e) {
			// Log any other unexpected errors
			test.log(LogStatus.FAIL, "An unexpected error occurred: " + e.getMessage());
			captureScreenshot("Email Id Validation Test");
		}
	}
}