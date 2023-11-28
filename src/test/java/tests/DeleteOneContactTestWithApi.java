package tests;

import datasetup.Data;
import datasetup.dto.ContactDTO;
import org.testng.annotations.*;

public class DeleteOneContactTestWithApi extends BaseTests {

    ContactDTO contact;
    String repetedLoggerText;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

//        repetedLoggerText = " fill email input field with: %s and password input field with: %s and click button Login";

        if (apple.isPageUrlHome())
            apple.getUserHelper().openLoginPage();

        apple.getUserHelper().login(new Data.ConfigurationPropertiesData().lombok);
    }

    @BeforeMethod
    public void beforeMethod() {

        contact = new Data.RandomContactData().contact;
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();
    }

    @AfterClass(alwaysRun = true)
    public void postConditions() {

        apple.getUserHelper().logout();
    }

    @Test
    public void deleteCreatedContactPositive() {

        softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(contact, token), 200);
//        apple.getContactHelper().addNewContact(contact);

        apple.getContactHelper().openContactInfoByPhone(contact.getPhone());
        apple.getContactHelper().removeActiveContact();

        softAssert.assertTrue(apple.getContactHelper().validateContactDeleted(contact.getPhone()));
        softAssert.assertAll();
    }
}
