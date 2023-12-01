package tests.restassured.authentication;

import api.UserApi;
import datasetup.Data;
import datasetup.dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRegistration {

    UserDTOLombok user;
    UserApi authenticate = new UserApi();

    @BeforeMethod
    public void beforeMethod() {

        user = new Data.RandomUserData().lombok;
    }

    @Test
    public void statusCode() {

        Assert.assertEquals(authenticate.getStatusCode("register", user), 200, "status code not 200 for register test with correct data");
    }

    @Test
    public void token() {

        System.out.println(authenticate.getToken("register", user));
    }

    @Test
    public void wrongEmail() {

        user.setUsername("komeamail.com");
        Assert.assertEquals(authenticate.getStatusCode("register", user), 400);
    }

    @Test
    public void conflict() {

        user = new Data.ConfigurationPropertiesData().lombok;
        Assert.assertEquals(authenticate.getStatusCode("register", user), 409);
    }
}
