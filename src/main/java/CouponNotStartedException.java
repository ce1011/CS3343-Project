public class CouponNotStartedException extends Exception{
    public CouponNotStartedException(String startDate) {
        super("The coupon is available on " + startDate);
    }
}
