import java.util.ArrayList;

public class OrderController {

    private OrderView view;

    private OrderService model;

    public OrderController(){
        this.view = new OrderView(this);
        this.model = OrderService.getOrderServiceInstance();

    }

    public void entry() {
        view.entryView();
    }

    public void displayOrder(int choice, int sortIn){
//        int currentPage = ()
    }

//    public void displayOrder(String transactionID, int skip, int limit, OrderSortType sort){
//        int currentPage = (skip/limit) + 1;
//        int itemPerPage = limit;
//        try{
//            //ArrayList<Order> orderList = orderService.searchOrder(transactionID, 0, 99999999, sort);
//            ArrayList<Order> allOrderMatchCriteria = orderService.searchOrder(transactionID, 0, 99999999, sort);
//            int totalPage = (allOrderMatchCriteria.size()/limit)+1;
//
//            view.displayOrderList(orderList, currentPage, totalPage, itemPerPage, transactionID, sort);
//        }catch (IndexOutOfBoundsException e){
//
//        }
//    }
}
