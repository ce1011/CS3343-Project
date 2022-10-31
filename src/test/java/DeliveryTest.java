import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;


public class DeliveryTest {
    @Test
    public void testSetDeliveryState1(){
        Delivery d = new Delivery("10001", "10001", "Kowloon", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setDeliveryState("Processing");
        assertEquals("Processing",d.getDeliveryState().toString());
    } 
    @Test
    public void testSetDeliveryState2(){
        Delivery d = new Delivery("10001", "10001", "Hong Kong", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setDeliveryState("Pending");
        assertEquals("Pending",d.getDeliveryState().toString());
    }
    @Test
    public void testSetDeliveryState3(){
        Delivery d = new Delivery("10001", "10001", "New Territories", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setDeliveryState("Dispatching");
        assertEquals("Dispatching",d.getDeliveryState().toString());
    }
    @Test
    public void testSetDeliveryState4(){
        Delivery d = new Delivery("10001", "10001", "Island Districts", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setDeliveryState("Delivered");
        assertEquals("Delivered",d.getDeliveryState().toString());
    }
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
    @Test
    public void testSetEstDeliveryDate(){
        Delivery d = new Delivery("10001", "10001", "Kowloon", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        d.setEstDeliveryDate(LocalDateTime.parse("2022-10-31T00:00:00"));
        assertEquals("2022-10-31T00:00",d.getEstDeliveryDate().toString());
    }
    @Test
    public void testGetCreatedDate(){
        Delivery d = new Delivery("10001", "10001", "Kowloon", "#19 G/F Yau Kom Tau Village, Kowloon,Hongkong", 30);
        LocalDateTime date = LocalDateTime.now();
        assertEquals(date.toString(),d.getCreatedDate().toString());
    }
}
