package tests;

import manager.AppleManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners(TestNGListener.class)
public class BaseTests {

    static AppleManager apple = new AppleManager();
    SoftAssert softAssert = new SoftAssert();
    Logger logger = LoggerFactory.getLogger(BaseTests.class);
    boolean isFlagLogin = false, isFlagAlert = false;

    @BeforeSuite (alwaysRun = true)
    public void before() {

        apple.setUp();
    }

    @AfterSuite (alwaysRun = true)
    public void after() {

        apple.tearDown();
    }

    @BeforeMethod (alwaysRun = true)
    public void loggerBeforeMethod(Method method) {

        logger.info("__________________________________________________________________________");
        logger.info("started method: " + method.getName() + " with parameters: " + Arrays.toString(method.getParameters()));
    }

    @AfterMethod (alwaysRun = true)
    public void loggerAfterMethod(Method method) {

        logger.info("stopped method: " + method.getName() + ", with parameters: " + Arrays.toString(method.getParameters()));
    }

    public void flagAfterMethod() {

        if (isFlagAlert) {
            isFlagAlert = false;
            apple.getUserHelper().alertAccept(true);
        }
        if (isFlagLogin) {
            isFlagLogin = false;
            apple.getUserHelper().logout();
        }
    }
}
