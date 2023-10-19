package manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    Logger logger = LoggerFactory.getLogger(TestNGListener.class);
    long timeStart, timeStop;

    @Override
    public void onTestStart(ITestResult result) {

        timeStart = System.currentTimeMillis();

        logger.info("Start: " + result.getName());
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        timeStop = System.currentTimeMillis();

        logger.info("Success: " + result.getName() + " duration: " + (timeStop - timeStart));
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        timeStop = System.currentTimeMillis();

        logger.info("Failed: " + result.getName() + " duration: " + (timeStop - timeStart));
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        timeStop = System.currentTimeMillis();

        logger.info("Skipped: " + result.getName() + " duration: " + (timeStop - timeStart));
        ITestListener.super.onTestSkipped(result);
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

        logger.info("onStart: " + context.getName());
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {

        logger.info("onFinish: passed: " + context.getPassedTests());
        logger.info("onFinish: failed: " + context.getFailedTests());

        ITestListener.super.onFinish(context);
    }
}
