import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSortByPriceAscTest {
    private ProductSortByPriceAsc productSortByPriceAsc = new ProductSortByPriceAsc();
    @Test
    void compareWithSamePrice() {
        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(0, productSortByPriceAsc.compare(product1, product2));
    }

    @Test
    void compareWithProductAPriceLowerThanProductB() {
        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 13.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(-1, productSortByPriceAsc.compare(product1, product2));
    }

    @Test
    void compareWithProductAPriceHigherThanProductB() {
        Product product1 = new Product("name", 13.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(1, productSortByPriceAsc.compare(product1, product2));
    }
}