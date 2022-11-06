package main;

import java.util.ArrayList;

public class CartService {
	private static CartService instance = CartService.getInstance();
	private static ArrayList<CartItem> items = new ArrayList<CartItem>();
	private ProductService productService = ProductService.getInstance();
	
	
	public static CartService getInstance() {
		if(instance == null) {
			instance = new CartService();
		}
		return instance;
	}
	
	public void addProduct(String name, int qty) throws ProductNotFoundException, OutOfStockException {
		Product selected = productService.getProductByName(name);
		CartItem existItem = searchItem(name);
		
		if(existItem!=null) { //item exists in cart
			
			int totalQty = existItem.getQuantity()+qty;
			checkStock(name,totalQty,selected.getInStockQuantity());
			existItem.setQuantity(totalQty);
			
		} else {
			
			checkStock(name,qty,selected.getInStockQuantity());
			CartItem item = new CartItem(selected, qty);
			items.add(item);
			
		}

	}
	
	public int getCartSize() {
		return items.size();
	}
	
	public double getCurrentPrice() {
		double totalPrice=0;
		for (CartItem item : items) {
			totalPrice += item.getSubtotal();
		}
		return totalPrice;
	}
	
	public void clearCart() {
		items.clear();
	}
	
	public void removeProductFromCart(int index) {
		items.remove(index);
	}
	
	public void checkStock(String name, int qty,int stock) throws OutOfStockException{
		if(qty>stock) {
			throw new OutOfStockException(name);
		}
	}
	
	public CartItem searchItem(String name) {
		CartItem found = null;
		for (CartItem item : items) {
			if(item.getProduct().getName().equals(name)) {
				found = item;
			}
		}
		return found;
	}
	
	public static void resetCartService() {
		items = new ArrayList<CartItem>();
	}
}
