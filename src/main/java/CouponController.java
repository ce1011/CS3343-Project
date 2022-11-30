import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CouponController {
    private CouponView cView;
    private CouponService cService;
    private static SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");

    public CouponController(){
        this.cView = new CouponView(this);
        this.cService = CouponService.getInstance();
    }

    public void entryCouponView(){
        cView.entryView();
    }
    
    public void deleteCoupon(String name){
        try {
            Coupon c = cService.searchCoupon(name);
            cService.deleteCoupon(c);

        } catch (CouponNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public void editCoupon(String name, int choice, String value){
        try {
            Coupon c = cService.searchCoupon(name);
            cService.updateCoupon(c, choice, value);
        }catch (CouponDateModifyException e) {
            System.out.println(e.toString());
        }catch (CouponNotFoundException e) {
            System.out.println(e.toString());
        }catch (ParseException e) {
            System.out.println(e.toString());
        }
        
    }

    public void createCoupon(String couponName, String startDate, String endDate, int quota, double minimum, String type, double discountValue, String status){
        try{
        Date sD = sdformat.parse(startDate);
        Date eD = sdformat.parse(endDate);
        Coupon c = new Coupon(couponName, sD, eD, status, quota, discountValue, minimum, type);
        cService.createCoupon(c);

        }catch (CouponAlreadyExistException e) {
            System.out.println(e.toString());
        }catch (ParseException e) {
            System.out.println(e.toString());
        }catch (CouponDateEarlyException e) {
            System.out.println(e.toString());
        }catch (CouponDateLateException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<Coupon> getCouponList(){
        return cService.getCouponList();
    }


}
