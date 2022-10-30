import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void getInstance() {
        ProductService productService = ProductService.getInstance();
        assertNotNull(productService);
    }

    @Test
    void confirmOnlyOneInstanceWhenCreateTwoProductService() {
        ProductService productService1 = ProductService.getInstance();
        ProductService productService2 = ProductService.getInstance();
        assertEquals(productService1, productService2);
    }

    @Test
    void createProduct() {
    }

    @Test
    void removeProduct() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void getProductByName() {
    }

    @Test
    void getProducts() {
    }

    @Test
    void searchProduct() {
    }

    @Test
    void getProductsSize() {
    }
}