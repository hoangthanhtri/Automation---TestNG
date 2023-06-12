package basetest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static driverfactory.DriverFactory.cleanUpDriver;
import static driverfactory.DriverFactory.getWebDriver;

public class BaseTest {
    private int roundId;

    @BeforeClass
    public void setUpClass() {
        getWebDriver();
    }

    @BeforeMethod
    public void setUpMethod() {

        roundId = setRoundId();
    }

    @AfterMethod
    public void captureExceptionImage(ITestResult result) {
        if (result.getStatus() != ITestResult.SUCCESS) {
            File screenshot;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());

            String reportDirectory = new File(System.getProperty("user.dir")) + "/target/surefire-reports/" + date;
            File directory = new File(reportDirectory);
            directory.mkdirs();

            File destFile = new File(directory, result.getName() + "_" + RandomStringUtils.randomNumeric(3) + ".png");
            try {
                screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, destFile);
                System.out.println("Screenshot captured: " + destFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

    }

    @AfterClass
    public void tearDown() {
        cleanUpDriver();
    }

    private int setRoundId() {
        int id = 0;
        try {
            String keyUpdate = "roundId";
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir") + "/config.properties");
            properties.load(file);
            file.close();
            id = Integer.parseInt(properties.getProperty(keyUpdate));
            for (String key : properties.stringPropertyNames()
            ) {
                if (key.equalsIgnoreCase(keyUpdate)) {
                    properties.setProperty(key, String.valueOf(id + 1));
                    break;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(
                    System.getProperty("user.dir") + "/config.properties");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        return id;
    }

    protected int getRoundId() {
        return roundId;
    }
}
