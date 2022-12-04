import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderSortByCreateDateDescTest {

    @Test
    void compareWithOrderASameAsOrderB() {

        OrderSortByCreateDate_DESC orderSortByCreateDateDesc = new OrderSortByCreateDate_DESC();
        Order order1 = new Order(null, null, 10000, null);
        Order order2 = new Order(null, null, 10000, null);
        assertEquals(0, orderSortByCreateDateDesc.compare(order1, order2));

    }

    @Test
    void compareWithOrderACreateEarlierThanOrderB() {
        try {
            OrderSortByCreateDate_DESC orderSortByCreateDateDesc = new OrderSortByCreateDate_DESC();
            Order order1 = new Order(null, null, 10000, null);
            Thread.sleep(1000);
            Order order2 = new Order(null, null, 10000, null);
            assertEquals(1, orderSortByCreateDateDesc.compare(order1, order2));
        } catch (InterruptedException e) {

        }
    }

    @Test
    void compareWithOrderALaterThanOrderB() {
        try {
            OrderSortByCreateDate_DESC orderSortByCreateDateDesc = new OrderSortByCreateDate_DESC();
            Order order1 = new Order(null, null, 10000, null);
            Thread.sleep(1000);
            Order order2 = new Order(null, null, 10000, null);
            assertEquals(-1, orderSortByCreateDateDesc.compare(order2, order1));
        } catch (InterruptedException e) {

        }
    }


}