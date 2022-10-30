import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;    

public class CouponService {
    private static CouponService instance = CouponService.getInstance();
    private static ArrayList<Coupon> couponList = new ArrayList<Coupon>();
    private static SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");

    public static CouponService getInstance() {
        if (instance == null) {
            instance = new CouponService();
        }
        return instance;
    }

    public void createCoupon(Coupon c) throws CouponAlreadyExistException{
        try{
            for(Coupon coupon : couponList) {
                if(coupon.getCouponName() == c.getCouponName()){
                    throw new CouponAlreadyExistException();
                }
            }
            couponList.add(c);
        }catch(CouponAlreadyExistException e){
            throw new CouponAlreadyExistException();
        }
        
    }

    public void updateCoupon(Coupon c,int option,String value) throws ParseException, CouponDateModifyException{ //Not tested
        switch(option){
            case 1:
                couponList.get(couponList.indexOf(c)).setUseQuota(Integer.parseInt(value)); //changes quota
            case 2:
                couponList.get(couponList.indexOf(c)).setStatus(c.createState(value)); //changes status
            case 3:
                LocalDateTime now = LocalDateTime.now();
                Date d2 = sdformat.parse(sdformat.format(now));
                Date d1 = sdformat.parse(value);
                if(d1.compareTo(c.getStartDate()) < 0){
                    throw new CouponDateModifyException();
                }else if(d1.compareTo(d2) < 0){
                    throw new CouponDateModifyException();
                }else{
                    couponList.get(couponList.indexOf(c)).setEndDate(d1); //changes Enddate
                }
        }
    }

    public ArrayList getCouponList(){
        return couponList;
    }

    public Coupon searchCoupon(String name) throws CouponNotFoundException{
        for(Coupon coupon : couponList) {
            if(coupon.getCouponName() == name){
                return coupon;
            }
        }
        throw new CouponNotFoundException(name);
    }

    public boolean validateCoupon (double totalPrice, Coupon c) throws CouponTotalPriceInvalidException, CouponNotStartedException, ParseException, CouponNotAvailableException, CouponExhaustedException, CouponExpiredException{
        LocalDateTime now = LocalDateTime.now();
        Date d1 = sdformat.parse(sdformat.format(now));
        if(totalPrice < c.getMinimumUsagePrice()){
            throw new CouponTotalPriceInvalidException(totalPrice);
        }else if(d1.compareTo(c.getStartDate()) < 0){
            throw new CouponNotStartedException(sdformat.format(c.getStartDate()));
        }else if(d1.compareTo(c.getEndDate()) > 0){
            throw new CouponExpiredException(sdformat.format(c.getEndDate()));
        }else if(c.getUseQuota() <= 0){
            throw new CouponExhaustedException();
        }else if(c.getStatus().toString() == "Finished" || c.getStatus().toString() == "OnHold"){
            throw new CouponNotAvailableException();
        }else{
            return true;
        }
    }

    public double calculatePrice(double totalPrice, Coupon c) throws CouponTotalPriceInvalidException, CouponNotStartedException, ParseException, CouponNotAvailableException, CouponExhaustedException, CouponExpiredException{
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
