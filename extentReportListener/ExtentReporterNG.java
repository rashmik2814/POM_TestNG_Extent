package extentReportListener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.HTMLReporter;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;

public class ExtentReporterNG implements IReporter {

    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // Define the location and name of the HTML report
        String reportPath = outputDirectory + File.separator + "ExtentReport.html";

        // Initialize ExtentReports with the report path (no need to attach reporters)
        extent = new ExtentReports(reportPath);

        // Initialize the HTMLReporter (if you want to set custom configurations)
        HTMLReporter htmlReporter = new HTMLReporter(reportPath);

        // Configure system info
        extent.addSystemInfo("OS", "Windows");
        extent.addSystemInfo("Tester", "Rashmi Khedkar");

        // Loop through all suites and generate a report for each suite
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                // Build test nodes for passed, failed, and skipped tests
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }

        // Finally, flush the report to the output file
        extent.flush();
    }

    // Helper method to build the test nodes (test results) in the Extent report
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                // Create a new test in the report
                ExtentTest test = extent.startTest(result.getMethod().getMethodName());

                // Set the start and end time for the test
                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                // Assign groups (categories) to the test
                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                // Log the result status (pass, fail, skip)
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                // End the test after logging all results
                extent.endTest(test);
            }
        }
    }

    // Helper method to convert milliseconds to Date
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
