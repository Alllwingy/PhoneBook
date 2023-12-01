package tests.restassured.contacts;

import com.jayway.restassured.response.Response;
import datasetup.Data;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGetAllContacts extends BaseContacts {

    @BeforeMethod
    public void beforeMethod() {

        contact = new Data.RandomContactData().contact;
    }

    @Test
    public void positive() {

        String id = serve.getId(contact);
        Response response = serve.request("get");

        soft.assertEquals(serve.getStatusCode(response), 200);
        soft.assertTrue(serve.isIdPresent(response, id));
        soft.assertAll();
    }
}