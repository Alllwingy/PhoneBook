package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import datasetup.Data;
import datasetup.dto.AllContactsDTO;
import datasetup.dto.ContactDTO;
import datasetup.dto.MessageResponseDTO;

public class ContactsService extends BaseApi {

    String token = new UserApi().getToken("login", new Data.ConfigurationPropertiesData().lombok);

    public Response request(String method, ContactDTO contact) {

        switch (method) {

            case "add": return RestAssured.given(requestS).header("Authorization", token).body(contact).when().post("/contacts");
            case "put": return RestAssured.given(requestS).header("Authorization", token).body(contact).when().put("/contacts");
        }

        return null;
    }

    public Response request(String method) {

        switch (method) {

            case "all": return RestAssured.given(requestS).header("Authorization", token).when().delete("/contacts/clear");
            case "get": return RestAssured.given(requestS).header("Authorization", token).when().get("/contacts");
        }

        return null;
    }

    public Response request(String method, String id) {

        switch (method) {

            case "del": return RestAssured.given(requestS).header("Authorization", token).when().delete("/contacts/" + id);
        }

        return null;
    }

    public int getStatusCode(Response response) {

        return response.getStatusCode();
    }

    public String getMessage(Response response) {

        return response.body().as(MessageResponseDTO.class).getMessage();
    }

    public String getId(ContactDTO contact) {

        return request("add", contact).getBody().as(MessageResponseDTO.class).getMessage().split(": ")[1];
    }

    public boolean isIdPresent(Response response, String id) {

        for (ContactDTO contact : response.getBody().as(AllContactsDTO.class).getContacts())
            if (contact.getId().equals(id))
                return true;

        return false;
    }
}