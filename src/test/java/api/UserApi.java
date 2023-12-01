package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import datasetup.dto.UserDTOLombok;

public class UserApi extends BaseApi {

    private Response authenticate(String method, UserDTOLombok user) {

        switch (method) {

            case "login": return RestAssured.given(requestS).body(user).when().post("/user/login/usernamepassword");

            case "register": return RestAssured.given(requestS).body(user).when().post("/user/registration/usernamepassword");
        }

        return null;
    }

    public int getStatusCode(String method, UserDTOLombok user) {

        return authenticate(method, user).getStatusCode();
    }

    public String getToken(String method, UserDTOLombok user) {

        return authenticate(method, user).then().extract().path("token");
    }
}
