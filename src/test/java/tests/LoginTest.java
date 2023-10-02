package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void positiveLoginTestWithUserDTO() {

        UserDTO user = new UserDTO(apple.getUserHelper().email, apple.getUserHelper().password);

        apple.getUserHelper().login(user);
        Assert.assertTrue(apple.getUserHelper()
                .validatePresenceOfContactsButtonOnNavigationBar(
                        apple.getUserHelper().contactsButtonOnNavigationBar,
                        apple.getUserHelper().contactsButtonTextToValidate));
    }

    @Test
    public void positiveLoginTestWithUserDTOWith() {

        UserDTOWith user = new UserDTOWith()
                .setEmail(apple.getUserHelper().email)
                .setPassword(apple.getUserHelper().password);

        apple.getUserHelper().login(user);
        Assert.assertTrue(apple.getUserHelper()
                .validatePresenceOfContactsButtonOnNavigationBar(
                        apple.getUserHelper().contactsButtonOnNavigationBar,
                        apple.getUserHelper().contactsButtonTextToValidate));
    }
}
