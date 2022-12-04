import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderState_DeliveredTest {

    @Test
    void testToString() {
        OrderState_Delivered deleted = new OrderState_Delivered();
        assertEquals("Delivered", deleted.toString());
    }
}