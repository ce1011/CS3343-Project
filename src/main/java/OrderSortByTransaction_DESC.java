public class OrderSortByTransaction_DESC extends OrderSortType {

    @Override
    public int compare(Order o1, Order o2){
        return o2.getTransactionID().compareTo(o1.getTransactionID());
    }
}
