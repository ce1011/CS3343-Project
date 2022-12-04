
public class CouponDateLateException extends Exception{
    public CouponDateLateException() {
        super("The end date cannot be before start date");
    }
}

