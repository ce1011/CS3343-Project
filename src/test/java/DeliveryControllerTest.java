import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryControllerTest {
	
	@Test
	public void editDeliveryInfoTest() throws DeliveryZoneNotFoundException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryService.getInstance().createDelivery("10001", "Kowloon", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();
	    dc.editDeliveryInfo("10001", "9999", "Kowloon", "Cityu", 6.9);
	    assertEquals("Success !\n", outContent.toString());
	}
	@Test
	public void editDeliveryInfoTest1() throws DeliveryZoneNotFoundException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryService.getInstance().createDelivery("1999", "Kowloon", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();
	    dc.editDeliveryInfo("11155", "9999", "Kowloon", "Cityu", 6.9);
	    assertEquals("DeliveryItemNotFoundException: Delivery Item: 11155 is not found.\n", outContent.toString());
	}
	
	@Test
	public void editDeliveryInfoTest2() throws DeliveryZoneNotFoundException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryService.getInstance().createDelivery("10008", "Kowloon", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();
	    dc.editDeliveryInfo("10001", "9999", "Shanghai", "Cityu", 6.9);
	    assertEquals("DeliveryZoneNotFoundException: Zone: Shanghai is not found.\n", outContent.toString());
	}
	
	@Test
	public void editDeliverySystemPriceTest() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.editDeliverySystemPrice(99, 9999);
	    assertEquals("Success !\n", outContent.toString());
	}

	@Test
	public void findDeliveryTest()  throws DeliveryZoneNotFoundException, DeliveryItemNotFoundException {
		DeliveryService.getInstance().createDelivery("100044", "Islands District", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();
	    assertEquals(DeliveryService.getInstance().getDelivery("10001"),dc.findDelivery("10001"));
	}
	
	@Test
	public void findDeliveryTest1()  throws DeliveryZoneNotFoundException, DeliveryItemNotFoundException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryService.getInstance().createDelivery("100044", "New Territories", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();
	    assertEquals(null,dc.findDelivery("10004"));
	}
	
	@Test
	public void createDeliveryTest() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryController dc = new DeliveryController();
	    dc.createDelivery("1000", "Hong Kong", "CityU", 0.3);
	    assertEquals("Success !\n", outContent.toString());
	}
	
	@Test
	public void createDeliveryTest1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryController dc = new DeliveryController();
	    dc.createDelivery("1000", "Ting Kau", "CityU", 0.3);
	    assertEquals("DeliveryZoneNotFoundException: Zone: Ting Kau is not found.\n", outContent.toString());
	}
	
	@Test
	public void addDeliveryZoneTest() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryController dc = new DeliveryController();
	    dc.addDeliveryZone("Sai Kung");
	    assertEquals("Success !\n", outContent.toString());
	}
	
	@Test
	public void deleteDeliveryZoneTest() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryController dc = new DeliveryController();
	    dc.addDeliveryZone("Sai Kung");
	    dc.deleteDeliveryZone("Sai Kung");
	    assertEquals("Success !\n", outContent.toString());
	}
	
	@Test
	public void deleteDeliveryZoneTest1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryController dc = new DeliveryController();
	    //dc.addDeliveryZone("Sai Kung");
	    dc.deleteDeliveryZone("Hong Kong1");
	    assertEquals("DeliveryZoneNotFoundException: Zone: Hong Kong1 is not found.\n", outContent.toString());
	}
	
	@Test
	public void deleteDeliveryTest() throws Exception{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryService.getInstance().createDelivery("10008", "Kowloon", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();		
	    dc.deleteDelivery("10004");
	    assertEquals("Success !\n", outContent.toString());
	}
	
	@Test
	public void deleteDeliveryTest1() throws Exception{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryService.getInstance().createDelivery("10008", "Kowloon", "Cityu", 5.2);
	    DeliveryController dc = new DeliveryController();		
	    dc.deleteDelivery("11155");
	    assertEquals("DeliveryItemNotFoundException: Delivery Item: 11155 is not found.\n", outContent.toString());
	}
	
	@Test
	public void getDeliveryListTest() throws Exception {
		DeliveryController dc = new DeliveryController();		
	    DeliveryService.getInstance().createDelivery("10008", "Kowloon", "Cityu", 5.2);
		assertNotNull(dc.getDeliveryList());
	}

}
