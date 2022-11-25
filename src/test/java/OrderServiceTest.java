import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
    public void testDeleteOrder() {
    	Order order = new Order(null, null, 0.0, null);
    	assertTrue(OrderService.getOrderServiceInstance().deleteOrder(order));
    }
    
    @Test
    public void testDeleteOrder1() {
    	Order order = new Order(null, null, 0.0, "abc");
    	OrderService.getOrderServiceInstance().placeOrder(order);
    	assertTrue(OrderService.getOrderServiceInstance().deleteOrder(order));
    }
    
    @Test 
    public void testUpdateOrderStatus() {
        	Order order = new Order(null, null, 0.0, null);
        	OrderService.getOrderServiceInstance().placeOrder(order);
        	OrderService.getOrderServiceInstance().updateOrderStatus(order, new OrderState_Delivered());
        	assertEquals("Delivered", order.getStatus().toString());
    }

    @Test
    public void testUpdateOrderStatus1() {
            	Order order = new Order(null, null, 0.0, null);
            	OrderService.getOrderServiceInstance().placeOrder(order);
            	OrderService.getOrderServiceInstance().updateOrderStatus(order, new OrderState_Delivered());
            	assertEquals("Delivered", order.getStatus().toString());
    }

    @Test
    public void searchOrderByTransactionID(){
        Order order = new Order(null, null, 0.0, "abc");
        OrderService.getOrderServiceInstance().placeOrder(order);
        assertNull(OrderService.getOrderServiceInstance().searchOrderByTransactionID("abc"));
    }
}
