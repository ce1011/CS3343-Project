import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryServiceTest {
	
    @Test
    public void testGetDeliveryServiceInstance(){
        assertNotNull(DeliveryService.getInstance());
    }
    
    @Test
    public void testUpdateDeliveryPrice() {
    	DeliveryService.getInstance().updateDeliveryPrice(20, 15);
    	assertEquals(20,20);
    }
 
}
