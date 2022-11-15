import java.util.Scanner;

public class HomeView {
    private ProductController productController;
    private OrderController orderController;

    public HomeView() {
        this.productController = new ProductController();
        this.orderController = new OrderController();
    }

    public void customerHome(){
        System.out.println("Welcome to the customer home page");
        System.out.println("1. View products");
        System.out.println("2. View cart");
        System.out.println("3. View orders");
        System.out.println("4. Logout");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                this.productController.displayProduct("", 0, 999999,0,5,new ProductSortByCreateDateAsc());
                break;
//            case 2:
//                showCartView();
//                break;
            case 3:
                orderController.entry();
                break;
//            case 4:
//                logout();
//                break;
            case 9:
                this.productController.addTempProduct();
                this.customerHome();
            default:
                System.out.println("Invalid choice!");
                this.customerHome();
        }
    }
}
