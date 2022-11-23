public class OrderSortByPrice_ASC extends OrderSortType{

    @Override
    public int compare(Order o1, Order o2) {
        return Double.compare(Double.parseDouble(o1.getTotalPrice()), Double.parseDouble(o2.getTotalPrice()));
    }
}