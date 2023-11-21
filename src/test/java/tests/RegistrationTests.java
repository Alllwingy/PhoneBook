package tests;

import datasetup.Data;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class RegistrationTests extends BaseTests {

    Data.RandomUserData rud;
    String repetedLoggerText;

    @BeforeClass (alwaysRun = true)
    public void beforeClass() {

        repetedLoggerText = " fill email input field with: %s and password input field with: %s and click button Login";

        if (apple.isPageUrlHome())
            apple.getUserHelper().openLoginPage();
    }

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod() {

        rud = new Data.RandomUserData();
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();
    }

    @Test  (groups = { "smoke", "all" })
    public void positive_UserDTOLombok_WFaker(Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, rud.lombok.getUsername(), rud.lombok.getPassword()));

        apple.getUserHelper().registration(rud.lombok);

        isFlagLogin = true;

        Assert.assertTrue(apple.getUserHelper().validationOfContactsButtonOnNavigationBar());
    }

    @Test  (groups = { "regression", "all" })
    public void negative_UserDTOLombok_WrongEmail(Method method) {

        rud.lombok.setUsername("abqduv.co.il");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, rud.lombok.getUsername(), rud.lombok.getPassword()));

        apple.getUserHelper().registration(rud.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextRegistraton());
    }

    @Test
    public void negative_UserDTOLombok_EmptyEmail(Method method) {

        rud.lombok.setUsername("");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, rud.lombok.getUsername(), rud.lombok.getPassword()));

        apple.getUserHelper().registration(rud.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextRegistraton());
    }

    @Test
    public void negative_UserDTOLombok_WrongPassword(Method method) {

        rud.lombok.setPassword("Test");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, rud.lombok.getUsername(), rud.lombok.getPassword()));

        apple.getUserHelper().registration(rud.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextRegistraton());
    }

    @Test
    public void negative_UserDTOLombok_EmptyPassword(Method method) {

        rud.lombok.setPassword("");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, rud.lombok.getUsername(), rud.lombok.getPassword()));

        apple.getUserHelper().registration(rud.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextRegistraton());
    }
}
