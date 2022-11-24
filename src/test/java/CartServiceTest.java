
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

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
		Product product2 = new Product("good product", 5.0, "desc", 4, new ProductState_Launch(), 0.5);
		ProductService.getInstance().createProduct(product);
		ProductService.getInstance().createProduct(product2);
		CartService.getInstance().addProduct("product", 2);
		
	}
	
	@Test
	void removeProductFromCartByNameTest() throws CartItemNotFoundException {
		CartService cartService = CartService.getInstance();
		cartService.removeProductFromCartByName("product");
		assertEquals(0,CartService.getInstance().getCartSize());
	}
	
	@Test
	void getCartItemsTest(){
		CartService cartService = CartService.getInstance();
		ArrayList<CartItem> result =  cartService.getCartItems();
		assertEquals("product",result.get(0).getProduct().getName());
	}
	
	@Test
	void setCartItemQtyTest() throws ProductNotFoundException, OutOfStockException, CartItemNotFoundException{
		CartService cartService = CartService.getInstance();
		cartService.setCartItemQty("product", 2);
		assertEquals(2,cartService.getCartItems().get(0).getQuantity());
	}
	
	@Test
	void setNotExistCartItemQtyTest() throws ProductNotFoundException, OutOfStockException, CartItemNotFoundException{
		CartService cartService = CartService.getInstance();
		
		assertThrows(CartItemNotFoundException.class, () -> cartService.setCartItemQty("good product", 2));
	}
	
	
	@Test
	void removeNoProductByNameTest() throws CartItemNotFoundException {
		CartService cartService = CartService.getInstance();
		
		assertThrows(CartItemNotFoundException.class, () -> cartService.removeProductFromCartByName("good"));
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
		assertEquals(4,CartService.getInstance().searchCartItem("product").getQuantity());
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
		CartItem item = CartService.getInstance().searchCartItem("product");
		assertEquals("product",item.getProduct().getName());
	}

}
