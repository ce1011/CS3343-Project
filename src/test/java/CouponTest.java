
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class CouponTest {
	private Coupon coupon;
	SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
	
    @BeforeEach
    void setUp() throws ParseException {
        Date date1 = sdformat.parse("05-01-2022");
        Date date2 = sdformat.parse("07-01-2022");
    	coupon = new Coupon("Dessert", date1, date2, "A", 2, 50, 30, "fixedPrice");
    }
    
	@Test
	 void testGetName(){
        assertEquals("Dessert", coupon.getCouponName());
    }
	
	@Test
	void testSetName(){
		coupon.setCouponName("Food");
        assertEquals("Food", coupon.getCouponName());
    }
	
	@Test
	void testSetDiscountValue(){
		coupon.setDiscountValue(30);
        assertEquals(30.0, coupon.getDiscountValue());
    }
	
	@Test
	void testGetDiscountValue(){
        assertEquals(50.0, coupon.getDiscountValue());
    }
	
	@Test
	void testGetStartDate(){
        assertEquals("05-01-2022", sdformat.format(coupon.getStartDate()));
    }
	
	@Test
	void testSetStartDate() throws ParseException{
		Date date2 = sdformat.parse("09-01-2022");
		coupon.setStartDate(date2);
        assertEquals("09-01-2022", sdformat.format(coupon.getStartDate()));
    }
	
	@Test
	void testGetEndDate(){
        assertEquals("07-01-2022", sdformat.format(coupon.getEndDate()));
    }
	
	@Test
	void testSetEndDate() throws ParseException{
		Date date2 = sdformat.parse("11-01-2022");
		coupon.setEndDate(date2);
        assertEquals("11-01-2022", sdformat.format(coupon.getEndDate()));
    }
	
	@Test
	void testGetSetOnHoldStatus(){
	   coupon.setStatus(coupon.createState("OnHold"));
	   assertEquals("OnHold", coupon.getStatus().toString());
    }
	
	@Test
	void testGetSetAvailableStatus(){
	   coupon.setStatus(coupon.createState("Available"));
	   assertEquals("Available", coupon.getStatus().toString());
    }
	
	@Test
	void testGetSetFinishedStatus(){
	   coupon.setStatus(coupon.createState("Finished"));
	   assertEquals("Finished", coupon.getStatus().toString());
    }
	
	@Test
	 void testGetUseQuota(){
       assertEquals(2, coupon.getUseQuota());
    }
	
	@Test
	void testSetUseQuota(){
	   coupon.setUseQuota(5);
       assertEquals(5, coupon.getUseQuota());
    }
	
	@Test
	 void testGetMinimumUsagePrice(){
      assertEquals(30.0, coupon.getMinimumUsagePrice());
   }
	
	@Test
	void testSetMinimumUsagePrice(){
	  coupon.setMinimumUsagePrice(35);
      assertEquals(35.0, coupon.getMinimumUsagePrice());
   }
	
	@Test
	 void testGetType(){
      assertEquals("fixedPrice", coupon.getType());
   }
	
	@Test
	void testSetType(){
	  coupon.setType("Percentage");
      assertEquals("Percentage", coupon.getType());
   }
	
	
}
