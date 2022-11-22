import java.util.Scanner;

public class HomeController {
    private ProductController productController;
    private CouponController couponController;
    private DeliveryController deliveryController;
    
    private HomeView homeView;

    public HomeController() {
        this.productController = new ProductController();
        this.couponController = new CouponController();
        this.deliveryController = new DeliveryController();
        this.homeView = new HomeView(this);
    }
    public void showCustomerHome() {
        homeView.customerHome();
    }

    public void showAdminHome(User user) {
        homeView.currentUser(user);
        homeView.adminHome();
    }

    public void showCouponView(){
        //go to coupon view
        couponController.entryCouponView();
    }

    public void showProductView() {
    productController.filterView();

    }

    public void showOrderView(){
        //go to order view
    }

    public void showDeliveryView(){
        deliveryController.entryDeliveryView();
    }

    public void showReportView(){
        //report view
    }


}
