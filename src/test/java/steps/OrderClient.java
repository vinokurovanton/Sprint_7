package steps;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import json.Order;

import static data.TestData.*;
import static io.restassured.RestAssured.given;

public class OrderClient {
    public ValidatableResponse create(Order order) {
        return given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .and()
                .body(order)
                .when()
                .post(ORDERS_URI)
                .then();
    }

    public ValidatableResponse getList() {
        return given().log().all()
                .baseUri(BASE_URI)
                .when()
                .get(ORDERS_URI)
                .then();
    }

    public ValidatableResponse acceptOrder(Integer orderId, Integer courierId) {
        return given().log().all()
                .baseUri(BASE_URI)
                .when()
                .queryParam(COURIER_ID_PARAM, courierId)
                .put(ORDERS_URI + ACCEPT_URI + orderId)
                .then();
    }

    public ValidatableResponse acceptOrder(Integer orderId) {
        return given().log().all()
                .baseUri(BASE_URI)
                .when()
                .put(ORDERS_URI + ACCEPT_URI + orderId)
                .then();
    }

    public ValidatableResponse acceptOrder() {
        return given().log().all()
                .baseUri(BASE_URI)
                .when()
                .put(ORDERS_URI + ACCEPT_URI)
                .then();
    }

    public ValidatableResponse getOrder(int no) {
        return given().log().all()
                .baseUri(BASE_URI)
                .when()
                .queryParam(TRACK_NO_PARAM, no)
                .get(TRACK_URI)
                .then();
    }

    public ValidatableResponse getOrder() {
        return given().log().all()
                .baseUri(BASE_URI)
                .when()
                .get(TRACK_URI)
                .then();
    }
}
