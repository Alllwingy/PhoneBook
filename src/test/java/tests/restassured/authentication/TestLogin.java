package tests.restassured.authentication;

import api.UserApi;
import datasetup.Data;
import datasetup.dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {

    UserDTOLombok user;
    UserApi authenticate = new UserApi();

    @BeforeMethod
    public void beforeMethod() {

        user = new Data.ConfigurationPropertiesData().lombok;
    }

    @Test
    public void statusCode() {

        Assert.assertEquals(authenticate.getStatusCode("login", user), 200, "status code not 200 for login test with correct data");
    }

    @Test
    public void token() {

        System.out.println("token: " + authenticate.getToken("login", user));
    }

    @Test
    public void wrongPassword() {

        user.setPassword("Task12345");
        Assert.assertEquals(authenticate.getStatusCode("login", user), 401);
    }
}
