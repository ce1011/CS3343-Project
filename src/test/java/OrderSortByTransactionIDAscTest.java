import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderSortByTransactionIDAscTest {
    OrderSortByTransactionID_ASC orderSortByTransactionIDAsc = new OrderSortByTransactionID_ASC();

    @Test
    void compareWithProductANameSmallerThanProductB() {
        Order order1 = new Order(null, null, 1000, null);
        Order order2 = new Order(null, null, 1000, null);
        assertEquals(-1, orderSortByTransactionIDAsc.compare(order1, order2));
    }

    @Test
    void compareWithTransactionIDOrderALargerThanOrderB() {
        Order order1 = new Order(null, null, 1000, null);
        Order order2 = new Order(null, null, 1000, null);
        assertEquals(1, orderSortByTransactionIDAsc.compare(order2, order1));
    }
}