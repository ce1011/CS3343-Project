
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CartItemTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void getQuantityTest() {
		Product product = new Product("product", 10.0, "description", 10, new ProductState_Launch(), 0.5);
		CartItem item = new CartItem(product,2);
		assertEquals(2,item.getQuantity());
	}
	
	@Test
	void getSubtotalTest() {
		Product product = new Product("product", 10.0, "description", 10, new ProductState_Launch(), 0.5);
		CartItem item = new CartItem(product,2);
		assertEquals(20.0,item.getSubtotal());
	}
	
	
	@Test
	void getProductTest() {
		Product product = new Product("product", 10.0, "description", 10, new ProductState_Launch(), 0.5);
		CartItem item = new CartItem(product,2);
		assertEquals("product",item.getProduct().getName());
	}
	
	@Test
	void setQuantityTest() {
		Product product = new Product("product", 10.0, "description", 10, new ProductState_Launch(), 0.5);
		CartItem item = new CartItem(product,2);
		item.setQuantity(4);
		assertEquals(4,item.getQuantity());
	}
	
	@Test
	void setProductTest() {
		Product product = new Product("product", 10.0, "description", 10, new ProductState_Launch(), 0.5);
		CartItem item = new CartItem(product,2);
		Product newProduct = new Product("product2", 12.0, "description2", 10, new ProductState_Launch(), 0.5);
		item.setProduct(newProduct);
		assertEquals("product2",item.getProduct().getName());
	}

}
