public class OrderSortByCustomerName_ASC extends OrderSortType{
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getUser().getUsername().compareTo(o2.getUser().getUsername());
    }
}