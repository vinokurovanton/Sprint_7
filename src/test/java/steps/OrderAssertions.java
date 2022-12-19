package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {
    @Step
    public void createdSuccessful(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
    }

    @Step
    public void acceptSuccessful(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("ok", is(true));
    }

    @Step
    public void requestFailedBadRequest(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    @Step
    public void requestFailedNotFound(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", notNullValue());
    }

    @Step
    public void listGets(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }

    @Step
    public int getNo(ValidatableResponse response) {
        return response
                .body("track", CoreMatchers.notNullValue())
                .extract()
                .path("track");
    }

    @Step
    public int getId(ValidatableResponse response) {
        return response
                .body("order.id", CoreMatchers.notNullValue())
                .extract()
                .path("order.id");
    }
}
