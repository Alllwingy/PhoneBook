package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void positiveLoginTestWithUserDTO() {

        UserDTO userDTO = new UserDTO(
                apple.getUserHelperToApply().email,
                apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(userDTO);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfPresenceOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positiveLoginTestWithUserDTOWith() {

        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail(apple.getUserHelperToApply().email)
                .withPassword(apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(userDTOWith);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfPresenceOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positiveLoginTestWithUserDTOLombok() {

        UserDTOLombok userDTOLombok = UserDTOLombok.builder()
                .email(apple.getUserHelperToApply().email)
                .password(apple.getUserHelperToApply().password)
                .build();

        apple.getUserHelperToApply().login(userDTOLombok);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfPresenceOfContactsButtonOnNavigationBar());
    }
}