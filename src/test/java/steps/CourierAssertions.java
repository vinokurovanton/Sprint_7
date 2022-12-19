package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class CourierAssertions {
    @Step
    public void createdSuccessful(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }

    @Step
    public void loggedInSuccessful(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("id", greaterThan(0));
    }

    @Step
    public int getCourierId(ValidatableResponse response){
        return response
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    @Step
    public void createdFailedConflict(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_CONFLICT)
                .body("message", notNullValue());
    }

    @Step
    public void createdFailedBadRequest(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    @Step
    public void deleteSuccessful(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }

    @Step
    public void deleteFailedBadRequest(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    @Step
    public void deleteFailedNotFound(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", notNullValue());
    }

    @Step
    public void loggedInFailedBadRequest(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", notNullValue());
    }

    @Step
    public void loggedInFailedNotFound(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", notNullValue());
    }
}
