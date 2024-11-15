package extentReportListener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportManager {
    private static ExtentReports extent;
    public static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
            extent.addSystemInfo("Host Name", "YourHost")
                  .addSystemInfo("Environment", "QA")
                  .addSystemInfo("User Name", "YourName");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        test = getInstance().startTest(testName, description);
        return test;
    }

    public static void endTest() {
        if (extent != null) {
            extent.endTest(test);
        }
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
