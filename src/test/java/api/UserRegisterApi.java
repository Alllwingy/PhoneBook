package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import datasetup.dto.AuthenticationResponseDTO;
import datasetup.dto.UserDTOLombok;

public class UserRegisterApi extends BaseApi {

    Response responseRegister = null;

    private Response registerRequest(UserDTOLombok user) {

        return responseRegister = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(baseUrl + "/v1/user/registration/usernamepassword");
    }

    public void setResponseRegisterNull() {

        responseRegister = null;
    }

    public int getStatusCodeResponseRegister(UserDTOLombok user) {

        if (responseRegister == null)
            responseRegister = registerRequest(user);

        System.out.println(user.getUsername());

        return responseRegister.getStatusCode();
    }

    public String getTokenFromRegisterResponse(UserDTOLombok user) {

        if (responseRegister == null)
            responseRegister = registerRequest(user);

        return responseRegister.getBody().as(AuthenticationResponseDTO.class).getToken();
    }
}
