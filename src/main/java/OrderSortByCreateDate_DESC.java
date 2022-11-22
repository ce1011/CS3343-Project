public class OrderSortByCreateDate_DESC extends OrderSortType{

    public int compare(Order o1, Order o2) {
        return o2.getOrderDate().compareTo(o1.getOrderDate());
    }
}
