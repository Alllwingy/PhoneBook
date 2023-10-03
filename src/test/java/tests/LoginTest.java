package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void positiveLoginTestWithUserDTO() {

        UserDTO user = new UserDTO(
                apple.getUserHelperToApply().email,
                apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(user);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfPresenceOfContactsButtonOnNavigationBar());
    }

    @Test
    public void positiveLoginTestWithUserDTOWith() {

        UserDTOWith user = new UserDTOWith()
                .withEmail(apple.getUserHelperToApply().email)
                .withPassword(apple.getUserHelperToApply().password);

        apple.getUserHelperToApply().login(user);
        Assert.assertTrue(apple.getUserHelperToApply()
                .validationOfPresenceOfContactsButtonOnNavigationBar());
    }
}