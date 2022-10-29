import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductStateLaunchTest {

    @Test
    void testToString() {
        ProductState_Launch launch = new ProductState_Launch();
        assertEquals("Launch", launch.toString());
    }
}