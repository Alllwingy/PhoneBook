package api;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;

public class BaseApi {

    RequestSpecification requestS = new RequestSpecBuilder()
            .setBaseUri("https://contactapp-telran-backend.herokuapp.com")
            .setBasePath("/v1")
            .setContentType(ContentType.JSON)
            .build();
}
