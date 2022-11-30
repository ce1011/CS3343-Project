public class OrderSortByPrice_DESC extends OrderSortType{

    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(Double.parseDouble(o2.getTotalPrice()), Double.parseDouble(o1.getTotalPrice()));
    }
}