package tests.restassured.contacts;

import com.jayway.restassured.response.Response;
import datasetup.Data;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestUpdateContact extends BaseContacts {

    @BeforeMethod
    public void beforeMethod() {

        contact = new Data.RandomContactData().contact;
    }

    @Test
    public void positive() {

        contact.setId(serve.getId(new Data.RandomContactData().contact));
        Response response = serve.request("put", contact);

        soft.assertEquals(serve.getStatusCode(response), 200, "status code not 200 for update test with correct data");
        soft.assertEquals(serve.getMessage(response), "Contact was updated");
        soft.assertAll();
    }
}
