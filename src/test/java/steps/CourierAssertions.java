package steps;

import io.restassured.response.ValidatableResponse;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class CourierAssertions {
    public void createdSuccessful(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }

    public void loggedInSuccessful(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("id", greaterThan(0));
    }

    public int getCourierId(ValidatableResponse response){
        return response
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    public void createdFailedConflict(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_CONFLICT)
                .body("message", notNullValue());
    }

    public void createdFailedBadRequest(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    public void deleteSuccessful(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }

    public void deleteFailedBadRequest(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    public void deleteFailedNotFound(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", notNullValue());
    }

    public void loggedInFailedBadRequest(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    public void loggedInFailedNotFound(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", notNullValue());
    }
}
