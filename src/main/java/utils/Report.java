package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static driverfactory.DriverFactory.getWebDriver;
import static utils.DataGenerate.getRunDatetime;

public class Report {

    public static String testRunDateTime = getRunDatetime();
    //implement to me a Report class that will be used to generate the report and capture the screenshot include the following methods: initReport(), addTestFeaturesReport(), addMethodReportWithoutDataProvider(), addMethodReportWithDataProvider(), closeAndSave(), screenshotPath()
    private static ExtentReports extentReports;
    private static String reportDirectory;

    public static void initReport() {

        reportDirectory = "reports/" + testRunDateTime;
        File directory = new File(reportDirectory);
        directory.mkdirs();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("dd/mm/yyyy, hh:mm:ss'('zzz')'");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

    }

    public static ExtentTest addTestFeaturesReport(String featureName) {
        return extentReports.createTest(featureName);
    }

    public static synchronized void addMethodReport(ExtentTest testFeature, Status status, String methodName, Throwable throwable) {

        switch (status) {
            //Pass
            case PASS -> testFeature.createNode(methodName).pass("Passed");
            //Skip
            case SKIP -> testFeature.createNode(methodName).skip("Skipped");
            //Fail
            case FAIL -> {
                testFeature.createNode(methodName).fail(throwable).addScreenCaptureFromPath(screenshotFilePath(methodName));
            }
        }
    }

    private static String screenshotFilePath(String methodName) {
        File screenshot;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss-SSS");
        String time = timeFormat.format(new Date());

        String screenshotDirectory = reportDirectory + "/screenshot";

        File directory = new File(screenshotDirectory);
        directory.mkdirs();

        File destFile = new File(directory, time + "Lodash" + methodName + ".png");
        try {
            screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, destFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "." + File.separator + destFile.getPath();

    }

    private static String screenshotBase64String(String methodName) {
        byte[] screenshotBytes;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss-SSS");
        String time = timeFormat.format(new Date());

        String screenshotDirectory = reportDirectory + "/screenshot";

        File directory = new File(screenshotDirectory);
        directory.mkdirs();

        File destFile = new File(directory, time + "Lodash" + methodName + ".png");
        screenshotBytes = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, destFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Base64.getEncoder().encodeToString(screenshotBytes);

    }


    public static void closeAndSave() {
        extentReports.flush();
        File sourceFile = new File("report.html");
        File destinationDir = new File(reportDirectory + "/" + testRunDateTime + "_Report.html");
        try {
            FileUtils.copyFile(sourceFile, destinationDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
