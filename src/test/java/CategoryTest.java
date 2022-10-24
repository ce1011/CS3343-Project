import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void setName() {
        Category category = new Category("test");
        category.setName("test2");
        assertEquals("test2", category.getName());
    }

    @Test
    void getName() {
        Category category = new Category("test");
        assertEquals("test", category.getName());
    }
}