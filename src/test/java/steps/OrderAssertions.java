package steps;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {
    public void createdSuccessful(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
    }

    public void acceptSuccessful(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("ok", is(true));
    }

    public void requestFailedBadRequest(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    public void requestFailedNotFound(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", notNullValue());
    }

    public void listGets(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }

    public int getNo(ValidatableResponse response) {
        return response
                .body("track", CoreMatchers.notNullValue())
                .extract()
                .path("track");
    }

    public int getId(ValidatableResponse response) {
        return response
                .body("order.id", CoreMatchers.notNullValue())
                .extract()
                .path("order.id");
    }
}
