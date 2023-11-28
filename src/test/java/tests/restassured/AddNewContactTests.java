package tests.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContactTests extends BaseRestAssured {

    @Test
    public void statusCodePositiveAddNewContact() {

        Assert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(contact, token), 200);
    }

    @Test
    public void messagePositiveAddContact() {

        Assert.assertTrue(contactsService.getMessagePositiveResponseAddNewContact(contact, token).contains("Contact was added"));
    }
}
