

import java.util.ArrayList;

public class CartController {
	private CartView view;
	private CartService cartModel;

	private OrderController orderController;

	public CartController() {
		view = new CartView(this);
		cartModel = CartService.getInstance();
		orderController = new OrderController();
	}
	
	
	public ArrayList<CartItem> getCart(){
		return cartModel.getCartItems();
	}
	
	public double getTotalPrice() {
		return cartModel.getCurrentPrice();
	}
	
	public void addProductToCart(String name, int qty) {
		try {
			cartModel.addProduct(name, qty);
			view.success();
		} catch(ProductNotFoundException ex) {
			view.productNotFound();
		} catch (OutOfStockException ex) {
			view.outOfStock();
		}
	}

	public void removeProductFromCart(String name){
		try {
			cartModel.removeProductFromCartByName(name);
			view.success();
		} catch (CartItemNotFoundException e) {
			view.itemNotFound();
		}
	}

	public void setCartItemQty(String name, int qty){
		try{
			cartModel.setCartItemQty(name, qty);
			view.success();
		} catch(CartItemNotFoundException e){
			view.itemNotFound();
		} catch (ProductNotFoundException e) {
			view.productNotFound();
		} catch (OutOfStockException e) {
			view.outOfStock();
		}
	}

	public void clearCart(){
		cartModel.clearCart();
		view.success();
	}

	public void placeOrder() throws CouponNotFoundException {
		orderController.placeOrder();
	}
	
	
	
}
