import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductStateDeletedTest {

    @Test
    void testToString() {
        ProductState_Deleted deleted = new ProductState_Deleted();
        assertEquals("Deleted", deleted.toString());
    }
}