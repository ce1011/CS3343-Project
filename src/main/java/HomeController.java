import java.util.Scanner;

public class HomeController {
    private ProductController productController;
    private HomeView homeView;

    public HomeController() {
        this.productController = new ProductController();
        this.homeView = new HomeView();
    }
    public void showCustomerHome() {
        homeView.customerHome();
    }
}
