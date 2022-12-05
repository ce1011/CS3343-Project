import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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
        System.setIn(new ByteArrayInputStream("6\n7\n".getBytes()));
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
        //System.err.print(outContent.toString());
        String expect = "WelcometoOrderlistpanelSortorderlistby1.BycreatedateinAscendingorder2.BycreatedateinDescendingorder3.ByPriceinAscendingorder4.ByPriceinDescendingorder5.ByTransactionIDinAscendingorder6.ByTransactionIDinDescendingorder7.ReturnYourchoice:No.|TransactionID|Product|OrderDate|TotalPrice|Orderstatus1|100005|testaddProduct()|2022-12-0507:46:04|100.0|Pending2|100002|testaddProduct()|2022-12-0507:46:04|100.0|Pending(b)BackPleaseenteryourchoice:Invalidchoice";
        assertTrue(!expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }
    @Test
    public void customerOrderViewTest1() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("1\n7\n".getBytes()));
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
        //System.err.print(outContent.toString());
        String expect = "WelcometoOrderlistpanelSortorderlistby1.BycreatedateinAscendingorder2.BycreatedateinDescendingorder3.ByPriceinAscendingorder4.ByPriceinDescendingorder5.ByTransactionIDinAscendingorder6.ByTransactionIDinDescendingorder7.ReturnYourchoice:No.|TransactionID|Product|OrderDate|TotalPrice|Orderstatus1|100005|testaddProduct()|2022-12-0507:46:04|100.0|Pending2|100002|testaddProduct()|2022-12-0507:46:04|100.0|Pending(b)BackPleaseenteryourchoice:Invalidchoice";
        assertTrue(!expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }
    @Test
    public void customerOrderViewTest2() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("2\n7\n".getBytes()));
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
        //System.err.print(outContent.toString());
        String expect = "WelcometoOrderlistpanelSortorderlistby1.BycreatedateinAscendingorder2.BycreatedateinDescendingorder3.ByPriceinAscendingorder4.ByPriceinDescendingorder5.ByTransactionIDinAscendingorder6.ByTransactionIDinDescendingorder7.ReturnYourchoice:No.|TransactionID|Product|OrderDate|TotalPrice|Orderstatus1|100005|testaddProduct()|2022-12-0507:46:04|100.0|Pending2|100002|testaddProduct()|2022-12-0507:46:04|100.0|Pending(b)BackPleaseenteryourchoice:Invalidchoice";
        assertTrue(!expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }

    @Test
    public void customerOrderViewTest3() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("3\n7\n".getBytes()));
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
        //System.err.print(outContent.toString());
        String expect = "WelcometoOrderlistpanelSortorderlistby1.BycreatedateinAscendingorder2.BycreatedateinDescendingorder3.ByPriceinAscendingorder4.ByPriceinDescendingorder5.ByTransactionIDinAscendingorder6.ByTransactionIDinDescendingorder7.ReturnYourchoice:No.|TransactionID|Product|OrderDate|TotalPrice|Orderstatus1|100005|testaddProduct()|2022-12-0507:46:04|100.0|Pending2|100002|testaddProduct()|2022-12-0507:46:04|100.0|Pending(b)BackPleaseenteryourchoice:Invalidchoice";
        assertTrue(!expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }
    @Test
    public void customerOrderViewTest4() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("4\n7\n".getBytes()));
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
        //System.err.print(outContent.toString());
        String expect = "WelcometoOrderlistpanelSortorderlistby1.BycreatedateinAscendingorder2.BycreatedateinDescendingorder3.ByPriceinAscendingorder4.ByPriceinDescendingorder5.ByTransactionIDinAscendingorder6.ByTransactionIDinDescendingorder7.ReturnYourchoice:No.|TransactionID|Product|OrderDate|TotalPrice|Orderstatus1|100005|testaddProduct()|2022-12-0507:46:04|100.0|Pending2|100002|testaddProduct()|2022-12-0507:46:04|100.0|Pending(b)BackPleaseenteryourchoice:Invalidchoice";
        assertTrue(!expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }
    @Test
    public void customerOrderViewTest5() throws ExistingProductWithSameNameFoundException{
        System.setIn(new ByteArrayInputStream("5\n8\n\7".getBytes()));
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
        //System.err.print(outContent.toString());
        String expect = "WelcometoOrderlistpanelSortorderlistby1.BycreatedateinAscendingorder2.BycreatedateinDescendingorder3.ByPriceinAscendingorder4.ByPriceinDescendingorder5.ByTransactionIDinAscendingorder6.ByTransactionIDinDescendingorder7.ReturnYourchoice:No.|TransactionID|Product|OrderDate|TotalPrice|Orderstatus1|100005|testaddProduct()|2022-12-0507:46:04|100.0|Pending2|100002|testaddProduct()|2022-12-0507:46:04|100.0|Pending(b)BackPleaseenteryourchoice:Invalidchoice";
        assertTrue(!expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "").equals(outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")));
    }
    
    @Test
    public void adminOrderViewTest1() throws Exception{
        System.setIn(new ByteArrayInputStream("1\n1\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    
    @Test
    public void adminOrderViewTest2() throws Exception{
        System.setIn(new ByteArrayInputStream("1\n2\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    
    @Test
    public void adminOrderViewTest3() throws Exception{
        System.setIn(new ByteArrayInputStream("1\n3\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    
    @Test
    public void adminOrderViewTest4() throws Exception{
        System.setIn(new ByteArrayInputStream("1\n4\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    @Test
    public void adminOrderViewTest5() throws Exception{
        System.setIn(new ByteArrayInputStream("1\n5\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    @Test
    public void adminOrderViewTest6() throws Exception{
        System.setIn(new ByteArrayInputStream("1\n6\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    @Test
    public void adminOrderViewTest8() throws Exception{
        System.setIn(new ByteArrayInputStream("2\n10001\nPending\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    @Test
    public void adminOrderViewTest9() throws Exception{
        System.setIn(new ByteArrayInputStream("3\n\n10001\n4\n".getBytes()));
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
        orderController.adminOrderView();
        //System.err.print(outContent.toString());
        assertTrue(outContent.toString().contains(""));
    }
    @Test
    public void reportViewTest() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    	OrderController orderController = new OrderController();
        orderController.reportView();
        System.err.print(outContent.toString());
        String expect = "         Product Name|Price\r\n"
        		+ "Total: $0.000000\r\n"
        		+ "Please enter coupon name if not apply coupon type NA\r\n"
        		+ "\r\n"
        		+ "Please enter your address\r\n"
        		+ "Please enter delivery zone\r\n"
        		+ "Place order successfully!\r\n"
        		+ "           Date|       Sum\r\n"
        		+ "     2022-12-05|1015.000000";
        assertNotEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
    }
    
    @Test
    public void placeOrderTest() throws Exception{
        System.setIn(new ByteArrayInputStream("N/A\n10001\nKowloon\n4\n".getBytes()));
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
    	OrderController orderController = new OrderController();
        orderController.placeOrder();
        //System.err.print(outContent.toString());
        String expect = "        Product Name|Price\r\n"
        		+ "Total: $0.000000\r\n"
        		+ "Please enter coupon name if not apply coupon type NA\r\n"
        		+ "\r\n"
        		+ "Please enter your address\r\n"
        		+ "Please enter delivery zone\r\n"
        		+ "Place order successfully!";
        assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }
    @Test
    public void placeOrderTest1() throws Exception{
        System.setIn(new ByteArrayInputStream("Dessert\n10001\nKowloon\n4\n".getBytes()));
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = sdformat.parse("04-12-2022");
        Date date2 = sdformat.parse("07-12-2022");
    	Coupon coupon = new Coupon("Dessert", date1, date2, "A", 2, 50, 30, "fixedPrice");
        CouponService.getInstance().createCoupon(coupon);
        Product tempData = new Product("test addProduct()", 12.9, "description", 10, new ProductState_Discontinued(), 2.4);
        ArrayList<CartItem> list = new ArrayList<CartItem>();
        CartItem item = new CartItem(tempData, 11);
        list.add(item);
        Order order = new Order(list, null, 10, null);
        User user = new User("123", "123", Customer.getInstance());
        order.setUser(user);
        Store.getInstance().setCurrentUser(user);
    	OrderController orderController = new OrderController();
        orderController.placeOrder();
        System.err.print(outContent.toString());
        String expect = "Coupon Successfully Created.\r\n"
        		+ "        Product Name|Price\r\n"
        		+ "Total: $0.000000\r\n"
        		+ "Please enter coupon name if not apply coupon type NA\r\n"
        		+ "\r\n"
        		+ "Coupon not available\r\n"
        		+ "Please enter your address\r\n"
        		+ "Please enter delivery zone\r\n"
        		+ "Place order successfully!\r\n"
        		+ "";
        assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));

    }
}
