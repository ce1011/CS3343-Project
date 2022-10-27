import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {

    @Test
    void getName() {
        Product product = new Product("name", 12.9, "description", 10, new Available(), 1.2);
        assertEquals("name", product.getName());
    }

    @Test
    void setName() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void setPrice() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void getInStockQuantity() {
    }

    @Test
    void setInStockQuantity() {
    }

    @Test
    void getState() {
    }

    @Test
    void setState() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getCreateDate() {
    }

    @Test
    void compareTo() {
    }
}