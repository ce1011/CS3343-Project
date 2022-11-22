import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {

    private OrderController controller;

    public OrderView(OrderController controller){
        this.controller = controller;
    }

    public void customerFilter() {
        String transactionID;
        OrderSortType sort = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Order list panel");
        System.out.println("Sort order list by");
        System.out.println("1. By create date");
        System.out.println("2. By Price");
        System.out.println("3. By TransactionID");
        System.out.println("Sort by: ");
        int sortBy = scanner.nextInt();
        System.out.println("Sort in Ascending or Descending order");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.println("Sort in: ");
        int sortIn = scanner.nextInt();

        switch(sortBy){
            case 1:
                sort = switch (sortIn) {
                    case 1 -> new OrderSortByCreateDate_ASC();
                    case 2 -> new OrderSortByCreateDate_DESC();
                    default -> new OrderSortByCreateDate_ASC();
                };
            case 2:
                sort = switch (sortIn) {
                    case 1 -> new OrderSortByPrice_ASC();
                    case 2 -> new OrderSortByPrice_DESC();
                    default -> new OrderSortByPrice_ASC();
                };
            case 3:
                sort = switch (sortIn) {
                    case 1 -> new OrderSortByTransactionID_ASC();
                    case 2 -> new OrderSortByTransaction_DESC();
                    default -> new OrderSortByTransactionID_ASC();
                };
        }
        controller.displayCustomerOrderList(sort);
    }

    public void displayCustomerOrderList(ArrayList<Order> orderList, OrderSortType sort){

    }
}