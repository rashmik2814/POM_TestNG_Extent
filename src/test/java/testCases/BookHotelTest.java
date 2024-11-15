package testCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseTest;
import extentReportListener.ExtentReportManager;
import pages.BookHotelPage;
import pages.BookedItineraryPage;
import pages.BookingConfirmationPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.SearchHotelPage;
import pages.SelectHotelPage;
import utils.ConfigReader; // Import ConfigReader

public class BookHotelTest extends BaseTest {
	@Test
	public void HotleBookingEndToEndTest() throws IOException {
		//to create the extent report with description
		test = ExtentReportManager.createTest("Adactin Hotel Booking Test",
				"Test end-to-end flow of hotel booking portal");

		// Load the application URL from config.properties
		driver.get(ConfigReader.getProperty("app.url"));

		LoginPage lPage = new LoginPage(driver);
		// Load login credentials from config.properties
		lPage.doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
		// Search Hotel Page
		SearchHotelPage searchPage = new SearchHotelPage(driver);
		
		// for taking screenshot of successful login
		
		test.log(LogStatus.INFO, "Validate User Login");
		try {
			Assert.assertTrue(driver.findElement(searchPage.searchButton).isDisplayed());
			test.log(LogStatus.PASS, "User logged in successfully");
			captureScreenshot(test.getDescription());

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "User Login failed : " + e.getMessage());
			captureScreenshot(test.getDescription());
			throw e;
		}

		// Retrieving hotel search parameters from config.properties through method called in config.reader
		
		searchPage.searchHotel(ConfigReader.getProperty("location"), ConfigReader.getProperty("hotelName"),
				ConfigReader.getProperty("roomType"), ConfigReader.getProperty("numberOfRooms"),
				ConfigReader.getProperty("checkInDate"), ConfigReader.getProperty("checkOutDate"),
				ConfigReader.getProperty("adults"), ConfigReader.getProperty("children"));
		
		// creating object for Select Hotel Page and verify that hotel searched is correct
		
		SelectHotelPage selectPage = new SelectHotelPage(driver);

		test.log(LogStatus.INFO, "Validate Search Hotel");
		
		//Fetching the hotel name from application and storing it in variable searchedHotelName.
		
		String searchedHotelName = driver.findElement(selectPage.hotelNameLocator).getAttribute("value");

		try {
		//validating the variable value is same as application
			Assert.assertEquals(searchedHotelName, ConfigReader.getProperty("hotelName"));
			test.log(LogStatus.PASS, "Hotel searched successfully");
			captureScreenshot(test.getDescription());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Search failed : " + e.getMessage());
			captureScreenshot(test.getDescription());
			throw e;
		}

		// Select searched hotel
		selectPage.selectHotel();

		// Book hotel page
		BookHotelPage bookPage = new BookHotelPage(driver);

		// Retrieve booking details from config.properties
		bookPage.bookHotel(ConfigReader.getProperty("firstName"), ConfigReader.getProperty("lastName"),
				ConfigReader.getProperty("billingAddress"), ConfigReader.getProperty("creditCardNumber"),
				ConfigReader.getProperty("creditCardType"), ConfigReader.getProperty("expiryMonth"),
				ConfigReader.getProperty("expiryYear"), ConfigReader.getProperty("cvv"));

		BookingConfirmationPage confirmationPage = new BookingConfirmationPage(driver);
		
		//Fetching the order number from application and storing it in variable orderNumber.
		
		String orderNumber = driver.findElement(confirmationPage.orderNo).getAttribute("value");

		test.log(LogStatus.INFO, "Validate Book Hotel");
		try {
			Assert.assertTrue(driver.findElement(confirmationPage.orderNo).isDisplayed());
			test.log(LogStatus.PASS, "Hotel Booked successfully");
			captureScreenshot(test.getDescription());
			
		//to print this order number to the extent report
			
			test.log(LogStatus.INFO, "Order No is :- " + orderNumber);
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Hotel Booking Functionality failed : " + e.getMessage());
			captureScreenshot(test.getDescription());
			throw e;
		}

		// click on My Itinerary Button
		driver.findElement(confirmationPage.myItinerary).click();
		
		// Booked Itinerary Page
		BookedItineraryPage bookedPage = new BookedItineraryPage(driver);
		
		test.log(LogStatus.INFO, "Validate that user able to search the Itinerary");
		
		bookedPage.checkBookedItinerary(orderNumber);

		try {
			Assert.assertTrue(driver.findElement(By.cssSelector("input[value=\"" + orderNumber + "\"]")).isDisplayed());
			test.log(LogStatus.PASS, "Itinerary validated successfully with order Number:- " + orderNumber);
			captureScreenshot(test.getDescription());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate itinerary: " + e.getMessage());
			captureScreenshot(test.getDescription());
			throw e;
		}

		// Logout page
		driver.findElement(bookedPage.logOutButton).click();
		LogoutPage logoutPage = new LogoutPage(driver);

		test.log(LogStatus.INFO, "Validate Logout Screen");
		try {
			Assert.assertTrue(driver.findElement(logoutPage.logoutLastScreen).isDisplayed());
			test.log(LogStatus.PASS, "Hotel Search Functionality Passed");
			captureScreenshot(test.getDescription());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "User logout Failed : " + e.getMessage());
			captureScreenshot(test.getDescription());
			throw e;
		}
	}
}
