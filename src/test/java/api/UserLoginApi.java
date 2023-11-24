package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import datasetup.dto.AuthenticationResponseDTO;
import datasetup.dto.UserDTOLombok;

public class UserLoginApi extends BaseApi {

    Response responseLogin = null;

    private Response loginRequest(UserDTOLombok user) {

        System.out.println("------------------------------- loginRequest method run");

        responseLogin = RestAssured.given()
//                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(baseUrl + "/v1/user/login/usernamepassword");
//                .thenReturn();

        return responseLogin;
    }

    public void setResponseLoginNull() {

        System.out.println("-------------------------------- response is null");

        responseLogin = null;
    }

    public int getStatusCodeResponseLogin(UserDTOLombok user) {

        if(responseLogin == null)
            responseLogin = loginRequest(user);

        return responseLogin.getStatusCode();
    }

    public String getTokenFromLoginResponse(UserDTOLombok user) {

        if(responseLogin == null)
            responseLogin = loginRequest(user);

        return responseLogin.getBody().as(AuthenticationResponseDTO.class).getToken();
    }
}
