import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.Scanner;

public class OrderView {
    Scanner scanner = new Scanner(System.in);
    private OrderController controller;

    public OrderView(OrderController controller){
        this.controller = controller;
    }

    public void customerOrderView(User user) {
        OrderSortType sort = null;
        System.out.println("Welcome to Order list panel");
        System.out.println("Sort order list by");
        System.out.println("1. By create date in Ascending order");
        System.out.println("2. By create date in Descending order");
        System.out.println("3. By Price in Ascending order");
        System.out.println("4. By Price in Descending order");
        System.out.println("5. By TransactionID in Ascending order");
        System.out.println("6. By TransactionID in Descending order");
        System.out.println("7. Return");
        System.out.println("Your choice: ");
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
                sort = new OrderSortByTransactionID_DESC();
                break;
            case 7:
                break;
            default:
                System.out.println("Invalid choice!");
        }
        controller.displayCustomerOrderList(user, sort);
        return;

    }

    public void displayCustomerOrderList(ArrayList<Order> orderList, User user, OrderSortType sort){
        System.out.format("%4s|%15s|%50s|%25s|%12s|%12s\n", "No.", "TransactionID", "Product", "Order Date", "Total Price", "Order status");
        int i=1;
        for(Order order : orderList) {
            System.out.format("%4s|%15s|%50s|%25s|%12s|%12s\n", i, order.getTransactionID(), order.retrieveProductName(), order.getOrderDate(), order.getTotalPrice(), order.getStatus());
            i++;
        }

        System.out.println("(b) Back");
        System.out.println("Please enter your choice: ");
        //Scanner scanner = new Scanner(System.in);
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
        Order result = null;
        System.out.println("Welcome to Admin Order Management panel");
        //Scanner scanner = new Scanner(System.in);
        System.out.println("1. View order list");
        System.out.println("2. Update order status");
        System.out.println("3. Delete order");
        System.out.println("4. Return");
        System.out.println("Please select function: ");
        choice = scanner.nextInt();

        switch(choice){
            case 1:
                System.out.println("1. Sort by create date in ascending");
                System.out.println("2. Sort by create date in descending");
                System.out.println("3. Sort by price in ascending");
                System.out.println("4. Sort by price in descending");
                System.out.println("5. Sort by TransactionID in ascending");
                System.out.println("6. Sort by TransactionID in descending");
                System.out.println("7. Return");
                System.out.println("Your choice: ");
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
                        sort = new OrderSortByTransactionID_DESC();
                        break;
                    case 7:
                        adminOrderView();
                    default:
                        sort = new OrderSortByCreateDate_ASC();
                        break;
                }
                controller.displayAdminOrderList(sort);
                break;
            case 2:
                System.out.println("Which order do you want to update?");
                System.out.println("Please enter transactionID: ");
                scanner.next();
                transactionID = scanner.nextLine();
                System.out.println("State: (Pending / Processing / Dispatching / Delivered)");
                System.out.println("Please enter the new state");
                String newStatus = scanner.nextLine();
                controller.updateOrderStatus(transactionID, newStatus);
                System.out.println("Update Order Successfully!");
                break;
            case 3:
                System.out.println("Which order do you want to delete?");
                System.out.println("Please enter transactionID: ");
                scanner.next();
                transactionID = scanner.nextLine().trim();
                controller.deleteOrder(transactionID);
                System.out.println("Delete Order Successfully!");
                break;
            case 4:
                return;
            default:
                System.out.println("---Please choose again---");
                adminOrderView();
        }
        adminOrderView();
    }

    public void displayAdminProductList(ArrayList<Order> orderList, OrderSortType sort) {
        System.out.format("%4s|%15s|%50s|%25s|%12s|%12s\n", "No.", "TransactionID", "Product", "Order Date", "Total Price", "Order status");
        int i=1;
        for(Order order : orderList) {
            System.out.format("%4s|%15s|%50s|%25s|%12s|%12s\n", i, order.getTransactionID(), order.retrieveProductName(), order.getOrderDate(), order.getTotalPrice(), order.getStatus());
            i++;
        }

        System.out.println("(b) Back");
        System.out.println("Please enter your choice: ");
        //Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        switch(choice) {
            case "b":
                break; //need modify
            default:
                System.out.println("Invalid choice");
        }
    }
    public void displaySummaryReport(Map<String, DoubleSummaryStatistics> data){
        System.out.format("%15s|%10s\n","Date","Sum");
        for (Map.Entry<String, DoubleSummaryStatistics> entry : data.entrySet()) {
            System.out.format("%15s|%10f\n",entry.getKey(),entry.getValue().getSum());
        }
    }


}

