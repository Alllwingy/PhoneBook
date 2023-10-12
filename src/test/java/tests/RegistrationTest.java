package tests;

import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @Test
    public void positiveRegistrationTestWithUserDTOLombok() {

        String generateEmail = random.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email(generateEmail)
                .password("Task$12345")
                .build();

        apple.getUserHelperToApply().fillRegistrationForm(user);
        Assert.assertTrue(apple.getUserHelperToApply().validationOfPresenceOfContactsButtonOnNavigationBar());
    }
}
