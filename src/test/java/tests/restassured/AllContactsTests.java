package tests.restassured;

import datasetup.Data;
import datasetup.dto.ContactDTO;
import org.testng.annotations.Test;

public class AllContactsTests extends BaseRestAssured {

    ContactDTO contact = new Data.RandomContactData().contact;
    @Test
    public void allContactsTest() throws InterruptedException {

        softAssert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(contact, token), 200);

        String id = contactsService.getIdResponseAddNewContact(contact, token);

        System.out.println("id: " + id);

        softAssert.assertEquals(contactsService.getStatusCodeResponseGetAllContacts(token), 200);
        softAssert.assertTrue(contactsService.isIdInTheAllContactResponse(token, id));
        softAssert.assertAll();
    }
}
