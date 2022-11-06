package main;

public class CartItem {
	private int quantity;
	private Product product;
	
	public CartItem(Product product, int quantity){
		this.product = product;
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public double getSubtotal() {
		return product.getPrice() * this.quantity;
	}
	
	public void setQuantity(int qty) {
		this.quantity = qty;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
