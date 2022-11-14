
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartServiceTest {
	
    @AfterEach
    void resetCartService() {
        CartService.resetCartService();
        ProductService.resetProductService();
    }

	@BeforeEach
	void setUp() throws Exception {
		Product product = new Product("product", 5.0, "desc", 4, new ProductState_Launch(), 0.5);
		ProductService.getInstance().createProduct(product);
		CartService.getInstance().addProduct("product", 2);
	}
	
	@Test
	void addProductTest() throws ProductNotFoundException, OutOfStockException {

		assertEquals(1,CartService.getInstance().getCartSize());
	}
	
	@Test
	void addOutOfStockProductTest() throws ProductNotFoundException, OutOfStockException, ExistingProductWithSameNameFoundException {
		CartService cartService = CartService.getInstance();
		Product newProduct = new Product("product2", 5.0, "desc", 1, new ProductState_Launch(), 0.5);
		ProductService.getInstance().createProduct(newProduct);
		assertThrows(OutOfStockException.class, () -> cartService.addProduct("product2", 2));
	}

	@Test
	void addExistedProductTest() throws ProductNotFoundException, OutOfStockException {
		CartService.getInstance().addProduct("product", 2);
		assertEquals(4,CartService.getInstance().searchItem("product").getQuantity());
	}
	
	@Test
	void getCurrentPriceTest(){
		assertEquals(10.0,CartService.getInstance().getCurrentPrice());
	}
	
	@Test
	void clearCartTest(){
		CartService.getInstance().clearCart();
		assertEquals(0,CartService.getInstance().getCartSize());
	}
	
	@Test
	void removeProductTest(){
		CartService.getInstance().removeProductFromCart(0);
		assertEquals(0,CartService.getInstance().getCartSize());
	}
	
	@Test
	void searchItemTest() {
		CartItem item = CartService.getInstance().searchItem("product");
		assertEquals("product",item.getProduct().getName());
	}

}
