package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends BaseTest {

    int seconds = 10;

    @BeforeMethod
    public void beforeMethod() {

        apple.navigateToMainPage();
    }

    @AfterMethod
    public void afterMethod() {

        apple.getUserHelperToApply().logoutIfLogin();
    }

    @Test
    public void positive_UserDTO() throws IOException {

        UserDTO userDTO = new UserDTO(
                random.readRandomEmailFromFile(),
                apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(userDTO);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positive_UserDTOWith() throws IOException {

        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail(random.readRandomEmailFromFile())
                .withPassword(apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(userDTOWith);
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
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfContactsButtonOnNavigationBar());
    }

    @Test
    public void negative_UserDTOLombok_WrongEmail() {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("winnergmail.com")
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        Assert.assertEquals(apple.getUserHelperToApply().alert(seconds),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_EmptyEmail() {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email("")
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        Assert.assertEquals(apple.getUserHelperToApply().alert(seconds),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_WrongPassword() throws IOException {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email(random.readRandomEmailFromFile())
                .password("Test")
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        Assert.assertEquals(apple.getUserHelperToApply().alert(seconds),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }

    @Test
    public void negative_UserDTOLombok_EmptyPassword() throws IOException {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email(random.readRandomEmailFromFile())
                .password("")
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        Assert.assertEquals(apple.getUserHelperToApply().alert(seconds),
                apple.getUserHelperToApply().alertTextLogin_WrongEmailToValidate);
    }
}