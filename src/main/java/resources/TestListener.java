package resources;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;

import extentReportListener.ExtentReportManager;
import utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import java.lang.reflect.Field;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName(), "Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.test.log(LogStatus.PASS, "Test Passed");
        ExtentReportManager.endTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriverInstance(result);
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
        ExtentReportManager.test.log(LogStatus.FAIL, "Test Failed");
        ExtentReportManager.test.log(LogStatus.FAIL, result.getThrowable());
        ExtentReportManager.test.log(LogStatus.FAIL, ExtentReportManager.test.addScreenCapture(screenshotPath));
        ExtentReportManager.endTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.test.log(LogStatus.SKIP, "Test Skipped");
        ExtentReportManager.endTest();
    }

    private WebDriver getDriverInstance(ITestResult result) {
        try {
            Field driverField = result.getInstance().getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
