public class CouponController {
    private CouponView cView;
    private CouponService cService;

    public CouponController(){
        this.cView = new CouponView(this);
        this.cService = CouponService.getInstance();
    }

    public void deleteCoupon(String name){
        try {
            Coupon c = cService.searchCoupon(name);
            cService.deleteCoupon(c);

        } catch (CouponNotFoundException e) {
            System.out.println(e.toString());
        }
       


    }

    public void displayCoupon(Coupon c){
        
    }



    


}
