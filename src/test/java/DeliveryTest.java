import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;


public class DeliveryTest {
    @Test
    public void testGetDeliveryID(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        assertEquals("10001",d.getDeliveryID());
    }
    @Test
    public void testGetOrderID(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        assertEquals("10001",d.getOrderID());
    }
    @Test
    public void testGetZoneID(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        assertEquals("Island Districts",d.getZone());
    }
    @Test
    public void testGetAddress(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        assertEquals("#19 G/F Yau Kom Tau Village, Kowloon,Hongkong",d.getAddress());
    }
    @Test
    public void testGetDeliveryFee(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        assertEquals(30,d.getDeliveryFee());
    }
    @Test
    public void testSetOrderID(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setOrderID("9999");
        assertEquals("9999",d.getOrderID());
    }
    @Test
    public void testSetZone(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setZone("Kowloon");
        assertEquals("Kowloon",d.getZone());
    }
    @Test
    public void testSetAddress(){
        Delivery d = new Delivery("10001", "10001", "Kowloon", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setAddress("2108 Paul Y. Centre 51 Hung To Road Kowloon, Kowloon,Hongkong");
        assertEquals("2108 Paul Y. Centre 51 Hung To Road Kowloon, Kowloon,Hongkong",d.getAddress());
    }
    @Test
    public void testSetDeliveryFee(){
        Delivery d = new Delivery("10001", "10001", "Kowloon", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setDeliveryFee(50);
        assertEquals(50,d.getDeliveryFee());
    }
}
