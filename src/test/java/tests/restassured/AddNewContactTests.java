package tests.restassured;

import datasetup.Data;
import datasetup.dto.ContactDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContactTests extends BaseRestAssured {

    ContactDTO contact = new Data.RandomContactData().contact;

    @Test
    public void statusCodePositiveAddNewContact() {

        Assert.assertEquals(contactsService.getStatusCodeResponseAddNewContact(contact, token), 200);
    }

    @Test
    public void messagePositiveAddContact() {

        Assert.assertTrue(contactsService.getMessagePositiveResponseAddNewContact(contact, token).contains("Contact was added"));
    }
}
