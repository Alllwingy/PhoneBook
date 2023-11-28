package tests;

import api.ContactsService;
import api.UserLoginApi;
import api.UserRegisterApi;
import datasetup.Data;
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
    UserLoginApi userLoginApi = new UserLoginApi();
    UserRegisterApi userRegisterApi = new UserRegisterApi();
    ContactsService contactsService = new ContactsService();
    Logger logger = LoggerFactory.getLogger(BaseTests.class);
    SoftAssert softAssert = new SoftAssert();
    static String token = "";
    boolean isFlagLogin = false, isFlagAlert = false;

    @BeforeSuite (alwaysRun = true)
    public void before() {

        token = userLoginApi.getTokenFromLoginResponse(new Data.ConfigurationPropertiesData().lombok);
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
