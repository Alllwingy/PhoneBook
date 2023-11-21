package tests;

import datasetup.Data;
import datasetup.dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginTests extends BaseTests {

    Data.ConfigurationPropertiesData cpd;
    String repetedLoggerText;

    @BeforeClass (alwaysRun = true)
    public void beforeClass() {

        repetedLoggerText = " fill email input field with: %s and password input field with: %s and click button Login";

        if (apple.isPageUrlHome())
            apple.getUserHelper().openLoginPage();
    }

    @BeforeMethod
    public void beforeMethod() {

        cpd = new Data.ConfigurationPropertiesData();
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();
    }

    @Test (groups = { "smoke", "all" }, dataProvider = "dataU", dataProviderClass = Data.UserDataCSV.class)
    public void positive_UserDTO(UserDTOLombok userDP, Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getUsername(), userDP.getPassword()));

        apple.getUserHelper().login(userDP);

        isFlagLogin = true;

        Assert.assertTrue(apple.getUserHelper().validationOfContactsButtonOnNavigationBar());
    }

    @Test (groups = { "smoke", "all" })
    public void positive_UserDTOWith(Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, cpd.with.getEmail(), cpd.with.getPassword()));

        apple.getUserHelper().login(cpd.with);

        isFlagLogin = true;

        Assert.assertTrue(apple.getUserHelper().validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positive_UserDTOLombok(Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, cpd.lombok.getUsername(), cpd.lombok.getPassword()));

        apple.getUserHelper().login(cpd.lombok);

        isFlagLogin = true;

        Assert.assertTrue(apple.getUserHelper().validationOfContactsButtonOnNavigationBar());
    }

    @Test  (groups = { "smoke", "regression", "all" })
    public void negative_UserDTOLombok_WrongEmail(Method method) {

        cpd.lombok.setUsername("winnergmail.com");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, cpd.lombok.getUsername(), cpd.lombok.getPassword()));

        apple.getUserHelper().login(cpd.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextLogin());
    }

    @Test (dataProvider = "dataU", dataProviderClass = Data.UserDataCSV.class)
    public void negative_UserDTOLombok_EmptyEmail(UserDTOLombok userDP, Method method) {

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, userDP.getUsername(), userDP.getPassword()));

        apple.getUserHelper().login(userDP);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextLogin());
    }

    @Test
    public void negative_UserDTOLombok_WrongPassword(Method method) throws IOException {

        cpd.lombok.setPassword("Test");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, cpd.lombok.getUsername(), cpd.lombok.getPassword()));

        apple.getUserHelper().login(cpd.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextLogin());
    }

    @Test
    public void negative_UserDTOLombok_EmptyPassword(Method method) throws IOException {

        cpd.lombok.setPassword("");

        logger.info(String.format("in method: " + method.getName()
                + repetedLoggerText, cpd.lombok.getUsername(), cpd.lombok.getPassword()));

        apple.getUserHelper().login(cpd.lombok);

        isFlagAlert = true;

        Assert.assertTrue(apple.getUserHelper().validationOfAlertTextLogin());
    }
}