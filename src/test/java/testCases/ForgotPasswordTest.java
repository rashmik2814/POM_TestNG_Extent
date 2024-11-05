package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.AloginPage;
import pages.ForgotPasswordPage;

public class ForgotPasswordTest extends BaseTest {
	@Test
	public void ForgotPassword() throws IOException {
		driver.get("http://adactinhotelapp.com/index.php");
		AloginPage lPage = new AloginPage(driver);
		driver.findElement(lPage.ForgetPasswordLink).click();
		ForgotPasswordPage fPage = new ForgotPasswordPage(driver);
		fPage.fillForgotPasswordForm("rashmikhedkar@gmail.com");
	}
	@Test
	public void EmailIdValidation() throws IOException {
		driver.get("http://adactinhotelapp.com/index.php");
		AloginPage lPage = new AloginPage(driver);
		driver.findElement(lPage.ForgetPasswordLink).click();
		ForgotPasswordPage fPage = new ForgotPasswordPage(driver);
		fPage.fillForgotPasswordForm("rashmikhedkar");
		Assert.assertEquals(driver.findElement(fPage.errorText).isDisplayed(),true);
	}
	
}
