package tests.okhttp;

import com.google.gson.Gson;
import datasetup.Data;
import datasetup.dto.AuthenticationResponseDTO;
import datasetup.dto.ErrorDTO;
import datasetup.dto.ErrorDTO_Bug;
import datasetup.dto.UserDTOLombok;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTestOkHTTP {

    UserDTOLombok user;
    public static final MediaType JSON = MediaType.get("application/json");
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    String baseUrl = "https://contactapp-telran-backend.herokuapp.com";

    @BeforeMethod
    public void beforeMethod() {

        user = new Data.RandomUserData().lombok;
    }

    @Test
    public void registrationPositive() {

        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute();
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
    public void registrationNegative_400() {

        user.setUsername("documentgmail.com");

        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute();
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

            ErrorDTO_Bug errorDTO_Bug = gson.fromJson(responseJson, ErrorDTO_Bug.class);

            System.out.println("string error: " + errorDTO_Bug.getError());
            System.out.println("int status: " + errorDTO_Bug.getStatus());
            System.out.println(response.code());
            System.out.println(response.message());

            Assert.assertEquals(response.code(), 400, "response not 400");
        }
    }

    @Test
    public void registrationNegative_409() {

        user.setUsername("document@gmail.com");
        user.setPassword("Task$12345");

        RequestBody requestBody = RequestBody.create(gson.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = okHttpClient.newCall(request).execute();
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

            Assert.assertEquals(response.code(), 409, "response not 409");
        }
    }
}
