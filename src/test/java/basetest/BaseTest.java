package basetest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Report;

import java.util.Arrays;
import java.util.stream.Collectors;

import static driverfactory.DriverFactory.cleanUpDriver;
import static driverfactory.DriverFactory.getWebDriver;
import static utils.DataGenerate.getRunDatetime;

public class BaseTest {
    private String roundId;
    private ExtentTest testFeatures;

    @BeforeSuite
    public void setUpSuite() {
        Logger logger = org.slf4j.LoggerFactory.getLogger(BaseTest.class);

        Report.initReport();

    }

    @BeforeClass
    public void setUpClass() {
        getWebDriver();
        testFeatures = Report.addTestFeaturesReport(getClass().getName());
    }

    @BeforeMethod
    public void setUpMethod(ITestResult method) {
        setRoundId(getRunDatetime());
    }

    @AfterMethod
    public void captureExceptionImage(ITestResult result) {
        String methodName;
        Throwable throwable = result.getThrowable();
        Object[] testData = result.getParameters();
        Status status;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS -> {
                status = Status.PASS;
            }
            case ITestResult.FAILURE -> {
                status = Status.FAIL;
            }
            case ITestResult.SKIP -> {
                status = Status.SKIP;
            }
            default -> throw new IllegalStateException("Unexpected value: " + result.getStatus());
        }
        if (testData != null && testData.length > 0) {
            methodName = result.getName() + Arrays.stream(testData).map(Object::toString).collect(Collectors.toList());
        } else {
            methodName = result.getName();
        }
        Report.addMethodReport(testFeatures, status, methodName, throwable);

    }

    @AfterClass
    public void tearDownClass() {
        cleanUpDriver();

    }

    @AfterSuite
    public void tearDownSuite() {
        Report.closeAndSave();
    }

    protected String getRoundId() {
        return roundId;
    }

    private synchronized void setRoundId(String roundId) {
        this.roundId = roundId;
    }
}
