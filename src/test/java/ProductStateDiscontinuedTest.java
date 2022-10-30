import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductStateDiscontinuedTest {

    @Test
    void testToString() {
        ProductState_Discontinued discontinued = new ProductState_Discontinued();
        assertEquals("Discontinued", discontinued.toString());
    }
}