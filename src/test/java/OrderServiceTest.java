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
}
