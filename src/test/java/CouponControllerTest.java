import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CouponControllerTest {
	
	@Test
	public void entryCouponViewTest() {
		System.setIn(new ByteArrayInputStream("5\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CouponController cc = new CouponController();
		cc.entryCouponView();
		assertEquals("Coupon Management Page\r\n".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
				+ "1. View Coupon List\r\n".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
				+ "2. Create a Coupon\r\n".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
				+ "3. Edit a Coupon\r\n".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
				+ "4. Delete a Coupon\r\n".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
				+ "5. Return to main menu\r\n".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", "")
				+ "Please input the number in front for your action:".replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
	}
	
	@Test
	public void entryCouponViewTest1() throws Exception{
		SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
        CouponService.getInstance().createCoupon(new Coupon("CouponValid", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2023"), "Available", 5, 70, 999, "Percentage"));
		System.setIn(new ByteArrayInputStream("4\nCouponValid\n5\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CouponController cc = new CouponController();
		cc.entryCouponView();
		String expect = "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n"
				+ "Please input the name of coupon to delete:\r\n"
				+ "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n"
				+ "";
		assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
	}
	
	@Test
	public void entryCouponViewTest2() throws Exception{
		SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
        CouponService.getInstance().createCoupon(new Coupon("CouponValid1", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2023"), "Available", 5, 70, 999, "Percentage"));
		System.setIn(new ByteArrayInputStream("3\nCouponValid1\n1\n100\n5\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CouponController cc = new CouponController();
		cc.entryCouponView();
		String expect = "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n"
				+ "Please input the name of coupon to edit:\r\n"
				+ "Please enter the number of the item you want to edit:\r\n"
				+ "1. Change Quota of the coupon\r\n"
				+ "2. Change the Status of the coupon (e.g OnHold/Available/Finished)\r\n"
				+ "3. Change the EndDate of the coupon (e.g 11-01-2022)\r\n"
				+ "Please input the value to change\r\n"
				+ "Changes Complete.\r\n"
				+ "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:";
		assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
	}
	
	@Test
	public void entryCouponViewTest3() throws Exception{
		System.setIn(new ByteArrayInputStream("2\nCouponValid2\n15-12-2022\n16-12-2022\n15\n100\nfiexedValue\n33\nOnHold\n5\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CouponController cc = new CouponController();
		cc.entryCouponView();
		String expect = "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n"
				+ "Please input the name of coupon:\r\n"
				+ "Please input the earliest date the coupon can be used (e.g 15-01-2022):\r\n"
				+ "Please input the date the coupon ends (e.g 22-01-2022):\r\n"
				+ "Please input quota the coupon has:\r\n"
				+ "Please input the minimum price to use the coupon:\r\n"
				+ "Please input the discount type of the coupon (fixedValue/percentage):\r\n"
				+ "Please input the discount value of the coupon (for percentage 1-99):\r\n"
				+ "Please input the status of the coupon (OnHold/Available/Finished):\r\n"
				+ "Coupon Successfully Created.\r\n"
				+ "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:";
		assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
	}
	
	@Test
	public void entryCouponViewTest4() throws Exception{
		SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
		CouponService.getInstance().createCoupon(new Coupon("Dessert", sdformat.parse("05-01-2022"), sdformat.parse("07-01-2022"), "Finished", 2, 50, 30, "fixedPrice"));
    	CouponService.getInstance().createCoupon(new Coupon("Food", sdformat.parse("12-03-2022"), sdformat.parse("21-05-2022"), "Available", 5, 20, 100, "fixedPrice"));
    	CouponService.getInstance().createCoupon(new Coupon("Gym", sdformat.parse("05-08-2022"), sdformat.parse("07-09-2022"), "OnHold", 10, 30, 300, "Percentage"));
    	CouponService.getInstance().createCoupon(new Coupon("Pen", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2022"), "OnHold", 50, 70, 800, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("CouponNotStartedException", sdformat.parse("15-09-2023"), sdformat.parse("17-11-2023"), "Available", 50, 70, 100, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("CouponTotalPriceInvalidException", sdformat.parse("15-09-2023"), sdformat.parse("17-11-2023"), "Available", 50, 70, 999, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("Couponta", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2023"), "OnHold", 1, 70, 999, "Percentage"));
		System.setIn(new ByteArrayInputStream("1\n5\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CouponController cc = new CouponController();
		cc.entryCouponView();
		String expect = "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n"
				+ "          CouponName|  StartDate|    EndDate|         Status|UseQuota|DiscountValue|MinimumUsagePrice|           Type\r\n"
				+ "      -$100 Discount|Tue Dec 20 00:00:00 HKT 2022|Wed Dec 28 00:00:00 HKT 2022|      Available|100|0.000000|99.900000|     fixedPrice\r\n"
				+ "       -$20 Discount|Tue Dec 20 00:00:00 HKT 2022|Wed Dec 28 00:00:00 HKT 2022|      Available|100|0.000000|99.900000|     fixedPrice\r\n"
				+ "        -$2 Discount|Sun Nov 20 00:00:00 HKT 2022|Mon Nov 28 00:00:00 HKT 2022|      Available|100|0.000000|99.900000|     fixedPrice\r\n"
				+ "     -$1000 Discount|Sun Nov 20 00:00:00 HKT 2022|Wed Dec 28 00:00:00 HKT 2022|      Available|100|0.000000|99.900000|     fixedPrice\r\n"
				+ "        -$8 Discount|Tue Dec 20 00:00:00 HKT 2022|Wed Dec 28 00:00:00 HKT 2022|      Available|100|0.000000|99.900000|     fixedPrice\r\n"
				+ "       -$22 Discount|Tue Dec 20 00:00:00 HKT 2022|Wed Dec 28 00:00:00 HKT 2022|       Finished|  0|0.000000|99.900000|     fixedPrice\r\n"
				+ "         CouponValid|Thu Sep 15 00:00:00 HKT 2022|Fri Nov 17 00:00:00 HKT 2023|       Finished|  0|70.000000|999.000000|     Percentage\r\n"
				+ "        CouponValid1|Thu Sep 15 00:00:00 HKT 2022|Fri Nov 17 00:00:00 HKT 2023|      Available|100|70.000000|999.000000|     Percentage\r\n"
				+ "        CouponValid2|Thu Dec 15 00:00:00 HKT 2022|Fri Dec 16 00:00:00 HKT 2022|         OnHold| 15|33.000000|100.000000|    fiexedValue\r\n"
				+ "             Dessert|Wed Jan 05 00:00:00 HKT 2022|Fri Jan 07 00:00:00 HKT 2022|       Finished|  2|50.000000|30.000000|     fixedPrice\r\n"
				+ "                Food|Sat Mar 12 00:00:00 HKT 2022|Sat May 21 00:00:00 HKT 2022|      Available|  5|20.000000|100.000000|     fixedPrice\r\n"
				+ "                 Gym|Fri Aug 05 00:00:00 HKT 2022|Wed Sep 07 00:00:00 HKT 2022|         OnHold| 10|30.000000|300.000000|     Percentage\r\n"
				+ "                 Pen|Thu Sep 15 00:00:00 HKT 2022|Thu Nov 17 00:00:00 HKT 2022|         OnHold| 50|70.000000|800.000000|     Percentage\r\n"
				+ "CouponNotStartedException|Fri Sep 15 00:00:00 HKT 2023|Fri Nov 17 00:00:00 HKT 2023|      Available| 50|70.000000|100.000000|     Percentage\r\n"
				+ "CouponTotalPriceInvalidException|Fri Sep 15 00:00:00 HKT 2023|Fri Nov 17 00:00:00 HKT 2023|      Available| 50|70.000000|999.000000|     Percentage\r\n"
				+ "            Couponta|Thu Sep 15 00:00:00 HKT 2022|Fri Nov 17 00:00:00 HKT 2023|         OnHold|  1|70.000000|999.000000|     Percentage\r\n"
				+ "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n";
		assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
	}
	
	@Test
	public void entryCouponViewTest5() {
		System.setIn(new ByteArrayInputStream("9\n5\n".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CouponController cc = new CouponController();
		cc.entryCouponView();
		System.err.print(outContent.toString());
		String expect = "Coupon Management Page\r\n"
				+ "1. View Coupon List\r\n"
				+ "2. Create a Coupon\r\n"
				+ "3. Edit a Coupon\r\n"
				+ "4. Delete a Coupon\r\n"
				+ "5. Return to main menu\r\n"
				+ "Please input the number in front for your action:\r\n"
				+ "Please input a correct number";
		assertEquals(expect.replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""),outContent.toString().replace("\n","").replace("\s", "").replace("\r", "").replace(" ", ""));
		}
	
	@Test
	public void createCouponTest() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$20 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available");
		assertNotNull(CouponService.getInstance().searchCoupon("-$20 Discount"));
	}
	
	@Test
	public void createCouponTest1() throws Exception {
		CouponController cc = new CouponController();
		cc.createCoupon("-$220 Discount", "20-12-2022", "28-11-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
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
		CouponController cc = new CouponController();
		cc.createCoupon("-$100 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		cc.editCoupon("-$100 Discount", 1, "100");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		assertEquals("",outContent.toString());
	}
	
	@Test
	public void editCouponTest1() {
		CouponController cc = new CouponController();
		cc.createCoupon("-$1000 Discount", "20-11-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		cc.editCoupon("-$1000 Discount", 3, "1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("",outContent.toString());
	}
	@Test
	public void editCouponTest2() {
		CouponController cc = new CouponController();
		cc.createCoupon("-$8 Discount", "20-12-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		cc.editCoupon("-$8 Discount", 3, "01-01-2022");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("",outContent.toString());
	}
	
	@Test
	public void editCouponTest3() {
		CouponController cc = new CouponController();
		cc.createCoupon("-$1000 Discount", "20-11-2022", "28-12-2022", 100, 99.9, "fixedPrice", 0, "Available"); 
		cc.editCoupon("-$10001 Discount", 1, "1");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals("",outContent.toString());
	}
	
	
	@Test
	public void getCouponListTest() {
		CouponController cc = new CouponController();
		assertNotNull(cc.getCouponList());
	}
}
 