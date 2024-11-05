package base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

public class BasePage {

	protected WebDriver driver;

	// constructor
	public BasePage(WebDriver driver) throws IOException {
		this.driver = driver;
	}

}
