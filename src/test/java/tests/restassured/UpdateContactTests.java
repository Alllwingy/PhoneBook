package tests.restassured;

import datasetup.Data;
import datasetup.dto.ContactDTO;
import org.testng.annotations.Test;

public class UpdateContactTests extends BaseRestAssured {

    ContactDTO contact = new Data.RandomContactData().contact;
    @Test
    public void updateContactTest() {

        contactsService.setResponseAddNewContactNull();
        String id = contactsService.getIdResponseAddNewContact(contact, token);
        contactsService.setResponseAddNewContactNull();

        contact.setId(id);

        softAssert.assertEquals(contactsService.getStatusCodeResponseUpdateContact(contact, token), 200, "status code not 200 for update test with correct data");
        System.out.println(contactsService.getMessagePositiveResponseUpdateContact(contact, token));

        softAssert.assertEquals(contactsService.getMessagePositiveResponseUpdateContact(contact, token), "Contact was updated");
        contactsService.setResponseUpdateContactNull();

        softAssert.assertAll();
    }
}
