package tests.restassured;

import datasetup.Data;
import org.testng.annotations.Test;

public class DeleteAllContactsTests extends BaseRestAssured {

    @Test
    public void deleteAllContacts_contactsExist() {

        contactsService.setResponseAddNewContactNull();
        contactsService.getIdResponseAddNewContact(new Data.RandomContactData().contact, token);
        contactsService.setResponseAddNewContactNull();

        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);

        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token), "All contacts was deleted!");

        softAssert.assertAll();
    }

    @Test
    public void deleteAllContacts_contactsDoNotExist() {

        contactsService.getStatusCodeResponseDeleteAllContacts(token);
        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteAllContacts(token), 200);

        System.out.println(contactsService.getMessageResponseDeleteAllContactsPositive(token));
        softAssert.assertEquals(contactsService.getMessageResponseDeleteAllContactsPositive(token), "All contacts was deleted!");

        softAssert.assertAll();
    }
}
