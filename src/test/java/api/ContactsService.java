package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import datasetup.dto.ContactDTO;
import datasetup.dto.MessageResponseDTO;

public class ContactsService extends BaseApi {

    Response responseAddNewContact = null;
    Response responseDeleteOneContact = null;
    Response responseDeleteAllContacts = null;
    Response responseUpdateContact = null;

    //-------------------------------------------------------------------------------------- responseAddNewContact

    private Response getResponseAddNewContact(ContactDTO contactDTO, String token) {

        responseAddNewContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(contactDTO)
                .when()
                .post(baseUrl + "/v1/contacts");

        return responseAddNewContact;
    }

    public void setResponseAddNewContactNull() {

        responseAddNewContact = null;
    }

    public int getStatusCodeResponseAddNewContact(ContactDTO contactDTO, String token) {

        if (responseAddNewContact == null)
            responseAddNewContact = getResponseAddNewContact(contactDTO, token);

        return responseAddNewContact.getStatusCode();
    }

    public String getMessagePositiveResponseAddNewContact(ContactDTO contactDTO, String token) {

        if (responseAddNewContact == null)
            responseAddNewContact = getResponseAddNewContact(contactDTO, token);

        return responseAddNewContact.body().as(MessageResponseDTO.class).getMessage();
    }

    public String getIdResponseAddNewContact(ContactDTO contactDTO, String token) {

        return getMessagePositiveResponseAddNewContact(contactDTO, token).split(":")[1].trim();
    }

    //-----------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------- responseDeleteOneContact

    private Response getResponseDeleteOneContact(String token, String id) {

        responseDeleteOneContact = RestAssured.given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/" + id);

        return responseDeleteOneContact;
    }

    public void setResponseDeleteOneContactNull() {

        responseDeleteOneContact = null;
    }

    public int getStatusCodeResponseDeleteOneContact(String token, String id) {

        if (responseDeleteOneContact == null)
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);

        return responseDeleteOneContact.getStatusCode();
    }

    public String getMessageDeleteOneContact(String token, String id) {

        if (responseDeleteOneContact == null)
            responseDeleteOneContact = getResponseDeleteOneContact(token, id);

        return responseDeleteOneContact.getBody().as(MessageResponseDTO.class).getMessage();
    }

    //-----------------------------------------------------------------------------------------------------------------

    //------------------------------ responseDeleteAllContacts

    private Response getResponseDeleteAllContacts(String token) {

        responseDeleteAllContacts = RestAssured.given()
                .header("Authorization", token)
                .when()
                .delete(baseUrl + "/v1/contacts/clear");

        return responseDeleteAllContacts;
    }

    public void setResponseDeleteAllContactsNull() {

        responseDeleteAllContacts = null;
    }

    public int getStatusCodeResponseDeleteAllContacts(String token) {

        if (responseDeleteAllContacts == null)
            responseDeleteAllContacts = getResponseDeleteAllContacts(token);

        return responseDeleteAllContacts.getStatusCode();
    }

    public String getMessageResponseDeleteAllContactsPositive(String token) {

        if (responseDeleteAllContacts == null)
            responseDeleteAllContacts = getResponseDeleteAllContacts(token);

        return responseDeleteAllContacts.getBody().as(MessageResponseDTO.class).getMessage();
    }

    //--------------------------------------------------------------------------

    //---------------------------- responseUpdateContact

    private Response getResponseUpdateContact(ContactDTO contactDTO, String token) {

        responseUpdateContact = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(contactDTO)
                .when()
                .put(baseUrl + "/v1/contacts");

        return responseUpdateContact;
    }

    public void setResponseUpdateContactNull() {

        responseUpdateContact = null;
    }

    public int getStatusCodeResponseUpdateContact(ContactDTO contactDTO, String token) {

        if (responseUpdateContact == null)
            responseUpdateContact = getResponseUpdateContact(contactDTO, token);

        return responseUpdateContact.getStatusCode();
    }

    public String getMessagePositiveResponseUpdateContact(ContactDTO contactDTO, String token) {

        if (responseUpdateContact == null)
            responseUpdateContact = getResponseUpdateContact(contactDTO, token);

        return responseUpdateContact.body().as(MessageResponseDTO.class).getMessage();
    }
}