package tests.restassured;

import datasetup.Data;
import org.testng.annotations.Test;

public class DeleteOneContactTests extends BaseRestAssured {

    @Test
    public void deleteContactTest() {

        contactsService.setResponseAddNewContactNull();
        String id = contactsService.getIdResponseAddNewContact(new Data.RandomContactData().contact, token);
        contactsService.setResponseAddNewContactNull();

        softAssert.assertEquals(contactsService.getStatusCodeResponseDeleteOneContact(token, id), 200);
        System.out.println(contactsService.getMessageDeleteOneContact(token, id));

        softAssert.assertEquals(contactsService.getMessageDeleteOneContact(token, id), "Contact was deleted!");
        contactsService.setResponseDeleteOneContactNull();

        softAssert.assertAll();
    }
}
