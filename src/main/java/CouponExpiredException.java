public class CouponExpiredException extends Exception{
    public CouponExpiredException(String endDate) {
        super("The coupon has expired on " + endDate);
    }

}
