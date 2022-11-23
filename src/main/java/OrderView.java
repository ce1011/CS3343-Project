import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {

    private OrderController controller;

    public OrderView(OrderController controller){
        this.controller = controller;
    }

    public void customerOrderView(User user) {
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
        controller.displayCustomerOrderList(user, sort);
    }

    public void displayCustomerOrderList(ArrayList<Order> orderList, User user, OrderSortType sort){
        System.out.format("%4s|%6s|%25s|%20s|%8s|%12s\n", "No.", "TransactionID", "Product", "Order Date", "Total Price", "Order status");
        int i=1;
        for(Order order : orderList) {
            System.out.format("%6s|%25s|%20s|%12s|%8s|%12s\n", i, order.getTransactionID(), order.retrieveProductName(), order.getOrderDate(), order.getTotalPrice(), order.getStatus());
            i++;
        }

        System.out.println("(b) Back");
        System.out.println("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        switch(choice) {
            case "b":
                break; //need modify
            default:
                System.out.println("Invalid choice");
        }
    }

    public void adminOrderView() {
        OrderSortType sort;
        String transactionID;
        int choice;
        System.out.println("Welcome to Admin Order Management panel");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select function");
        System.out.println("1. View order list");
        System.out.println("2. Update order status");
        System.out.println("3. Delete order");
        choice = scanner.nextInt();

        switch(choice){
            case 1:
                System.out.println("Sort order list by: ");
                System.out.println("1. Sort by create date in ascending");
                System.out.println("2. Sort by create date in descending");
                System.out.println("3. Sort by price in ascending");
                System.out.println("4. Sort by price in descending");
                System.out.println("5. Sort by TransactionID in ascending");
                System.out.println("6. Sort by TransactionID in descending");
                int sortBy = scanner.nextInt();
                switch(sortBy){
                    case 1:
                        sort = new OrderSortByCreateDate_ASC();
                        break;
                    case 2:
                        sort = new OrderSortByCreateDate_DESC();
                        break;
                    case 3:
                        sort = new OrderSortByPrice_ASC();
                        break;
                    case 4:
                        sort = new OrderSortByPrice_DESC();
                        break;
                    case 5:
                        sort = new OrderSortByTransactionID_ASC();
                        break;
                    case 6:
                        sort = new OrderSortByTransaction_DESC();
                        break;
                    default:
                        sort = new OrderSortByCreateDate_ASC();
                        break;
                }
                controller.displayAdminOrderList(sort);
                break;
            case 2:
                System.out.println("Which order do you want to update?");
                System.out.println("Please enter transactionID: ");
                transactionID = scanner.nextLine().trim();
                controller.updateOrderStatus(transactionID);
            case 3:
                System.out.println("Which order do you want to delete?");
                System.out.println("Please enter transactionID: ");
                transactionID = scanner.nextLine().trim();
                controller.deleteOrder(transactionID);
        }


    }

    public void displayAdminProductList(ArrayList<Order> orderList, OrderSortType sort) {
        System.out.format("%4s|%6s|%25s|%20s|%8s|%12s\n", "No.", "TransactionID", "Product", "Order Date", "Total Price", "Order status");
        int i=1;
        for(Order order : orderList) {
            System.out.format("%6s|%25s|%20s|%12s|%8s|%12s\n", i, order.getTransactionID(), order.retrieveProductName(), order.getOrderDate(), order.getTotalPrice(), order.getStatus());
            i++;
        }

        System.out.println("(b) Back");
        System.out.println("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        switch(choice) {
            case "b":
                break; //need modify
            default:
                System.out.println("Invalid choice");
        }
    }
}