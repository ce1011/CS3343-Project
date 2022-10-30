public class CouponTotalPriceInvalidException extends Exception{
    public CouponTotalPriceInvalidException(double totalPrice) {
        super(totalPrice + "$ is not enough to use the coupon.");
    }
    
}
