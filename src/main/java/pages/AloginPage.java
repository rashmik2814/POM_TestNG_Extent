package pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class AloginPage extends BasePage {

	// constructor
	public AloginPage(WebDriver driver) throws IOException {
		super(driver);
	}

	// Web elements
	public By usernameField = By.id("username");
	public By passwordField = By.id("password");
	public By loginButton = By.id("login");
	public By clickHereLink = By.cssSelector("div[class=\"auth_error\"] a");
	public By ForgetPasswordLink = By.cssSelector("div[class=\"login_forgot\"] a");
	public By NewUserRegistration = By.cssSelector("td[class=\"login_register\"] a");

	// Methods
	public void doLogin(String username, String password) {
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
	}
//	public void invalidUsernamePassword (String username, String password) {
//		driver.findElement(clickHereLink).click();
//	}
	}

