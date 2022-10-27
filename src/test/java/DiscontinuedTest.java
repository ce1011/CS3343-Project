import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscontinuedTest {

    @Test
    void testToString() {
        Discontinued discontinued = new Discontinued();
        assertEquals("Discontinued", discontinued.toString());
    }
}