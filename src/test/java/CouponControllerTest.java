import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class CouponControllerTest {
	
	@Test
	public void createCouponTest() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$20 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available");
		assertNotNull(CouponService.getInstance().searchCoupon("-$20 Discount"));
	}
	
	@Test
	public void createCouponTest1() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$20 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("",outContent.toString());
	}
	
	@Test
	public void createCouponTest2() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$20 Discount", "20-12-2022", "2812-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("",outContent.toString());
	}
	
	@Test
	public void createCouponTest3() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$2 Discount", "20-11-2022", "28-11-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("",outContent.toString());
	}
	
	
	@Test
	public void deleteCouponTest() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$22 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		cc.deleteCoupon("-$22 Discount");
		assertTrue(CouponService.getInstance().searchCoupon("-$22 Discount").getStatus() instanceof CouponState_Finished);
	}
	
	@Test
	public void deleteCouponTest1() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$22 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		cc.deleteCoupon("-$222 Discount");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		assertEquals("",outContent.toString());
	}
	@Test
	public void editCouponTest() {
		
	}
	@Test
	public void entryCouponViewTest() {
		
	}
	@Test
	public void getCouponListTest() {
		
	}
}
