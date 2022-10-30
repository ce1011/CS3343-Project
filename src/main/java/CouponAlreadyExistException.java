
public class CouponAlreadyExistException extends Exception{

    public CouponAlreadyExistException() {
        super("This coupon with the same name already exist in the system");
    }

}
