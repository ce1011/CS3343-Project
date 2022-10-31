import static org.junit.Assert.*;				
import org.junit.Test;	

class DeliveryTest {
    @Test
    void testSetDeliveryState(){
        Delivery d = new Delivery("10001", "10001", "Kowloon", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setDeliveryState("Processing");
        assertEquals(new DeliveryState_Processing(),d.getDeliveryState());
        
    }

}
