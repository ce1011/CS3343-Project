import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderState_ProcessingTest {

    @Test
    void testToString() {
        OrderState_Processing deleted = new OrderState_Processing();
        assertEquals("Processing", deleted.toString());
    }
}
