public class CouponDateEarlyException extends Exception{
    public CouponDateEarlyException() {
        super("The start date cannot be earlier than today");
    }
}
