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
    By buttonLogout = By.tagName("button");
    String contactsButtonTextToValidate = "CONTACTS";
    public String alertTextLogin_WrongEmailToValidate = "Wrong email or password";
    public String alertTextRegistration_WrongEmailToValidate = "Wrong email or password format\n" +
            "            Email must contains one @ and minimum 2 symbols after last dot\n" +
            "            Password must contain at least one uppercase letter!\n" +
            "            Password must contain at least one lowercase letter!\n" +
            "            Password must contain at least one digit!\n" +
            "            Password must contain at least one special symbol from [‘$’,’~’,’-‘,’_’]!";
    public String password = "Task$12345";

    public void login(UserDTO user) {

        click_Mouse(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.email());
        fill(passwordInputFieldWith, user.password());
        click_Action(loginButtonOnLoginForm,2,2);
    }

    public void login(UserDTOWith user) {

        click_Mouse(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.email());
        fill(passwordInputFieldWith, user.password());
        click_Action(loginButtonOnLoginForm);
    }

    public void login(UserDTOLombok user) {

        click_Mouse(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.getEmail());
        fill(passwordInputFieldWith, user.getPassword());
        click_Action(loginButtonOnLoginForm);
    }

    public void registration(UserDTOLombok user) {

        click_Mouse(loginButtonOnNavigationBar);
        fill(emailInputFieldWith, user.getEmail());
        fill(passwordInputFieldWith, user.getPassword());
        click_Mouse(registrationButtonOnLoginForm);
    }

    public boolean validationOfContactsButtonOnNavigationBar() {

        return isResultsEquals(contactsButtonOnNavigationBar, contactsButtonTextToValidate);
    }

    public void logoutIfLogin() {

        if (isElementPresent(buttonLogout))
            click_Mouse(buttonLogout);
    }
}