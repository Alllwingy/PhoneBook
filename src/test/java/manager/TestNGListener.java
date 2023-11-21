package manager;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DatesUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestNGListener implements ITestListener {

    Logger logger = LoggerFactory.getLogger(TestNGListener.class);
    Throwable throwable = new Throwable();


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);

        logger.info("Start: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);

        logger.info("Success: " + result.getName() + ", duration: " + (result.getEndMillis() - result.getStartMillis()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        logger.info("Failure: " + result.getName() + " start on exception in TestNGListener class");
        String fileName = "src/test/screenshots/screenshot_" + DatesUtils.getDateString() + ".png";
        logger.info("took screenshot with name: " + fileName);
//        logger.error(throwable.getMessage());
        logger.error(Arrays.toString(throwable.getStackTrace()));
//        logger.error(throwable.toString());
        takeScreenshot(AppleManager.driver, fileName);
    }

    public void takeScreenshot(WebDriver driver, String filePath) {

        try {

            Files.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(filePath));

        } catch (IOException e) {

            e.getStackTrace();
            logger.error("got an exception for adding screenshot to the folder: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);

        logger.info("Skipped: " + result.getName());
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);

    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);

        logger.info("onStart: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);

        logger.info("onFinish: passed: " + context.getPassedTests());
        logger.info("onFinish: failed: " + context.getFailedTests());
    }
}
