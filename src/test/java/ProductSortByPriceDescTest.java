import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSortByPriceDescTest {
    private ProductSortByPriceDesc productSortByPriceDesc = new ProductSortByPriceDesc();
    @Test
    void compareWithSamePrice() {
        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(0, productSortByPriceDesc.compare(product1, product2));
    }

    @Test
    void compareWithProductAPriceHigherThanProductB() {
        Product product1 = new Product("name", 13.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(-1, productSortByPriceDesc.compare(product1, product2));
    }

    @Test
    void compareWithProductAPriceLowerThanProductB() {
        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 13.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(1, productSortByPriceDesc.compare(product1, product2));
    }
}