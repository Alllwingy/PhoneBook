package tests.restassured.contacts;

import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

public class TestDeleteAllContacts extends BaseContacts {

    @Test
    public void contactsExist() {

        Response response = serve.request("all");

        soft.assertEquals(serve.getStatusCode(response), 200);
        soft.assertEquals(serve.getMessage(response), "All contacts was deleted!");
        soft.assertAll();
    }

    @Test
    public void contactsDoNotExist() {

        Response response = serve.request("all");

        soft.assertEquals(serve.getStatusCode(response), 200);
        soft.assertEquals(serve.getMessage(response), "All contacts was deleted!");
        soft.assertAll();
    }
}
