package tests;

import datasetup.Data;
import datasetup.dto.ContactDTO;
import datasetup.dto.UserContact;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestAddContact extends BaseTests {

    boolean flagRefresh = false;

    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass() {

        if (apple.isPageUrlHome())
            apple.getUserHelper().openLoginPage();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        flagAfterMethod();

        if (flagRefresh)
            apple.refresh();
    }

    @Test(dataProvider = "dataC", dataProviderClass = Data.ContactDataCSV.class)
    public void addContactPositive(UserContact client) {

        apple.getUserHelper().login(client.getUser());

        for (ContactDTO i : client.getContacts()) {
            apple.getContactHelper().addNewContact(i);

            Assert.assertTrue(apple.getContactHelper().validateContactCreated(i.getPhone()));
        }

        for (ContactDTO i : client.getContacts()) {
            apple.getContactHelper().openContactInfoByPhone(i.getPhone());
            apple.getContactHelper().removeActiveContact();

            Assert.assertTrue(apple.getContactHelper().validateContactDeleted(i.getPhone()));
        }

        isFlagLogin = true;
    }

    @Test(dataProvider = "dataC", dataProviderClass = Data.ContactDataCSV.class)
    public void addContactNegativePhone(UserContact client) {

        apple.getUserHelper().login(client.getUser());

        for (ContactDTO i : client.getContacts()) {
            apple.getContactHelper().addNewContact(i);

            softAssert.assertTrue(apple.getUserHelper().validateMessageAlertWrongPhone());
        }

        isFlagAlert = true;
        isFlagLogin = true;

        softAssert.assertAll();
    }

    @Test (dataProvider = "dataC", dataProviderClass = Data.ContactDataCSV.class)
    public void addContactNegativeEmail(UserContact client) {

        apple.getUserHelper().login(client.getUser());

        for (ContactDTO i : client.getContacts()) {
            apple.getContactHelper().addNewContact(i);

            softAssert.assertTrue(apple.getUserHelper().validateMessageAlertWrongEmail());
        }

        isFlagAlert = true;
        isFlagLogin = true;

        softAssert.assertAll();
    }
}
