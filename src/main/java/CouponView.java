import java.util.Scanner;

public class CouponView {
    private CouponController cCon;
    public static Scanner scan = new Scanner(System.in);

    public CouponView(CouponController cCon){
        this.cCon = cCon;
    }

    public void entryView(){
        System.out.println("Coupon Management Page");
        System.out.println("1. View Coupon List");
        System.out.println("2. Create a Coupon");
        System.out.println("3. Edit a Coupon");
        System.out.println("4. Delete a Coupon");
        System.out.println("Please input the number in front for your action:");
        int input = scan.nextInt();
        scan.close();
        switch (input){
            case 1:
                displayCouponList();
                break;
            case 2:
                createCouponView();
                break;
            case 3:
                editCouponView();
                break;
            case 4:
                deleteCouponView();
                break;
            default:
                System.out.println("Please input a correct number");
                entryView();

        }
    }

    private void deleteCouponView() {
        System.out.println("Please input the name of coupon to delete:");
        String couponName = scan.nextLine();
        scan.close();
        cCon.deleteCoupon(couponName);
        this.entryView();
    }

    private void editCouponView() {
    }

    private void createCouponView() {
    }

    public void displayCouponList(){
        
    }


}
