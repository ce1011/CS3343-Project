public class OrderSortByPrice_ASC extends OrderSortType{

    @Override
    public int compare(Order o1, Order o2) {
        if(o1.getTotalPrice() > o2.getTotalPrice()) {
            return 1;
        }else if (o1.getTotalPrice() < o2.getTotalPrice()) {
            return -1;
        }else {
            return 0;
        }
    }
}
