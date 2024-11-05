package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.AloginPage;

//import pages.SearchHotelPage;
//import pages.SelectHotelPage;
//import org.testng.Assert;

public class LoginTest extends BaseTest {
	@Test
	//Valid Credentials
		public void Login() throws IOException {
		driver.get("http://adactinhotelapp.com/index.php");
		AloginPage lPage = new AloginPage(driver);
		Assert.assertEquals(driver.findElement((lPage.loginButton)).isDisplayed(), true);
		lPage.doLogin("rashmikhedkar", "Rashmi@123");
		}
	@Test
	//Invalid Credentials
		public void LoginInvalid() throws IOException {
		driver.get("http://adactinhotelapp.com/index.php");
		AloginPage lPage = new AloginPage(driver);
		Assert.assertEquals(driver.findElement((lPage.loginButton)).isDisplayed(), true);
		lPage.doLogin("rashmikhedkar28", "47HP27");
		Assert.assertEquals(driver.findElement((lPage.clickHereLink)).isDisplayed(), true);
		}	
	}
