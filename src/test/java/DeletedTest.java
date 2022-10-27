import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeletedTest {

    @Test
    void testToString() {
        Deleted deleted = new Deleted();
        assertEquals("Deleted", deleted.toString());
    }
}