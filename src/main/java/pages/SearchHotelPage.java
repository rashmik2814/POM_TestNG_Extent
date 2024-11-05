package pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import base.BasePage;

public class SearchHotelPage extends BasePage {

	// constructor
	public SearchHotelPage(WebDriver driver) throws IOException {
		super(driver);
	}

	// Web elements
	public By locationDropdown = By.id("location");
	public By hotelDropdown = By.id("hotels");
	public By roomTypeDropdown = By.id("room_type");
	public By numOfRoomsDropdown = By.id("room_nos");
	public By checkInField = By.id("datepick_in");
	public By checkOutField = By.id("datepick_out");
	public By adultDropdown = By.id("adult_room");
	public By childDropdown = By.id("child_room");
	public By searchButton = By.id("Submit");

	// Methods
	public void searchHotel(String location, String hotel, String roomType, String numOfRooms, String checkInDate,
			String checkOutDate, String adults, String children) {

		new Select(driver.findElement(locationDropdown)).selectByVisibleText(location);
		new Select(driver.findElement(hotelDropdown)).selectByVisibleText(hotel);
		new Select(driver.findElement(roomTypeDropdown)).selectByVisibleText(roomType);
		new Select(driver.findElement(numOfRoomsDropdown)).selectByVisibleText(numOfRooms);
		driver.findElement(checkInField).sendKeys(checkInDate);
		driver.findElement(checkOutField).sendKeys(checkOutDate);
		new Select(driver.findElement(adultDropdown)).selectByVisibleText(adults);
		new Select(driver.findElement(childDropdown)).selectByVisibleText(children);
		driver.findElement(searchButton).click();
	}

}
