
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class CouponServiceTest {
	SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
	
    @BeforeEach
    void setUp() throws ParseException, Exception {	
    	CouponService.getInstance().createCoupon(new Coupon("Dessert", sdformat.parse("05-01-2022"), sdformat.parse("07-01-2022"), "Finished", 2, 50, 30, "fixedPrice"));
    	CouponService.getInstance().createCoupon(new Coupon("Food", sdformat.parse("12-03-2022"), sdformat.parse("21-05-2022"), "Available", 5, 20, 100, "fixedPrice"));
    	CouponService.getInstance().createCoupon(new Coupon("Gym", sdformat.parse("05-08-2022"), sdformat.parse("07-09-2022"), "OnHold", 10, 30, 300, "Percentage"));
    	CouponService.getInstance().createCoupon(new Coupon("Pen", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2022"), "OnHold", 50, 70, 800, "Percentage"));
    }
    
    @AfterEach
    void resetCouponService() {
        CouponService.resetCouponService();
    }
    
    @Test
    void getInstance() {
        CouponService couponService = CouponService.getInstance();
        assertNotNull(couponService);
    }
    
    @Test
    public void testCreateCoupon(){
    	CouponService c = CouponService.getInstance();
    	Exception e = assertThrows(CouponAlreadyExistException.class,() -> {
    		c.createCoupon(new Coupon("Dessert", sdformat.parse("05-01-2022"), sdformat.parse("07-01-2022"), "Finished", 2, 50, 30, "fixedPrice"));
    	   });
    	assertEquals("This coupon with the same name already exist in the system",e.getMessage());
    }
    
    
    @Test
    public void testUpdateCoupon() throws ParseException, CouponDateModifyException{
    	Coupon c = (Coupon) CouponService.getInstance().getCouponList().get(0);
    	CouponService.getInstance().updateCoupon(c, 1, "10");
    }
    
    @Test
    public void testUpdateCoupon2() throws ParseException, CouponDateModifyException{
    	Coupon c = (Coupon) CouponService.getInstance().getCouponList().get(0);
    	CouponService.getInstance().updateCoupon(c, 2, "OnHold");
    	assertEquals("OnHold",c.getStatus().toString());
    }
    
    @Test
    public void testUpdateCoupon3() throws ParseException, CouponDateModifyException{
    	Coupon c = (Coupon) CouponService.getInstance().getCouponList().get(0);
    	Exception e = assertThrows(CouponDateModifyException.class,() -> {
    		CouponService.getInstance().updateCoupon(c, 3, "01-01-2022");
    	   });
    	assertEquals("The modified date cannot be eariler than the start date/current date",e.getMessage());
    }
    
    @Test
    public void testUpdateCoupon4() throws ParseException, CouponDateModifyException{
    	Coupon c = (Coupon) CouponService.getInstance().getCouponList().get(0);
    	Exception e = assertThrows(CouponDateModifyException.class,() -> {
    		CouponService.getInstance().updateCoupon(c, 3, "05-11-2022");
    	   });
    	assertEquals("The modified date cannot be eariler than the start date/current date",e.getMessage());
    }
    
    @Test
    public void testUpdateCoupon5() throws ParseException, CouponDateModifyException{
    	Coupon c = (Coupon) CouponService.getInstance().getCouponList().get(0);
    	CouponService.getInstance().updateCoupon(c, 3, "07-11-2022");
    }
    
    @Test
    public void testUpdateCoupon6() throws ParseException, CouponDateModifyException{
    	Coupon c = (Coupon) CouponService.getInstance().getCouponList().get(0);
    	CouponService.getInstance().updateCoupon(c, 4, "07-11-2022");
    }
    
    @Test
    public void testSearchCoupon() throws CouponNotFoundException{
    	Coupon c = CouponService.getInstance().searchCoupon("Dessert");
    	assertEquals("Dessert",c.getCouponName());
    }
    
    @Test
    public void testSearchCoupon2() throws CouponNotFoundException{
    	Coupon c = CouponService.getInstance().searchCoupon("Dessert");
    	Exception e = assertThrows(CouponNotFoundException.class,() -> {
    		CouponService.getInstance().searchCoupon("Gun");
    	   });
    	assertEquals("Gun does not exist in the system",e.getMessage());
    }
    
    @Test
    public void testValidateCoupon() throws CouponNotFoundException{
    	Coupon c = CouponService.getInstance().searchCoupon("Dessert");
    	Exception e = assertThrows(CouponTotalPriceInvalidException.class,() -> {
    		boolean check = CouponService.getInstance().validateCoupon(20.5, c);
    	   });
    	assertEquals("20.5$ is not enough to use the coupon.",e.getMessage());
    }
    
    
    
    
	
	
	
	
	
}
