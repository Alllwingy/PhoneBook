package tests.restassured;

import api.ContactsService;
import api.UserLoginApi;
import api.UserRegisterApi;
import datasetup.Data;
import datasetup.dto.ContactDTO;
import datasetup.dto.UserDTOLombok;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseRestAssured {

    UserDTOLombok user;
    ContactDTO contact;
    UserLoginApi userLoginApi = new UserLoginApi();
    UserRegisterApi userRegisterApi = new UserRegisterApi();
    ContactsService contactsService = new ContactsService();
    static String token = "";
    SoftAssert softAssert = new SoftAssert();

    @BeforeSuite
    public void preconditionApiRestAssured() {

        token = userLoginApi.getTokenFromLoginResponse(new Data.ConfigurationPropertiesData().lombok);
    }
}