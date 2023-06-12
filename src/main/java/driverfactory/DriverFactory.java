package driverfactory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utils.GlobalVars.DEFAULT_IMPLICIT_TIMEOUTS;

public class DriverFactory {

    //Declare a webDriver
    private static WebDriver webDriver = null;

    // This method gets the WebDriver instance for the current thread, creating it if necessary
    public static WebDriver getWebDriver() {

        if (webDriver == null) {
            webDriver = createDriver();
        }
        return webDriver;

    }

    //This method get run configuration
    private static WebDriver createDriver() {
        WebDriver driver = null;

        // Check the browser type and instantiate the appropriate WebDriver
        switch (getBrowserType()) {
            case "chrome" -> {
                // Set the ChromeDriver location from the project directory
                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (isHeadless())
                    chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (isHeadless())
                    firefoxOptions.addArguments("--headless");


                driver = new FirefoxDriver();
            }
        }

        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUTS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // Return the created WebDriver instance
        return driver;
    }

    //This method get run browser type from configuration
    private static String getBrowserType() {
        // Initialize a variable to store the browser type
        String browserType = null;

        // Get the browser type from the system property
        String browserTypeRemoteValue = System.getProperty("browserType");

        // Try to load the browser type from the properties file or use the system property value
        try {
            if (browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(
                        System.getProperty("user.dir") + "/config.properties");
                properties.load(file);
                browserType = properties.getProperty("browserType").toLowerCase().trim();
            } else {
                browserType = browserTypeRemoteValue.toLowerCase().trim();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return browserType;
    }

    //This method get isHeadless
    private static boolean isHeadless() {
        boolean isHeadless = false;
        String isHeadlessRemoteValue = System.getProperty("isHeadless");
        try {
            if (isHeadlessRemoteValue == null || isHeadlessRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
                properties.load(file);
                isHeadless = Boolean.parseBoolean(properties.getProperty("isHeadless"));
            } else {
                isHeadless = Boolean.parseBoolean(isHeadlessRemoteValue.toLowerCase().trim());
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return isHeadless;
    }


    public static void cleanUpDriver() {

        //webDriver.close();
        webDriver.quit();


    }
}
