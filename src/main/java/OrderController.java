import java.util.ArrayList;

public class OrderController {

    private OrderView view;

    private OrderService orderService;

    public OrderController(){
        this.view = new OrderView(this);
        this.orderService = OrderService.getOrderServiceInstance();

    }
    public void displayCustomerOrderList(User user, OrderSortType sort){
        ArrayList<Order> allOrderMatchCriteria = orderService.searchOrder(user, sort);
        view.displayCustomerOrderList(allOrderMatchCriteria, user, sort);
    }
    public void displayAdminOrderList(OrderSortType sort){
        ArrayList<Order> allOrderMatchCriteria = orderService.searchOrder(sort);

        view.displayAdminProductList(allOrderMatchCriteria, sort);
    }

    public void placeOrder(){

    }
    public void customerOrderView(User user) {
        view.customerOrderView(user);
    }

    public void adminOrderView() {
        view.adminOrderView();
    }

    public void updateOrderStatus(String transactionID) {
    }

    public void deleteOrder(String transactionID) {
    }
}