import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderController {

    private OrderView view;

    private OrderService orderService;

    public OrderController(){
        this.view = new OrderView(this);
        this.orderService = OrderService.getOrderServiceInstance();

    }
    public void displayCustomerOrderList(OrderSortType sort){
        ArrayList<Order> allOrderMatchCriteria = orderService.searchOrder(sort);
        view.displayCustomerOrderList(allOrderMatchCriteria, sort);
    }
    public void displayAdminOrderList(OrderSortType sort){

    }



    public void placeOrder(){

    }

    public void updateOrder(){

    }

    public void customerFilterView() {
        view.customerFilter();
    }

    public void displayOrder(int choice, int sortIn){
//        int currentPage = ()
    }

    public void displayOrder(User user, int skip, int limit, OrderSortType sort){
        int currentPage = (skip/limit) + 1;
        int itemPerPage = limit;
        try{
//            ArrayList<Order> orderList = model.searchOrder("user", 0, 99999999, sort);
//            ArrayList<Order> allOrderMatchCriteria = model.searchOrder(user, 0, 99999999, sort);
//            int totalPage = (allOrderMatchCriteria.size()/limit)+1;

            //view.displayOrderList(null, currentPage, totalPage, itemPerPage, sort);
        }catch (IndexOutOfBoundsException e){

        }
    }
}