public class OrderSortByCreateDate_ASC extends OrderSortType{

    @Override
    public int compare(Order o1, Order o2) {
        return o1.getOrderDate().compareTo(o2.getOrderDate());
    }
}
