package tests.restassured;

import datasetup.Data;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTestsRestAssured extends BaseRestAssured {

    @BeforeMethod
    public void beforeMethod() {

        user = new Data.RandomUserData().lombok;
    }

    @Test
    public void registerStatusCodeTest() {

        Assert.assertEquals(userRegisterApi.getStatusCodeResponseRegister(user), 200, "status code not 200 for register test with correct data");
    }

    @Test
    public void testToken() {

        System.out.println(userRegisterApi.getTokenFromRegisterResponse(user));
    }

    @Test
    public void negativeRegister_400() {

        userRegisterApi.setResponseRegisterNull();
        user.setUsername("komeamail.com");

        int statusCode = userRegisterApi.getStatusCodeResponseRegister(user);
        userRegisterApi.setResponseRegisterNull();

        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void negativeRegister_409() {

        userRegisterApi.setResponseRegisterNull();
        user = new Data.ConfigurationPropertiesData().lombok;

        int statusCode = userRegisterApi.getStatusCodeResponseRegister(user);
        userRegisterApi.setResponseRegisterNull();

        Assert.assertEquals(statusCode, 409);
    }
}
