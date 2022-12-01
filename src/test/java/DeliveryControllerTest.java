import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class DeliveryControllerTest {
	
	@Test
	public void entryDeliveryViewTest() {
	    System.setIn(new ByteArrayInputStream("-1".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
	    assertTrue(outContent.toString().contains("Invalid choice!"));
	}
	
	@Test
	public void entryDeliveryViewTest1() {
	    System.setIn(new ByteArrayInputStream("1\n10001\n999\nKowloon\nCityU AC2\n6.34\n8".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest2() throws Exception {
		DeliveryService.getInstance().createDelivery("13213", "Kowloon", "fsdfsdf", 0.3);
	    System.setIn(new ByteArrayInputStream("2\n10001\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest3() throws Exception {
	    System.setIn(new ByteArrayInputStream("2\n10011\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest4() throws Exception {
	    System.setIn(new ByteArrayInputStream("3\n11\n9\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest5() throws Exception {
	    System.setIn(new ByteArrayInputStream("4\n10003\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest6() throws Exception {
	    System.setIn(new ByteArrayInputStream("5\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest7() throws Exception {
	    System.setIn(new ByteArrayInputStream("6\nHam Tin\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void entryDeliveryViewTest8() throws Exception {
	    System.setIn(new ByteArrayInputStream("7\nHam Tin\n8\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryController dc = new DeliveryController();
		dc.entryDeliveryView();
		String result = outContent.toString();
		System.err.print(outContent.toString());
		String expect = "Please enter your choice:";
		assertTrue(result.contains(expect));
	}
	
	@Test
	public void editDeliveryInfoTest() throws DeliveryZoneNotFoundException {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DeliveryService.getInstance().addDeliveryZone("Kowloon");
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
	    assertEquals(null,dc.findDelivery("10110"));
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
	    DeliveryService.getInstance().addDeliveryZone("Sai Kung");
	    dc.deleteDeliveryZone("Sai Kung");
	    assertEquals("Success !\n", outContent.toString());
	}
	
	@Test
	public void deleteDeliveryZoneTest1() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    DeliveryController dc = new DeliveryController();
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
