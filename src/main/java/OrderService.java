import java.util.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class OrderService {

    private int nextTransactionID;
    private ArrayList<Order> centralOrderList;
    private static Date serviceTimeStamp;
    private static OrderService orderService = new OrderService();


    private OrderService() {
        centralOrderList = new ArrayList<Order>();
        nextTransactionID = 100000;
    }

    public static OrderService getOrderServiceInstance() {
        return orderService;
    }

    public void placeOrder(Order order) {
        centralOrderList.add(order);
    }

    public ArrayList<Order> searchOrder(User user, OrderSortType sort) {
        ArrayList<Order> result = new ArrayList<Order>();

        for (Order o : centralOrderList) {
            if (o.getUser().getUsername().equals(Store.getInstance().getCurrentUser().getUsername())) {
                result.add(o);
            }
        }

        result.sort(sort);

        List<Order> skippedOrderList = result.stream().toList();
        return new ArrayList<Order>(skippedOrderList);
    }

    public ArrayList<Order> searchOrder(OrderSortType sort) {
        ArrayList<Order> result = new ArrayList<Order>();

        for (Order o : centralOrderList) {
            result.add(o);
        }

        result.sort(sort);

        return result;
    }

    //TransactionID is 6 digit start from 1XXXXX
    public String assignTransactionID() {
        String assignedID = String.valueOf(nextTransactionID + 1);
        nextTransactionID++;
        return assignedID;
    }

    //order service date and time method
    public static String getCurrentTimestamp() {
        serviceTimeStamp = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ft.format(serviceTimeStamp);

    }

    public Order searchOrderByTransactionID(String transactionID) {
        Order result = null;
        for (Order o : centralOrderList) {
            if (o.getTransactionID().equals(transactionID)) {
                return o;
            }
        }
        return result;
    }

    public void deleteOrder(String transactionID) {
        Order result = null;
        for(Order o: centralOrderList){
            if(o.getTransactionID().equals(transactionID)){
                result = o;
            }
        }
        centralOrderList.remove(result);
    }

    public void updateOrderStatus(String transactionID, OrderState orderState) {
        for(Order o: centralOrderList){
            if(o.getTransactionID().equals(transactionID)){
                o.setStatus(orderState);
            }
        }
    }

    public Map<String, DoubleSummaryStatistics> getCentralOrderList(){
        return centralOrderList.stream().collect(Collectors.groupingBy(Order::getOrderDateYYYYMMDD, Collectors.summarizingDouble(Order::getTotalPriceDouble)));
    }
}
