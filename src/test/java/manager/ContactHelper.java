package manager;

import datasetup.dto.ContactDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    By btnAddNewContact = By.xpath("//a[@href='/add']");
    By inputNameAddContact = By.xpath("//input[@placeholder='Name']");
    By inputLastNameAddContact = By.xpath("//input[@placeholder='Last Name']");
    By inputPhoneAddContact = By.xpath("//input[@placeholder='Phone']");
    By inputEmailAddContact = By.xpath("//input[@placeholder='email']");
    By inputAddressAddContact = By.xpath("//input[@placeholder='Address']");
    By inputDescriptionAddContact = By.xpath("//input[@placeholder='description']");
    By btnSaveNewContact = By.xpath("//button/b");
    By textH3ContactList = By.xpath("//H3");
    By btnRemoveContact = By.xpath("//button[2]");
    String searchPhone = "//H3[text()='%s']";

    public void openContactInfoByPhone(String phone) {

        click_Mouse(By.xpath(String.format(searchPhone, phone)));
    }

    public void addNewContact(ContactDTO contact) {

        click_Mouse(btnAddNewContact);
        fill(inputNameAddContact, contact.getName());
        fill(inputLastNameAddContact, contact.getLastName());
        fill(inputPhoneAddContact, contact.getPhone());
        fill(inputEmailAddContact, contact.getEmail());
        fill(inputAddressAddContact, contact.getAddress());
        fill(inputDescriptionAddContact, contact.getDescription());
        click_Mouse(btnSaveNewContact);
    }

    public boolean validateContactCreated(String phone) {

        return isElementByTextExistsInTheList(textH3ContactList, phone);
    }

    public boolean validateContactDeleted(String phone) {

        return new WebDriverWait(driver, 2).until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(String.format(searchPhone, phone)), phone));
    }

    public void removeActiveContact() {

        click_Mouse(btnRemoveContact);
    }
}