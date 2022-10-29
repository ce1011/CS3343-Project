import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSortByNameDescTest {
    private ProductSortByNameDesc productSortByNameDesc = new ProductSortByNameDesc();


    @Test
    void compareWithSameName() {
        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(0, productSortByNameDesc.compare(product1, product2));
    }

    @Test
    void compareWithProductANameEarlierThanProductB() {
        Product product1 = new Product("a", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("b", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(1, productSortByNameDesc.compare(product1, product2));
    }

    @Test
    void compareWithProductANameLaterThanProductB() {
        Product product1 = new Product("b", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("a", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(-1, productSortByNameDesc.compare(product1, product2));
    }
}