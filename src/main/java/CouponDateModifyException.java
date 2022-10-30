public class CouponDateModifyException extends Exception {

    public CouponDateModifyException() {
        super("The modified date cannot be eariler than the start date/current date");
    }

}
