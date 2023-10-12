package manager;

import dto.UserDTO;
import dto.UserDTOLombok;
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
    By registrationButtonOnLoginForm = By.xpath("//button[@name='registration']");
//    String registrationButtonOnLoginForm = "document.querySelector('[name=\"registration\"]').click();\n";
    By contactsButtonOnNavigationBar = By.xpath("//a[@href='/contacts']");
    String contactsButtonTextToValidate = "CONTACTS";
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

    public void login(UserDTOLombok user) {

        clickOn(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.getEmail());
        fill(passwordInputFieldWith, user.getPassword());
        clickOn(loginButtonOnLoginForm);
    }

    public boolean validationOfPresenceOfContactsButtonOnNavigationBar() {

        return isResultsEquals(contactsButtonOnNavigationBar, contactsButtonTextToValidate);
    }

    public void fillRegistrationForm(UserDTOLombok user) {

        clickOn(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.getEmail());
        fill(passwordInputFieldWith, user.getPassword());
        clickOn(registrationButtonOnLoginForm);
    }
}