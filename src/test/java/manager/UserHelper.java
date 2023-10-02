package manager;

import dto.UserDTO;
import dto.UserDTOWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By loginButtonOnNavigationBar = By.xpath("//*[@href='/login']");
    By emailInputFieldWith = By.xpath("//input[@name='email']");
    By passwordInputFieldWith = By.xpath("//input[@name='password']");
    By loginButtonOnLoginForm = By.xpath("//button[@name='login']");
    public By contactsButtonOnNavigationBar = By.xpath("//a[@href='/contacts']");
    public String contactsButtonTextToValidate = "CONTACTS";
    public String email = "4hxbr1co25f2@gmail.com";
    public String password = "Task$12345";

    public void login(UserDTO user) {

        clickOn(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.email());
        fill(passwordInputFieldWith, user.password());
        clickOn(loginButtonOnLoginForm);
    }

    public void login(UserDTOWith user) {

        clickOn(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.email());
        fill(passwordInputFieldWith, user.password());
        clickOn(loginButtonOnLoginForm);
    }

    public boolean validatePresenceOfContactsButtonOnNavigationBar(By locator, String expectedResult) {

        return isResultsEquals(locator, expectedResult);
    }
}