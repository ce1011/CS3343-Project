public class CouponExhaustedException extends Exception {
    public CouponExhaustedException() {
        super("This coupon has no remaining usage");
    }

}
