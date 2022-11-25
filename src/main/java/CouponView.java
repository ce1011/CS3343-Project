import java.util.ArrayList;
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
        System.out.println("5. Return to main menu");
        System.out.println("Please input the number in front for your action:");
        int input = scan.nextInt();
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
            case 5:
                return;
            default:
                System.out.println("Please input a correct number");
                entryView();

        }
    }

    private void deleteCouponView() {
        System.out.println("Please input the name of coupon to delete:");
        String couponName = scan.next();
        cCon.deleteCoupon(couponName);
        this.entryView();
    }

    private void editCouponView() {
        System.out.println("Please input the name of coupon to edit:");
        String couponName = scan.next();
        System.out.println("Please enter the number of the item you want to edit:");
        System.out.println("1. Change Quota of the coupon");
        System.out.println("2. Change the Status of the coupon (e.g OnHold/Available/Finished)");
        System.out.println("3. Change the EndDate of the coupon (e.g 11-01-2022)");
        int choice = scan.nextInt();
        System.out.println("Please input the value to change");
        String value = scan.next();
        cCon.editCoupon(couponName, choice, value);
        this.entryView();  
    }

    private void createCouponView() {
        System.out.println("Please input the name of coupon:");
        String couponName = scan.next();
        System.out.println("Please input the earliest date the coupon can be used (e.g 15-01-2022):");
        String startDate = scan.next();
        System.out.println("Please input the date the coupon ends (e.g 22-01-2022):");
        String endDate = scan.next();
        System.out.println("Please input quota the coupon has:");
        int quota = scan.nextInt();
        System.out.println("Please input the minimum price to use the coupon:");
        double minimum = scan.nextDouble();
        System.out.println("Please input the discount type of the coupon (fixedValue/percentage):");
        String type = scan.next();
        System.out.println("Please input the discount value of the coupon (for percentage 1-99):");
        double discountValue = scan.nextDouble();
        System.out.println("Please input the status of the coupon (OnHold/Available/Finished):");
        String status = scan.next();
        cCon.createCoupon(couponName, startDate, endDate, quota, minimum, type, discountValue, status);
        this.entryView();  
    }

    public void displayCouponList(){
        ArrayList<Coupon> cList = cCon.getCouponList();
        System.out.format("%20s|%11s|%11s|%15s|%3d|%5f|%5f|%15s\n", "CouponName", "StartDate", "EndDate", "Status", "UseQuota", "DiscountValue", "MinimumUsagePrice", "Type");
        for(Coupon c: cList){
            System.out.format("%20s %11s %11s %15s %3d %5f %5f %15s\n", c.getCouponName(), c.getStartDate(), c.getEndDate(), c.getStatus(), c.getUseQuota(), c.getDiscountValue(), c.getMinimumUsagePrice(), c.getType());
        }
        this.entryView();
    }

}
