import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import json.Courier;
import json.CourierGenerator;
import json.Credentials;
import org.junit.*;
import steps.CourierAssertions;
import steps.CourierClient;

public class CourierTest {
    private CourierClient client = new CourierClient();
    private CourierAssertions checks = new CourierAssertions();
    private CourierGenerator courierGenerator = new CourierGenerator();
    private Courier tempCourier;
    private Credentials tempCredentials;
    private int tempCourierId;

    @Before
    public void setUp() {
        if (tempCourierId == 0) {
            tempCourier = courierGenerator.getRandomCourier();
            client.createCourier(tempCourier);
            tempCredentials = Credentials.from(tempCourier);
        }
    }

    @After
    public void tearDown() {
        if (tempCourierId == 0) {
            client.delete(tempCourierId);
        }
    }

    @Test
    @DisplayName("Create courier with valid credentials successful")
    public void checkCreatedCourierSuccessful() {
        Courier courier = courierGenerator.getRandomCourier();

        ValidatableResponse createClientResponse = client.createCourier(courier);
        checks.createdSuccessful(createClientResponse);
    }

    @Test
    @DisplayName("Failed create duplicate courier")
    public void checkThatImpossibleDuplicateCourier() {
        ValidatableResponse logInClientResponse = client.createCourier(tempCourier);
        checks.createdFailedConflict(logInClientResponse);
    }

    @Test
    @DisplayName("Check that login is required field in creating")
    public void checkLoginIsRequiredFieldInCreating() {
        Courier courier = courierGenerator.getRandomCourier();
        courier.setLogin(null);

        ValidatableResponse createClientResponse = client.createCourier(courier);
        checks.createdFailedBadRequest(createClientResponse);
    }

    @Test
    @DisplayName("Check that password is required field in creating")
    public void checkPasswordIsRequiredFieldInCreating() {
        Courier courier = courierGenerator.getRandomCourier();
        courier.setPassword(null);

        ValidatableResponse createClientResponse = client.createCourier(courier);
        checks.createdFailedBadRequest(createClientResponse);
    }

    @Test
    @DisplayName("Check that name is required field in creating")
    public void checkNameIsRequiredFieldInCreating() {
        Courier courier = courierGenerator.getRandomCourier();
        courier.setFirstName(null);

        ValidatableResponse createClientResponse = client.createCourier(courier);
        checks.createdFailedBadRequest(createClientResponse);
    }

    @Test
    @DisplayName("Log In with valid credentials")
    public void checkLoggedInSuccessful() {
        ValidatableResponse logInClientResponse = client.logIn(tempCredentials);
        tempCourierId = checks.getCourierId(logInClientResponse);

        checks.loggedInSuccessful(logInClientResponse);
    }

    @Test
    @DisplayName("Impossible to log In with unknown credentials")
    public void checkLoggedInFailedUnknownCourier() {
        ValidatableResponse logInClientResponse = client.logIn(Credentials.random());
        checks.loggedInFailedNotFound(logInClientResponse);
    }

    @Test
    @DisplayName("Impossible to log In without password")
    public void checkLoggedInFailedWithoutPassword() {
        Credentials cr = tempCredentials;
        cr.setPassword(null);
        ValidatableResponse logInClientResponse = client.logIn(cr);

        checks.loggedInFailedBadRequest(logInClientResponse);
    }

    @Test
    @DisplayName("Impossible to log In without login")
    public void checkLoggedInFailedWithoutLogin() {
        Credentials cr = tempCredentials;
        cr.setLogin(null);
        ValidatableResponse logInClientResponse = client.logIn(cr);

        checks.loggedInFailedBadRequest(logInClientResponse);
    }

    @Test
    @DisplayName("Successful delete of courier")
    public void deleteCourierSuccessful() {
        Courier courier = courierGenerator.getRandomCourier();
        client.createCourier(courier);
        ValidatableResponse response = client.logIn(Credentials.from(courier));
        int id = checks.getCourierId(response);

        response = client.delete(id);
        checks.deleteSuccessful(response);
    }

    @Test
    @DisplayName("Failed delete of courier without id")
    public void deleteCourierWithoutId() {
        ValidatableResponse response = client.delete();
        checks.deleteFailedBadRequest(response);
    }

    @Test
    @DisplayName("Failed delete of courier with unknown id")
    public void deleteCourierUnknownID() {
        ValidatableResponse response = client.delete(0);
        checks.deleteFailedNotFound(response);
    }
}
