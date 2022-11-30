
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
        CouponService.getInstance().createCoupon(new Coupon("CouponNotStartedException", sdformat.parse("15-09-2023"), sdformat.parse("17-11-2023"), "Available", 50, 70, 100, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("CouponTotalPriceInvalidException", sdformat.parse("15-09-2023"), sdformat.parse("17-11-2023"), "Available", 50, 70, 999, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("Couponta", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2023"), "OnHold", 1, 70, 999, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("Coupont", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2023"), "Available", 0, 70, 999, "Percentage"));
        CouponService.getInstance().createCoupon(new Coupon("fixed", sdformat.parse("05-01-2022"), sdformat.parse("07-01-2023"), "Available", 2, 50, 60, "fixedPrice"));
        CouponService.getInstance().createCoupon(new Coupon("percentage", sdformat.parse("05-01-2022"), sdformat.parse("07-01-2023"), "Available", 2, 50, 90, "percentage"));
        CouponService.getInstance().createCoupon(new Coupon("CouponValid", sdformat.parse("15-09-2022"), sdformat.parse("17-11-2023"), "Available", 5, 70, 999, "Percentage"));
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
<<<<<<< Updated upstream
    	CouponService.getInstance().updateCoupon(c, 3, "07-11-2022");
=======
    	CouponService.getInstance().updateCoupon(c, 3, "30-11-2022");
>>>>>>> Stashed changes
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
    @Test
    public void testDeleteCoupon() throws CouponNotFoundException{
        Coupon c = CouponService.getInstance().searchCoupon("Dessert");
        CouponService.getInstance().deleteCoupon(c);
        Coupon p = (Coupon) CouponService.getInstance().getCouponList().get(0);
        assertEquals("Finished", p.getStatus().toString());
    }

    @Test
    public void testCalculatePrice() throws CouponNotFoundException{
        Coupon c = CouponService.getInstance().searchCoupon("CouponNotStartedException");
        Exception e = assertThrows(CouponNotStartedException.class, () -> {
            boolean check = CouponService.getInstance().validateCoupon(900, c);
        });
    }

    @Test
    public void CouponTotalPriceInvalidException() throws CouponNotFoundException{
        Coupon c = CouponService.getInstance().searchCoupon("Gym");
        Exception e = assertThrows(CouponExpiredException.class, () -> {
            boolean check = CouponService.getInstance().validateCoupon(500, c);
        });
    }

    @Test
    public void CouponExpiredException() throws CouponNotFoundException{
        Coupon c = CouponService.getInstance().searchCoupon("CouponTotalPriceInvalidException");
        Exception e = assertThrows(CouponTotalPriceInvalidException.class, () -> {
            boolean check = CouponService.getInstance().validateCoupon(500, c);
        });
    }

    @Test
    public void CouponExhaustedException() throws CouponNotFoundException{
        Coupon c = CouponService.getInstance().searchCoupon("Coupont");
        Exception e = assertThrows(CouponExhaustedException.class, () -> {
            boolean check = CouponService.getInstance().validateCoupon(9999, c);
        });
    }

    @Test
    public void CouponException() throws CouponNotFoundException{
        Coupon c = CouponService.getInstance().searchCoupon("Couponta");
        Exception e = assertThrows(CouponNotAvailableException.class, () -> {
            boolean check = CouponService.getInstance().validateCoupon(9999, c);
        });
    }

    @Test
    public void CouponVaild() throws CouponNotFoundException, CouponTotalPriceInvalidException, CouponNotAvailableException, CouponNotStartedException, ParseException, CouponExpiredException, CouponExhaustedException {
        Coupon c = CouponService.getInstance().searchCoupon("CouponValid");
            assertEquals(true, CouponService.getInstance().validateCoupon(9999, c));

    }

    @Test
    public void CouponCalculate() throws CouponNotFoundException, CouponTotalPriceInvalidException, CouponNotAvailableException, CouponNotStartedException, ParseException, CouponExpiredException, CouponExhaustedException {
        Coupon c = CouponService.getInstance().searchCoupon("fixed");
        assertEquals(10.0, CouponService.getInstance().calculatePrice(60, c));

    }

    @Test
    public void CouponCalculatePer() throws CouponNotFoundException, CouponTotalPriceInvalidException, CouponNotAvailableException, CouponNotStartedException, ParseException, CouponExpiredException, CouponExhaustedException {
        Coupon c = CouponService.getInstance().searchCoupon("percentage");
        assertEquals(50.0, CouponService.getInstance().calculatePrice(100, c));

    }
    
    
    
    
	
	
	
	
	
}
