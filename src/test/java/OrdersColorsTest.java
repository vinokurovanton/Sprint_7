import io.restassured.response.ValidatableResponse;
import json.Order;
import json.OrderGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderAssertions;
import steps.OrderClient;

import java.util.List;

@RunWith(Parameterized.class)
public class OrdersColorsTest {
    private OrderClient client = new OrderClient();
    private OrderAssertions check = new OrderAssertions();

    private List<String> color;

    public OrdersColorsTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Order with color: {0}")
    public static Object[][] getSumData() {
        return new Object[][]{
                {List.of("BLACK")}, {List.of("BLACK", "GRAY")}, {List.of("GREY")}, {List.of("")}
        };
    }

    @Test
    public void checkOrdersWithDifferentColors() {
        Order order = OrderGenerator.createColored(color);
        ValidatableResponse response = client.create(order);

        check.createdSuccessful(response);
    }
}
