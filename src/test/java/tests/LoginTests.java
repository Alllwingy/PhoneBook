package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void beforeMethod() {

        apple.navigateToMainPage();
        apple.getUserHelperToApply().pause(1);
    }

    @AfterMethod
    public void afterMethod() {

        apple.getUserHelperToApply().pause(3);
        apple.getUserHelperToApply().logoutIfLogin();
    }

    @Test
    public void positive_UserDTO() throws IOException {

        UserDTO userDTO = new UserDTO(
                random.readRandomEmailFromFile(),
                apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(userDTO);
        apple.getUserHelperToApply().pause(10);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positive_UserDTOWith() throws IOException {

        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail(random.readRandomEmailFromFile())
                .withPassword(apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(userDTOWith);
        apple.getUserHelperToApply().pause(3);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positive_UserDTOLombok() throws IOException {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email(random.readRandomEmailFromFile())
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        apple.getUserHelperToApply().pause(3);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void negative_UserDTOLombok_WrongEmail() throws IOException {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("winnergmail.com")
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        apple.getUserHelperToApply().pause(3);
        Assert.assertEquals(apple.getUserHelperToApply().alert(10),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_EmptyEmail() throws IOException {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("")
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        apple.getUserHelperToApply().pause(3);
        Assert.assertEquals(apple.getUserHelperToApply().alert(10),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_WrongPassword() throws IOException {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email(random.readRandomEmailFromFile())
                .password("Test")
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        apple.getUserHelperToApply().pause(3);
        Assert.assertEquals(apple.getUserHelperToApply().alert(10),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }
}