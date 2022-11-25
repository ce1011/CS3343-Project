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
        ArrayList<CartItem> cartItem = CartService.getInstance().getCartItems();

        System.out.format("%20s|%5s", "Product Name", "Price");
        double total = 0;
        for(CartItem item : cartItem){
            System.out.format("%20s|%5f", item.getProduct().getName(), item.getSubtotal());
            total += item.getSubtotal();

            if(item.getProduct().getInStockQuantity() < item.getQuantity()){
                System.out.println("Not enough inventory");
                return;
            }


        }

        System.out.format("Total: $%6f", total);





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