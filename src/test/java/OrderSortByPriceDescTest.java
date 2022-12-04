import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderSortByPriceDescTest {
    private OrderSortByPrice_DESC orderSortByPriceDesc = new OrderSortByPrice_DESC();
    @Test
    void compareWithSamePrice() {
        Order order1  = new Order(null, null, 10000, null);
        Order order2 = new Order(null, null, 10000, null);
        assertEquals(0, orderSortByPriceDesc.compare(order1, order2));
    }

    @Test
    void compareWithOrderAPriceLowerThanOrderB() {
        Order order1  = new Order(null, null, 99, null);
        Order order2 = new Order(null, null, 100, null);
        assertEquals(1, orderSortByPriceDesc.compare(order1, order2));
    }

    @Test
    void compareWithOrderAPriceHigherThanOrderB() {
        Order order1  = new Order(null, null, 100, null);
        Order order2 = new Order(null, null, 99, null);
        assertEquals(-1, orderSortByPriceDesc.compare(order1, order2));
    }
}