package tests;

import manager.AppleManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.FakerGenerator;
import utils.RandomGenerator;

@Listeners(TestNGListener.class)
public class BaseTest {

    static AppleManager apple = new AppleManager();
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    RandomGenerator random = new RandomGenerator();
    FakerGenerator faker = new FakerGenerator();

    private long timeStart, timeStop;

    @BeforeSuite
    public void before() {

        apple.setUp();
    }

    @AfterSuite
    public void after() {

        apple.tearDown();
    }

//    @BeforeMethod
//    public void loggerBeforeMethod(Method method) {
//
//        timeStart = System.currentTimeMillis();
//        logger.info("Start method: " + method.getName()); }
//
//    @AfterMethod
//    public void loggerAfterMethod(Method method) {
//
//        timeStop = System.currentTimeMillis();
//        logger.info("Stop method: " + method.getName() + " method duration: " + (timeStop - timeStart)); }
}
