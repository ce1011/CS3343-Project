import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;    

public class CouponService {
    private static CouponService instance = CouponService.getInstance();
    private static ArrayList<Coupon> couponList = new ArrayList<Coupon>();
    //private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
    private LocalDateTime now = LocalDateTime.now();

    public static CouponService getInstance() {
        if (instance == null) {
            instance = new CouponService();
        }
        return instance;
    }

    public void createCoupon(Coupon coupon){
        couponList.add(coupon);
    }

    public ArrayList getCouponList(){
        return couponList;
    }

    public boolean validateCoupon (double totalPrice, Coupon c) throws CouponTotalPriceInvalidException, CouponNotStartedException, ParseException{
        Date d1 = sdformat.parse(sdformat.format(now));
        Date d2 = sdformat.parse(sdformat.format(c.getStartDate()));
        if(totalPrice < c.getMinimumUsagePrice()){
            throw new CouponTotalPriceInvalidException(totalPrice);
        }else if(d1.compareTo(d2) < 0){
            throw new CouponNotStartedException(sdformat.format(c.getStartDate()));
        }else{
            return true;
        }
    }

    public double calculatePrice(double totalPrice, Coupon c) throws CouponTotalPriceInvalidException, CouponNotStartedException, ParseException{
        if(validateCoupon(totalPrice, c)){
            if(c.getType() == "fixedPrice"){
                totalPrice -= c.getDiscountValue();
            }else if(c.getType() == "percentage"){
                totalPrice = totalPrice * (c.getDiscountValue()/100);
            }
        }
        return totalPrice;
    }

}
