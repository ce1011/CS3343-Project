import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductSortByCreateDateDescTest {
    ProductSortByCreateDateDesc productSortByCreateDateDesc = new ProductSortByCreateDateDesc();


    @Test
    void compareWithTwoSameCreateDateProduct() {
        Product product1 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        Product product2 = new Product("name", 12.9, "description", 10, new ProductState_Launch(), 1.2);
        assertEquals(0, productSortByCreateDateDesc.compare(product1, product2));
    }

    @Test
    void compareWithProductACreateEarlierThanProductB() {
        class ProductTestStub extends Product {
            private Date createDate;

            public ProductTestStub(String name, double price, String description, int inStockQuantity, ProductState state, double weight, Date createDate) {
                super(name, price, description, inStockQuantity, state, weight);
                this.createDate = createDate;
            }

            @Override
            public Date getCreateDate() {
                return createDate;
            }
        }
        ProductTestStub product1 = new ProductTestStub("name", 12.9, "description", 10, new ProductState_Launch(), 1.2, new Date(2020, 1, 1));
        ProductTestStub product2 = new ProductTestStub("name", 12.9, "description", 10, new ProductState_Launch(), 1.2, new Date(2020, 1, 2));
        assertEquals(1, productSortByCreateDateDesc.compare(product1, product2));
    }

    @Test
    void compareWithProductACreateLaterThanProductB() {

        class ProductTestStub extends Product {
            private Date createDate;

            public ProductTestStub(String name, double price, String description, int inStockQuantity, ProductState state, double weight, Date createDate) {
                super(name, price, description, inStockQuantity, state, weight);
                this.createDate = createDate;
            }

            @Override
            public Date getCreateDate() {
                return createDate;
            }
        }
        ProductTestStub product1 = new ProductTestStub("name", 12.9, "description", 10, new ProductState_Launch(), 1.2, new Date(2020, Calendar.JANUARY, 2));
        ProductTestStub product2 = new ProductTestStub("name", 12.9, "description", 10, new ProductState_Launch(), 1.2, new Date(2020, Calendar.JANUARY, 1));
        assertEquals(-1, productSortByCreateDateDesc.compare(product1, product2));
    }
}