import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartControllerTest {
	
	@BeforeEach
	public void setup() throws ProductNotFoundException, OutOfStockException, ExistingProductWithSameNameFoundException{
		
		ProductService ps = ProductService.getInstance();
		Product p = new Product("nike", 2.0, "shoe", 4, new ProductState_Launch(), 0.5);
		ps.createProduct(p);
		Product p1 = new Product("converse", 2.0, "shoe", 2, new ProductState_Launch(), 0.5);
		ps.createProduct(p1);
		CartService cs = new CartService().getInstance();
		cs.addProduct("nike", 2);
	}
	
	@AfterEach
	public void reset() {
		CartService.resetCartService();
		ProductService.resetProductService();
	}

	@Test
	void getCartTest() {
		CartController cc = new CartController();
		ArrayList<CartItem> result = cc.getCart();
		assertEquals(1,result.size());
	}
	
	
	@Test
	void getTotalPriceTest() {
		CartController cc = new CartController();
		ArrayList<CartItem> result = cc.getCart();
		assertEquals(1,result.size());
	}
	

	@Test
	public void showCartTest() {
		
	    System.setIn(new ByteArrayInputStream("1\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Total Price: $4.0"));
		
	}
	
	
	@Test
	public void showEmptyCartTest() {
		
	    System.setIn(new ByteArrayInputStream("1\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.clearCart();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Your cart is empty!"));
		
	}
	
	@Test
	public void serviceGetCartSizeTest() {
		
		CartService cs = CartService.getInstance();
		assertEquals(1,cs.getCartSize());
		
	}
	
	@Test
	public void serviceRemoveProductTest() {
		CartService cs = CartService.getInstance();
		cs.removeProductFromCart(0);
		assertEquals(null, cs.searchCartItem("nike"));
		
	}
	
	@Test
	public void entryCartViewTest() {
	    System.setIn(new ByteArrayInputStream("7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Shopping Now"));
		
	}
	
	
	@Test
	public void inputMismatchTest() {
	    System.setIn(new ByteArrayInputStream("8\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Invalid choice!"));
		
	}
	
	@Test
	public void addToCartViewTest() {
	    System.setIn(new ByteArrayInputStream("2\nconverse\n1".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Success!"));
		
	}
	
	@Test
	public void serviceAddExistingProductTest() {
	    System.setIn(new ByteArrayInputStream("2\nnike\n1".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Success!"));
		
	}
	
	@Test
	public void addNotFoundProductViewTest() {
	    System.setIn(new ByteArrayInputStream("2\nvood\n1".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Cannot find this product!"));
		
	}
	
	@Test
	public void addOutOfStockProductViewTest() {
	    System.setIn(new ByteArrayInputStream("2\nnike\n10".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("This product is out of stock!"));
		
	}
	
	@Test
	public void setProductQtyTest() throws ProductNotFoundException, OutOfStockException {

	    System.setIn(new ByteArrayInputStream("3\nnike\n2".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Success!"));
		
	}
	
	
	@Test
	public void setNotFoundCartProductQtyTest() throws ProductNotFoundException, OutOfStockException {
	    System.setIn(new ByteArrayInputStream("3\nconverse\n2\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Cannot find this product in your cart"));
		
	}
	
	@Test
	public void setNotFoundProductQtyTest() throws ProductNotFoundException, OutOfStockException {
	    System.setIn(new ByteArrayInputStream("3\nshoe\n2".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Cannot find this product!"));
	}
	
	@Test
	public void setOFSProductQtyTest() throws ProductNotFoundException, OutOfStockException {
	    System.setIn(new ByteArrayInputStream("3\nnike\n10".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("This product is out of stock!"));
	}
	
	
	@Test
	public void delProductTest() {
	    System.setIn(new ByteArrayInputStream("4\nnike\n1".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Success!"));
		
	}
	
	
	@Test
	public void delNotFoundProductTest() {
	    System.setIn(new ByteArrayInputStream("4\nshoe\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Cannot find this product in your cart"));
		
	}
	
	
	@Test
	public void clearCartTest() {
	    System.setIn(new ByteArrayInputStream("5\n1\n1".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Success!"));
		
	}
	
	@Test
	public void invalidClearCartTest() {
	    System.setIn(new ByteArrayInputStream("5\n3\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Invalid choice!"));
		
	}
	
	@Test
	public void notClearCartTest() {
	    System.setIn(new ByteArrayInputStream("5\n2\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Please enter your choice: "));
		
	}
	
	@Test
	public void placeOrderViewTest() {
		ByteArrayInputStream stream1 = new ByteArrayInputStream("6\n1\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("N/A\nHK\nHong Kong".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Place order successfully!"));
	}
	
	@Test
	public void placeOrderfailViewTest() {
		ByteArrayInputStream stream1 = new ByteArrayInputStream("6\n1\n".getBytes());
		ByteArrayInputStream stream2 = new ByteArrayInputStream("N/A\nHK\nchina".getBytes());
		Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
		vector.add(stream1);
		vector.add(stream2);
		Enumeration<ByteArrayInputStream> enumeration = vector.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		System.setIn(sis);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Place order fail"));
	}
	
	@Test
	public void controllerPlaceOrderViewTest() throws CouponNotFoundException {
	    System.setIn(new ByteArrayInputStream("N/A\nHK\nHong Kong".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.placeOrder();
		assertTrue(outContent.toString().contains("Place order successfully!"));
		
	}
	
	@Test
	public void notPlaceOrderTest() {
	    System.setIn(new ByteArrayInputStream("6\n2\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Please enter your choice: "));
		
	}
	
	@Test
	public void invalidPlaceOrderTest() {
	    System.setIn(new ByteArrayInputStream("6\n3\n7".getBytes()));
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		CartController cc = new CartController();
		cc.entryCartView();
		assertTrue(outContent.toString().contains("Invalid choice!"));
		
	}
	

	
	


}
