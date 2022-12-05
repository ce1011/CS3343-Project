import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

public class OrderServiceTest {
	
    @Test
    public void testPlaceOrder(){
    	Order order = new Order(null, null, 0.0, null);
    	OrderService.getOrderServiceInstance().placeOrder(order);
        assertNotNull(DeliveryService.getInstance());
    }

    @Test
    public void testSearchOrderByTransactionID() {
    	Order order = new Order(null, null, 0.0, null);
    	//OrderService.getOrderServiceInstance().placeOrder(order);
    	assertNull(OrderService.getOrderServiceInstance().searchOrderByTransactionID("100000"));
    }

    @Test
    public void searchOrderTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Order order = new Order(null, null, 0.0, null);
        User user = new User("123", "123", Customer.getInstance());
        order.setUser(user);
        Store.getInstance().setCurrentUser(user);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        orderService.searchOrder(Store.getInstance().getCurrentUser(), new OrderSortByCreateDate_ASC());
        assertEquals("", outContent.toString());

    }

    @Test
    public void searchOrderTest2(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Order order = new Order(null, null, 0.0, null);
        User user = new User("123", "123", Customer.getInstance());
        order.setUser(user);
        Store.getInstance().setCurrentUser(user);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        orderService.searchOrder(new OrderSortByCreateDate_ASC());
        assertEquals("", outContent.toString());

    }

    @Test
    public void searchOrderByTransactionIDTest(){
        Order order = new Order(null, null, 0.0, null);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        order.setTransactionID("100006");
        Order result = orderService.searchOrderByTransactionID("100006");
        assertEquals(result.toString(), order.toString());
    }

    @Test
    public void deleteOrderTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Order order = new Order(null, null, 10, null);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        order.setTransactionID("200001");
        orderService.deleteOrder("200001");
        assertEquals("", outContent.toString());
    }
    @Test
    public void updateOrderStatusTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Order order = new Order(null, null, 10, null);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        order.setTransactionID("300001");
        orderService.updateOrderStatus("300001", new OrderState_Processing());
        assertEquals("", outContent.toString());
    }

    @Test
    public void getCentralOrderListTest(){
        OrderService orderService = OrderService.getOrderServiceInstance();
        Map<String, DoubleSummaryStatistics> result = orderService.getCentralOrderList();
        assertEquals(result.toString(), orderService.getCentralOrderList().toString());
    }

}
