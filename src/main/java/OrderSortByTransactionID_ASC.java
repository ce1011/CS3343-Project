public class OrderSortByTransactionID_ASC extends OrderSortType{
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.getTransactionID_numerical() > o2.getTransactionID_numerical()) {
            return 1;
        } else if (o1.getTransactionID_numerical() < o2.getTransactionID_numerical()) {
            return -1;
        } else {
            return 0;
        }
    }
}
