package base;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import extentReportListener.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ScreenshotUtil;

public class BaseTest {

	protected WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;
//for initialization of extent report
	@BeforeSuite
	public void setupExtentReports() {
		extent = ExtentReportManager.getInstance();
	}
//initialization of webdriver
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	//to quit the webdriver

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	//to flush the extent report

	@AfterSuite
	public void tearDownExtentReports() {
		if (extent != null) {
			extent.flush();
		}
	}

	// To create and attach screenshots in Extent Reports
	protected void captureScreenshot(String testName) {
		String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
		test.log(LogStatus.INFO, test.addScreenCapture(screenshotPath));
	}

}
