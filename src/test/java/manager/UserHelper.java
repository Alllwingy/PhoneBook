package manager;

import datasetup.dto.UserDTO;
import datasetup.dto.UserDTOLombok;
import datasetup.dto.UserDTOWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHelper extends BaseHelper {

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
    public String alertTextRegistration_WrongEmailToValidate = "Wrong email or password format";
    String alertTextAddContact_WrongPhoneToValidate = "Phone not valid: Phone number must contain only digits! And length min 10, max 15!";
    String alertTextAddContact_WrongEmailToValidate = "Email not valid";


    public void openLoginPage() {

        click_Mouse(loginButtonOnNavigationBar);
    }

    public void login(UserDTO user) {

        fill(emailInputFieldWith, user.getEmail());
        fill(passwordInputFieldWith, user.getPassword());
        click_Action(loginButtonOnLoginForm);
    }

    public void login(UserDTOWith user) {

        fill(emailInputFieldWith, user.getEmail());
        fill(passwordInputFieldWith, user.getPassword());
        click_Action(loginButtonOnLoginForm);
    }

    public void login(UserDTOLombok user) {

        fill(emailInputFieldWith, user.getUsername());
        fill(passwordInputFieldWith, user.getPassword());
        click_Action(loginButtonOnLoginForm);
    }

    public void registration(UserDTOLombok user) {

        fill(emailInputFieldWith, user.getUsername());
        fill(passwordInputFieldWith, user.getPassword());
        click_Mouse(registrationButtonOnLoginForm);
    }

    public boolean validationOfContactsButtonOnNavigationBar() {

        return isResultsEquals(contactsButtonOnNavigationBar, contactsButtonTextToValidate);
    }

    public boolean validationOfAlertTextLogin() {

        return isTextContains(alertGetText(), alertTextLogin_WrongEmailToValidate);
    }

    public boolean validationOfAlertTextRegistraton() {

        return isTextContains(alertGetText(), alertTextRegistration_WrongEmailToValidate);
    }

    public boolean validateMessageAlertWrongPhone() {

        return isTextContains(alertGetText(), alertTextAddContact_WrongPhoneToValidate);
    }

    public boolean validateMessageAlertWrongEmail() {

        return isTextContains(alertGetText(), alertTextAddContact_WrongEmailToValidate);
    }

    public void logout() {

        new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(buttonLogout)).click();
    }
}