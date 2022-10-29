import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductSortByNameAscTest {
    ProductSortByNameAsc productSortByNameAsc = new ProductSortByNameAsc();


    @Test
    void compareWithTwoSameNameProduct() {

        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(0, productSortByNameAsc.compare(product1, product2));
    }

    @Test
    void compareWithProductANameSmallerThanProductB() {
        Product product1 = new Product("a", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("b", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(-1, productSortByNameAsc.compare(product1, product2));
    }

    @Test
    void compareWithProductANameLargerThanProductB() {
        Product product1 = new Product("b", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("a", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(1, productSortByNameAsc.compare(product1, product2));
    }
}