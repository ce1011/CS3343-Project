public class CouponNotFoundException extends Exception {
    public CouponNotFoundException(String name) {
        super(name + " does not exist in the system");
    }

}
