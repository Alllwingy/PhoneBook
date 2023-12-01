package tests.restassured.contacts;

import com.jayway.restassured.response.Response;
import datasetup.Data;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAddNewContact extends BaseContacts {

    @BeforeMethod
    public void beforeMethod() {

        contact = new Data.RandomContactData().contact;
    }

    @Test
    public void positive() {

        Response response = serve.request("add", contact);

        soft.assertEquals(serve.getStatusCode(response), 200);
        soft.assertTrue(serve.getMessage(response).contains("Contact was added!"));
        soft.assertAll();
    }


    @Test
    public void wrongPhone() {

        contact.setPhone("jfkfku");
        Assert.assertEquals(serve.getStatusCode(serve.request("add", contact)), 400);
    }
}
