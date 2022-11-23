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

    public void updateOrderStatus(Order targetOrder, String newStatus) {
        OrderState orderState = null;
        switch (newStatus){
            case "Pending":
                orderState = new OrderState_Pending();
            case "Processing":
                orderState = new OrderState_Processing();
            case "Dispatching":
                orderState = new OrderState_Dispatching();
            case "Delivered":
                orderState = new OrderState_Delivered();
        }
        orderService.updateOrderStatus(targetOrder, orderState);
    }

    public Order searchOrder(String transactionID) {
        return orderService.searchOrderByTransactionID(transactionID);
    }

    public boolean deleteOrder(Order result) {
        boolean deleted;
        deleted = orderService.deleteOrder(result);
        return deleted;
    }
}