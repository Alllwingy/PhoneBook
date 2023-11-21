package tests.okhttp;

import com.google.gson.Gson;
import datasetup.Data;
import datasetup.dto.AuthenticationResponseDTO;
import datasetup.dto.ErrorDTO;
import datasetup.dto.UserDTOLombok;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHTTP {

    UserDTOLombok user;
    public static final MediaType JSON = MediaType.get("application/json"); // choose JSON format for request / response body
    Gson gson = new Gson(); // Gson helps deserialize JSON into Java DTO objects
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @BeforeMethod
    public void beforeMethod() {

        user = new Data.ConfigurationPropertiesData().lombok;
    }

    @Test
    public void loginPositive() {

        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON); // Deserialize DTO user into JSON format in request body
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute(); // okHttpClient makes Call
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response == null)
            Assert.fail("got null response");
        else if (response.isSuccessful()) {

            String responseJson;

            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            AuthenticationResponseDTO authenticationResponseDTO = gson.fromJson(responseJson, AuthenticationResponseDTO.class);

            System.out.println(authenticationResponseDTO.getToken());
            System.out.println(response.code());
            System.out.println(response.message());

            Assert.assertEquals(response.code(), 200, "response not 200");

        } else {

            System.out.println(response.code() + " error");
            Assert.fail("response not successful");
        }
    }

    @Test
    public void loginNegative() {

        user.setPassword("Task12345");

        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON); // Deserialize DTO user into JSON format in request body
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute(); // okHttpClient makes Call
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response == null)
            Assert.fail("got null response");
        else if (response.isSuccessful())
            Assert.fail("got response with status code: " + response.code());
        else {

            String responseJson;

            try {
                responseJson = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ErrorDTO errorDTO = gson.fromJson(responseJson, ErrorDTO.class);

            System.out.println("string error: " + errorDTO.getError());
            System.out.println("int status: " + errorDTO.getStatus());
            System.out.println(response.code());
            System.out.println(response.message());

            Assert.assertEquals(response.code(), 401, "response not 401");
        }
    }
}