import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DeliveryServiceTest {
	
    @Test
    public void testGetDeliveryServiceInstance(){
        assertNotNull(DeliveryService.getInstance());
    }
    
    @Test
    public void testUpdateDeliveryPrice() {
    	DeliveryService.getInstance().updateDeliveryPrice(20, 15);
    	assertEquals(20,DeliveryService.getInstance().getDeliveryFirstKG_price());
    	assertEquals(15,DeliveryService.getInstance().getDeliveryAfterFirstKG_price());
    }
    
    @Test
    public void testRemoveDeliveryZone1() throws DeliveryZoneNotFoundException {
    	DeliveryService.getInstance().removeDeliveryZone("Kowloon");
    	assertEquals(new ArrayList<String>(List.of("Hong Kong", "New Territories","Islands District")),DeliveryService.getInstance().getDeliveryZoneList());
    }
    
    @Test
    public void testRemoveDeliveryZone2(){
    	Exception e = assertThrows(DeliveryZoneNotFoundException.class,() -> {
    		DeliveryService.getInstance().removeDeliveryZone("Cheung Chau");
    	   });
    	assertEquals("Zone: Cheung Chau is not found.",e.getMessage());
    }
    
    @Test
    public void testCreateDelivery1() throws DeliveryZoneNotFoundException, DeliveryItemNotFoundException {
    	Delivery d = DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	assertEquals(DeliveryService.getInstance().getDelivery("10001"),d);
    }
    
    @Test
    public void testCreateDelivery2(){
    	Exception e = assertThrows(DeliveryZoneNotFoundException.class,() -> {
    		DeliveryService.getInstance().createDelivery("9999", "Tuen Mun", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	   });
    	assertEquals("Zone: Tuen Mun is not found.",e.getMessage());
    }
    
    @Test
    public void testGetDelivery(){
    	Exception e = assertThrows(DeliveryItemNotFoundException.class,() -> {
    		DeliveryService.getInstance().getDelivery("12345");
    	   });
    	assertEquals("Delivery Item: 12345 is not found.",e.getMessage());
    }

    
    @Test
    public void testUpdateDelivery2() throws DeliveryZoneNotFoundException{
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	Exception e = assertThrows(DeliveryZoneNotFoundException.class,() -> {
        	DeliveryService.getInstance().updateDelivery("10001", "9999", "Greater Bay Area", "Cheung's Ancestral Hall, Tai Om Road", 6.9, "Processing");
    	   });
    	assertEquals("Zone: Greater Bay Area is not found.",e.getMessage());
    }
    
    @Test
    public void testUpdateDelivery3() throws DeliveryZoneNotFoundException{
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	Exception e = assertThrows(DeliveryStateNotFoundException.class,() -> {
        	DeliveryService.getInstance().updateDelivery("10001", "9999", "New Territories", "Cheung's Ancestral Hall, Tai Om Road", 6.9, "Exploded");
    	   });
    	assertEquals("Delivery State is not valid.",e.getMessage());
    }
    
    @Test
    public void testUpdateDelivery4() throws DeliveryZoneNotFoundException{
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	Exception e = assertThrows(DeliveryItemNotFoundException.class,() -> {
        	DeliveryService.getInstance().updateDelivery("420", "9999", "New Territories", "Cheung's Ancestral Hall, Tai Om Road", 6.9, "Processing");
    	   });
    	assertEquals("Delivery Item: 420 is not found.",e.getMessage());
    }
    
    @Test
    public void testDeleteDelivery1() throws DeliveryItemNotFoundException, DeliveryZoneNotFoundException{
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	DeliveryService.getInstance().deleteDelivery("10002");
    	Exception e = assertThrows(DeliveryItemNotFoundException.class,() -> {
        	DeliveryService.getInstance().getDelivery("10002");
    	   });
    	assertEquals("Delivery Item: 10002 is not found.",e.getMessage());
    }
    
    @Test
    public void testDeleteDelivery2() throws DeliveryItemNotFoundException, DeliveryZoneNotFoundException{
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	DeliveryService.getInstance().createDelivery("9999", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 3.4);
    	Exception e = assertThrows(DeliveryItemNotFoundException.class,() -> {
        	DeliveryService.getInstance().deleteDelivery("10015");
    	   });
    	assertEquals("Delivery Item: 10015 is not found.",e.getMessage());
    }
    
    @Test
    public void testCalculateDeliveryPrice1() {
    	DeliveryService.getInstance().updateDeliveryPrice(25, 10);
    	assertEquals(105, DeliveryService.getInstance().calculateDeliveryPrice(2.5, "Island District"));
    }

    @Test
    public void testCalculateDeliveryPrice2() {
    	DeliveryService.getInstance().updateDeliveryPrice(25, 10);
    	assertEquals(85, DeliveryService.getInstance().calculateDeliveryPrice(6.9, "Kowloon"));
    }
    
    @Test void testDeliveryState1() {
    	assertEquals("Pending", new OrderState_Pending().toString());
    }
    
    @Test void testDeliveryState2() {
    	assertEquals("Delivered", new OrderState_Delivered().toString());
    }
    
    @Test void testDeliveryState3() {
    	assertEquals("Dispatching", new OrderState_Dispatching().toString());
    }
 
}
