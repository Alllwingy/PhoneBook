package tests.restassured.contacts;

import com.jayway.restassured.response.Response;
import datasetup.Data;
import org.testng.annotations.Test;

public class TestDeleteContactById extends BaseContacts {

    @Test
    public void positive() {

        Response response = serve.request("del", serve.getId(new Data.RandomContactData().contact));

        soft.assertEquals(serve.getStatusCode(response), 200);
        soft.assertEquals(serve.getMessage(response), "Contact was deleted!");
        soft.assertAll();
    }
}
