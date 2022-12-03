public class OrderSortByCustomerName_DESC extends OrderSortType{
    @Override
    public int compare(Order o1, Order o2) {
        return o2.getUser().getUsername().compareTo(o1.getUser().getUsername());
    }
}