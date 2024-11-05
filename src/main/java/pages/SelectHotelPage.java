package pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class SelectHotelPage extends BasePage {

	//constructor
	public SelectHotelPage(WebDriver driver) throws IOException {
		super(driver);
	}

	// Web element for hotel search results
	public By hotelTable = By.cssSelector("td[class=\"login_title\"]"); 
	public By continueButton = By.cssSelector(".reg_button[value=\"Continue\"]");

	// Method to check if a hotel is present in the results
	public String isHotelPresent(String hotelName) {
		return driver.findElement(By.cssSelector("td[class=\"login_title\"]")).getText();
	}
	}
