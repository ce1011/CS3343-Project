import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    private Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
    }

    @Test
    void getName() {
        assertEquals("name", product.getName());
    }

    @Test
    void setName() {
        product.setName("new name");
        assertEquals("new name", product.getName());
    }

    @Test
    void getPrice() {
        assertEquals(12.9, product.getPrice());
    }

    @Test
    void setPrice() {
        product.setPrice(13.9);
        assertEquals(13.9, product.getPrice());
    }

    @Test
    void getDescription() {
        assertEquals("description", product.getDescription());
    }

    @Test
    void setDescription() {
        product.setDescription("new description");
        assertEquals("new description", product.getDescription());
    }

    @Test
    void getInStockQuantity() {
        assertEquals(10, product.getInStockQuantity());
    }

    @Test
    void setInStockQuantity() {
        product.setInStockQuantity(11);
        assertEquals(11, product.getInStockQuantity());
    }

    @Test
    void getState() {
        assertEquals(new ProductState_Launch().toString(), product.getState().toString());
    }



    @Test
    void setState() throws ProductIsDeletedException {
        product.setState(new ProductState_Discontinued());
        assertEquals(new ProductState_Discontinued().toString(), product.getState().toString());
    }

    @Test
    void setStateForDeletedProduct() {
        assertThrows(ProductIsDeletedException.class, () -> {
            product.setState(new ProductState_Deleted());
            product.setState(new ProductState_Discontinued());
        });
    }

    @Test
    void getWeight() {
        assertEquals(1.2, product.getWeight());
    }

    @Test
    void setWeight() {
        product.setWeight(1.3);
        assertEquals(1.3, product.getWeight());
    }

    @Test
    void getCreateDate() {
        assertEquals(new Date().toString(), product.getCreateDate().toString());
    }

}