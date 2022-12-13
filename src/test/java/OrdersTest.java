import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import json.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.CourierAssertions;
import steps.CourierClient;
import steps.OrderAssertions;
import steps.OrderClient;

public class OrdersTest {
    private static OrderClient orderClient = new OrderClient();
    private static CourierClient courierClient = new CourierClient();
    private static OrderAssertions orderCheck = new OrderAssertions();
    private static CourierAssertions courierCheck = new CourierAssertions();
    private static CourierGenerator courierGenerator = new CourierGenerator();
    private static int tempCourierId;
    private static int tempOrderId;
    private static Courier tempCourier;
    private static Order order;

    @BeforeClass
    public static void setUp() {
        tempCourier = courierGenerator.getRandomCourier();
        courierClient.create(tempCourier);
        ValidatableResponse response = courierClient.logIn(Credentials.from(tempCourier));
        tempCourierId = courierCheck.getCourierId(response);

        order = OrderGenerator.create();
        int no = orderCheck.getNo(orderClient.create(order));
        response = orderClient.getOrder(no);
        tempOrderId = orderCheck.getId(response);
    }

    @AfterClass
    public static void tearDown() {
        if (tempCourierId == 0) {
            courierClient.delete(tempCourierId);
        }
    }

    @Test
    @DisplayName("Get list of orders successful")
    public void checkGetListOfOrders() {
        ValidatableResponse response = orderClient.getList();
        orderCheck.listGets(response);
    }

    @Test
    @DisplayName("Accept order successful")
    public void acceptOrderSuccessful() {
        ValidatableResponse response = orderClient.acceptOrder(tempOrderId, tempCourierId);
        orderCheck.acceptSuccessful(response);
    }

    @Test
    @DisplayName("Accept order without courier id failed")
    public void acceptOrderWithoutCourierId() {
        ValidatableResponse response = orderClient.acceptOrder(tempOrderId);
        orderCheck.requestFailedBadRequest(response);
    }

    @Test
    @DisplayName("Accept order with bad courier id failed")
    public void acceptOrderWithBadCourierId() {
        ValidatableResponse response = orderClient.acceptOrder(tempOrderId, 0);
        orderCheck.requestFailedNotFound(response);
    }

    @Test
    @DisplayName("Accept order without order number failed")
    public void acceptOrderWithoutOrderNumber() {
        ValidatableResponse response = orderClient.acceptOrder();
        orderCheck.requestFailedBadRequest(response);
    }

    @Test
    @DisplayName("Accept order with bad order number failed")
    public void acceptOrderWithBadOrderNumber() {
        ValidatableResponse response = orderClient.acceptOrder(0, tempCourierId);
        orderCheck.requestFailedNotFound(response);
    }

    @Test
    @DisplayName("Get order id by track number successful")
    public void getOrderIdByNoSuccessful() {
        order = OrderGenerator.create();
        int no = orderCheck.getNo(orderClient.create(order));
        ValidatableResponse response = orderClient.getOrder(no);
        orderCheck.getId(response);
    }

    @Test
    @DisplayName("Get order id by unknown number failed")
    public void getOrderIdByUnknownNumberFailed() {
        ValidatableResponse response = orderClient.getOrder(0);
        orderCheck.requestFailedNotFound(response);
    }

    @Test
    @DisplayName("Get order id by unknown number failed")
    public void getOrderIdByEmptyNumberFailed() {
        ValidatableResponse response = orderClient.getOrder();
        orderCheck.requestFailedBadRequest(response);
    }
}
