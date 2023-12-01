package tests.restassured.contacts;

import api.ContactsService;
import datasetup.dto.ContactDTO;
import org.testng.asserts.SoftAssert;

public class BaseContacts {

    ContactDTO contact;
    ContactsService serve = new ContactsService();
    SoftAssert soft = new SoftAssert();
}