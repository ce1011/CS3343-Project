import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {

    private OrderController controller;

    public OrderView(OrderController controller){
        this.controller = controller;
    }

    public void displayOrderList(ArrayList<Order> orderList, int currentPage, int totalPage, int itemPerPage, OrderSortType sort){


    }

    public void entryView() {
        System.out.println("Welcome to Order Panel!");
        System.out.println("Sort order list by");
        System.out.println("1. By create date");
        System.out.println("2. By Price");
        System.out.println("3. By TransactionID");
//        System.out.println("4. By customer name");
        System.out.println("Sort by (Key in the number): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.println("Sort by Ascending or Descending order");
        System.out.println("Sort in (Key in the number): ");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int sortIn = scanner.nextInt();

        controller.displayOrder(choice, sortIn);

    }


}