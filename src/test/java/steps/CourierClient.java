package steps;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import json.Courier;
import json.Credentials;

import static data.TestData.*;
import static io.restassured.RestAssured.given;

public class CourierClient {
    public ValidatableResponse create(Courier courier) {
        return given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(COURIER_URI)
                .then();
    }

    public ValidatableResponse logIn(Credentials credentials) {
        return given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .and()
                .body(credentials)
                .when()
                .post(LOGIN_COURIER_URI)
                .then();
    }

    public ValidatableResponse delete(int id) {
        return given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .and()
                .body("{\"id\":\"" + id + "\"}")
                .delete(COURIER_URI + "/" + id)
                .then();
    }

    public ValidatableResponse delete() {
        return given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .and()
                .body("{\"id\":\"\"}")
                .delete(COURIER_URI + "/")
                .then();
    }
}
