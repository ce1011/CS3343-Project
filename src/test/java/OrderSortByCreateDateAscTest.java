import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderSortByCreateDateAscTest {

    @Test
    void compareWithOrderASameAsOrderB() {

        OrderSortByCreateDate_ASC orderSortByCreateDateAsc = new OrderSortByCreateDate_ASC();
        Order order1 = new Order(null, null, 10000, null);
        Order order2 = new Order(null, null, 10000, null);
        assertEquals(0, orderSortByCreateDateAsc.compare(order1, order2));

    }

    @Test
    void compareWithOrderACreateEarlierThanOrderB() {
        try {
            OrderSortByCreateDate_ASC orderSortByCreateDateAsc = new OrderSortByCreateDate_ASC();
            Order order1 = new Order(null, null, 10000, null);
            Thread.sleep(1000);
            Order order2 = new Order(null, null, 10000, null);
            assertEquals(-1, orderSortByCreateDateAsc.compare(order1, order2));
        } catch (InterruptedException e) {

        }
    }

    @Test
    void compareWithOrderALaterThanOrderB() {
        try {
            OrderSortByCreateDate_ASC orderSortByCreateDateAsc = new OrderSortByCreateDate_ASC();
            Order order1 = new Order(null, null, 10000, null);
            Thread.sleep(1000);
            Order order2 = new Order(null, null, 10000, null);
            assertEquals(1, orderSortByCreateDateAsc.compare(order2, order1));
        } catch (InterruptedException e) {

        }
    }


}