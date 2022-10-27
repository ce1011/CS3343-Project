import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {

    @Test
    void testToString() {
        Launch launch = new Launch();
        assertEquals("Launch", launch.toString());
    }
}