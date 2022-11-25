import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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

    public void placeOrder() throws CouponNotFoundException {
        ArrayList<CartItem> cartItem = CartService.getInstance().getCartItems();

        System.out.format("%20s|%5s\n", "Product Name", "Price");
        double total = 0;
        for(CartItem item : cartItem){
            System.out.format("%20s|%5f\n", item.getProduct().getName(), item.getSubtotal());
            total += item.getSubtotal();

            if(item.getProduct().getInStockQuantity() < item.getQuantity()){
                System.out.println("Not enough inventory");
                return;
            }


        }

        System.out.format("Total: $%6f\n", total);

        System.out.println("Please enter coupon name if not apply coupon type NA\n");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();

        Coupon coupon;

        double finalPrice =  0;

        if(!name.equals("N/A")){
            coupon = CouponService.getInstance().searchCoupon(name);
            try{
                 finalPrice =  CouponService.getInstance().calculatePrice(total, coupon);
                System.out.format("Final Price: $%7f\n", finalPrice);
            }catch (Exception e){
                System.out.println("Coupon not available");
                        }

        }else{
            coupon = new Coupon("N/A", new Date(), new Date(), "N/A", 999999, 0,0,"N/A");
            finalPrice = total;
        }
        
        

        Order order = new Order(cartItem, coupon, finalPrice, "No");


        DeliveryService deliveryService = DeliveryService.getInstance();
        System.out.println("Please enter your address");
        scanner.nextLine();
        String address = scanner.nextLine();
        System.out.println("Please enter delivery zone");
        String zone = scanner.nextLine();
        Double totalWeight = 0.0;

        //System.out.println(address);
        for(CartItem item: cartItem){
            totalWeight += item.getProduct().getWeight();
        }
        Delivery delivery = null;
        try{
            delivery = deliveryService.createDelivery(order.getTransactionID(), zone, address, totalWeight);
        }catch (DeliveryZoneNotFoundException e){
            e.toString();
        }
        
        order.setTotalPrice(finalPrice+delivery.getDeliveryFee());
        
        OrderService.getOrderServiceInstance().placeOrder(order);

        System.out.println("Place order successfully!");




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