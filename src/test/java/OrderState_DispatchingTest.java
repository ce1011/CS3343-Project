import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OrderState_DispatchingTest {

    @Test
    void testToString() {
        OrderState_Dispatching deleted = new OrderState_Dispatching();
        assertEquals("Dispatching", deleted.toString());
    }
}
