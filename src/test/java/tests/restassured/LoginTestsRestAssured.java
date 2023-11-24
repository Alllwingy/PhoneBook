package tests.restassured;

import datasetup.Data;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestsRestAssured extends BaseRestAssured {

    @BeforeMethod
    public void beforeMethod() {

        user = new Data.ConfigurationPropertiesData().lombok;
    }

    @Test
    public void loginStatusCodeTest() {

        Assert.assertEquals(userLoginApi.getStatusCodeResponseLogin(user), 200, "status code not 200 for login test with correct data");
    }

    @Test
    public void loginStatusCodeTest2() {

        Assert.assertEquals(userLoginApi.getStatusCodeResponseLogin(user), 200, "status code not 200 for login test with correct data");
    }

    @Test
    public void testToken() {

        System.out.println("token: " + userLoginApi.getTokenFromLoginResponse(user));
    }

    @Test
    public void negativeLogin() {

        userLoginApi.setResponseLoginNull();
        user.setPassword("Task12345");

        int statusCode = userLoginApi.getStatusCodeResponseLogin(user);
        userLoginApi.setResponseLoginNull();

        Assert.assertEquals(statusCode, 401);
    }
}
