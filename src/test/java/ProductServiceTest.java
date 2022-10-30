import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @AfterEach
    void resetProductService() {
        ProductService.resetProductService();
    }

    @BeforeEach
    void setupTest() throws ExistingProductWithSameNameFoundException {
        ProductService.getInstance().createProduct(new Product("productA", 12.9, "description", 10, new ProductState_Launch(), 1.2));
        ProductService.getInstance().createProduct(new Product("productB", 13.9, "description", 10, new ProductState_Launch(), 1.2));
        ProductService.getInstance().createProduct(new Product("productC", 14.9, "description", 10, new ProductState_Launch(), 1.2));
        ProductService.getInstance().createProduct( new Product("productDelete", 14.9, "description", 10, new ProductState_Deleted(), 1.2));
    }

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
    void createProductWithoutSameName() {
        ProductService productService = ProductService.getInstance();
        Product product = new Product("productD", 14.9, "description", 10, new ProductState_Launch(), 1.2);
        try {
            productService.createProduct(product);
        } catch (ExistingProductWithSameNameFoundException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    void createProductWithSameName() {
        ProductService productService = ProductService.getInstance();
        Product product = new Product("productA", 14.9, "description", 10, new ProductState_Launch(), 1.2);
        assertThrows(ExistingProductWithSameNameFoundException.class, () -> productService.createProduct(product));
    }

    @Test
    void removeProduct() {
        ProductService productService = ProductService.getInstance();
        try {
            productService.removeProduct("productA");
        } catch (ProductNotFoundException | ProductIsDeletedException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    void removeProductNotFound() {
        ProductService productService = ProductService.getInstance();
        assertThrows(ProductNotFoundException.class, () -> productService.removeProduct("productNotFound"));
    }

    @Test
    void getProduct() {
        ProductService productService = ProductService.getInstance();
        try {
            Product product = productService.getProduct(1);
            assertEquals("productB", product.getName());
        } catch (ProductNotFoundException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    void getProductWhichIsDelete() {
        ProductService productService = ProductService.getInstance();
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(3));
    }

    @Test
    void getProductIndexOutOfBounds() {
        ProductService productService = ProductService.getInstance();
        assertThrows(IndexOutOfBoundsException.class, () -> productService.getProduct(4));
    }
    @Test
    void getProductByName() {
        ProductService productService = ProductService.getInstance();
        try {
            Product product = productService.getProductByName("productA");
            assertEquals("productA", product.getName());
        } catch (ProductNotFoundException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    void getProductByNameNotFound() {
        ProductService productService = ProductService.getInstance();
        assertThrows(ProductNotFoundException.class, () -> productService.getProductByName("productNotFound"));
    }

    @Test
    void getProducts() {
        ProductService productService = ProductService.getInstance();
        ArrayList<Product> products = productService.getProducts();
        assertEquals(4, products.size());
    }

    @Test
    void searchProduct() {
        ProductService productService = ProductService.getInstance();
        ArrayList<Product> products = productService.searchProduct("product",0,999,0,10,new ProductSortByNameDesc());
        assertEquals("productC", products.get(0).getName());
        assertEquals("productB", products.get(1).getName());
        assertEquals("productA", products.get(2).getName());
    }

    @Test
    void searchProductWithOneResultExpected() {
        ProductService productService = ProductService.getInstance();
        ArrayList<Product> products = productService.searchProduct("A",0,999,0,10,new ProductSortByNameDesc());
        assertEquals(1, products.size());
    }

    @Test
    void searchProductWithLimitTwoResultExpected() {
        ProductService productService = ProductService.getInstance();
        ArrayList<Product> products = productService.searchProduct("product",0,999,0,2,new ProductSortByNameDesc());
        assertEquals(2, products.size());
    }

    @Test
    void getProductsSize() {
        ProductService productService = ProductService.getInstance();
        assertEquals(4, productService.getProductsSize());
    }
}