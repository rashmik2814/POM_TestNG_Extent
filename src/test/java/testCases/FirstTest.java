package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.AloginPage;
import pages.SearchHotelPage;
import pages.SelectHotelPage;
import org.testng.Assert;

public class FirstTest extends BaseTest {
	// String testCaseName="First Test";
	@Test
	public void ValidateLoginPage() throws IOException {
		AloginPage loginPage = new AloginPage(driver);
		driver.get("http://adactinhotelapp.com/index.php");
		Assert.assertEquals(driver.findElement((loginPage.loginButton)).isDisplayed(), true);
		loginPage.doLogin("rashmikhedkar", "47HP27");
		}
	@Test
	public void ValidateHotelSearch() throws IOException {
		AloginPage loginPage = new AloginPage(driver);
		driver.get("http://adactinhotelapp.com/index.php");
		loginPage.doLogin("rashmikhedkar", "47HP27");
		SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
		Assert.assertEquals(driver.findElement((searchHotelPage.searchButton)).isDisplayed(), true);
	}
	@Test
	public void ValidateSelectHotel() throws IOException {
		AloginPage loginPage = new AloginPage(driver);
		driver.get("http://adactinhotelapp.com/index.php");
		loginPage.doLogin("rashmikhedkar", "47HP27");
		SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
		SelectHotelPage selectHotelPage = new SelectHotelPage(driver);
		//Search for a hotel
		searchHotelPage.searchHotel("Sydney", "Hotel Sunshine", "Deluxe", "1 - One", "2024-10-28", "2024-10-30",
			"1 - One", "1 - One");
		Assert.assertEquals(driver.findElement((selectHotelPage.continueButton)).isDisplayed(), true);
	}
	}
