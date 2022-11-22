import java.util.Comparator;

public abstract class OrderSortType implements Comparator<Order> {
    public abstract int compare(Order o1, Order o2);
}