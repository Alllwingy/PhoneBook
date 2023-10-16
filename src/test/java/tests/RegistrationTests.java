package tests;

import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void beforeMethod() {

        apple.navigateToMainPage();
    }
    @AfterMethod
    public void afterMethod() {

        apple.getUserHelperToApply().logoutIfLogin();
    }

    @Test
    public void positive_UserDTOLombok() {

        UserDTOLombok user = UserDTOLombok.builder()
                .email(random.generateEmail(7))
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().registration(user);
        Assert.assertTrue(apple.getUserHelperToApply().validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void negative_UserDTOLombok_WrongEmail() {

        UserDTOLombok user = UserDTOLombok.builder()
                .email("abqduv.co.il")
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().registration(user);
        Assert.assertEquals(apple.getUserHelperToApply().alert(10),
                apple.getUserHelperToApply().alertTextRegistration_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_EmptyEmail() {

        UserDTOLombok user = UserDTOLombok.builder()
                .email("")
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().registration(user);
        Assert.assertEquals(apple.getUserHelperToApply().alert(10),
                apple.getUserHelperToApply().alertTextRegistration_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_WrongPassword() {

        UserDTOLombok user = UserDTOLombok.builder()
                .email(random.generateEmail(7))
                .password("Test")
                .build();

        apple.getUserHelperToApply().registration(user);
        Assert.assertEquals(apple.getUserHelperToApply().alert(10),
                apple.getUserHelperToApply().alertTextRegistration_WrongEmailToValidate);
    }
}
