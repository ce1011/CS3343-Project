public class OrderSortByTransactionID_ASC extends OrderSortType {

    @Override
    public int compare(Order o1, Order o2){
        return o1.getTransactionID().compareTo(o2.getTransactionID());
    }
}
