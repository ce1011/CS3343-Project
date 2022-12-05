import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderControllerTest {

    @Test
    public void deleteOrderTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Order order = new Order(null, null, 100, "");
        OrderController orderController = new OrderController();
        orderController.deleteOrder("100001");
        assertEquals("", outContent.toString());
    }

    @Test
    public void updateOrderStatusTest1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Order order = new Order(null, null, 100, "");
        OrderController orderController = new OrderController();
        orderController.updateOrderStatus("100001", "Pending");
        assertEquals("", outContent.toString());
    }

    @Test
    public void displayCustomerOrderListTest() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("+\ntest\n12.9\ndescription\n10\n2.4\nb\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Product tempData = new Product("test addProduct()", 12.9, "description", 10, new ProductState_Discontinued(), 2.4);
        ArrayList<CartItem> list = new ArrayList<CartItem>();
        CartItem item = new CartItem(tempData, 1);
        list.add(item);
        Order order = new Order(list, null, 100, null);
        User user = new User("123", "123", Customer.getInstance());
        order.setUser(user);
        Store.getInstance().setCurrentUser(user);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        OrderController orderController = new OrderController();
        orderController.displayCustomerOrderList(user, new OrderSortByCreateDate_ASC());
        assertEquals("", outContent.toString());
    }

    @Test
    public void displayAdminOrderListTest() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("+\ntest\n12.9\ndescription\n10\n2.4\nb\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Product tempData = new Product("test addProduct()", 12.9, "description", 10, new ProductState_Discontinued(), 2.4);
        ArrayList<CartItem> list = new ArrayList<CartItem>();
        CartItem item = new CartItem(tempData, 1);
        list.add(item);
        Order order = new Order(list, null, 100, null);
        User user = new User("123", "123", Customer.getInstance());
        order.setUser(user);
        Store.getInstance().setCurrentUser(user);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        OrderController orderController = new OrderController();
        orderController.displayAdminOrderList(new OrderSortByCreateDate_ASC());
        assertEquals("", outContent.toString());
    }

    @Test
    public void customerOrderViewTest() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("6\n".getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Product tempData = new Product("test addProduct()", 12.9, "description", 10, new ProductState_Discontinued(), 2.4);
        ArrayList<CartItem> list = new ArrayList<CartItem>();
        CartItem item = new CartItem(tempData, 11);
        list.add(item);
        Order order = new Order(list, null, 100, null);
        User user = new User("123", "123", Customer.getInstance());
        order.setUser(user);
        Store.getInstance().setCurrentUser(user);
        OrderService orderService = OrderService.getOrderServiceInstance();
        orderService.placeOrder(order);
        OrderController orderController = new OrderController();
        orderController.customerOrderView(user);
        assertEquals("", outContent.toString());
    }

}
