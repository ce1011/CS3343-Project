

import java.util.ArrayList;

public class CartController {
	private CartView view;
	private CartService cartModel;
	private ProductService productModel;
	
	public CartController() {
		view = new CartView(this);
		cartModel = CartService.getInstance();
		productModel = ProductService.getInstance();
	}
	
	public ArrayList<Product> getProducts() {
		return productModel.getProducts();
	}
	
}
