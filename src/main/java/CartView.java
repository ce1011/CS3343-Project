

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CartView {
	Scanner sc = new Scanner(System.in);
	private CartController controller;
	
	public CartView(CartController controller) {
		this.controller = controller;
	}
	
	public void shoppingView() {
		System.out.println("Shopping Now");
		System.out.println("1. Show Current Cart");
		System.out.println("2. Select Products");
		System.out.println("3. Set Product Quantity");
		System.out.println("4. Delete Product");
		System.out.println("5. Clear Cart");
		System.out.println("6. Place Order");
		System.out.println("7. Exit");
		System.out.print("Please enter your choice: ");
		
		int choice = -1;
		choice = sc.nextInt();
		
		
		switch(choice) {
			case 1: //show cart
				showCartView();
				
				break;
			case 2: //add product to cart
				addToCartView();
				shoppingView();
				break;
			case 3: //set product quantity
				setProductQtyView();
				break;
			case 4: //delete product from cart
				deleteProductView();
				
				break;
			case 5: //clear cart
				clearCartView();
				break;
			case 6: //place order
				placeOrderView();
				break;
			case 7: //exit
				break;
			default:
				System.out.println("Invalid choice!");
				finishLine();
				shoppingView();
		}
		//scanner.close();
	}


	public void setProductQtyView() {
		
		System.out.print("Please Enter the product name: ");
		String name = sc.next();
		System.out.print("Set the Quantity: ");
		int qty = sc.nextInt();
		controller.setCartItemQty(name, qty);
		//sc.close();
	}

	public void deleteProductView(){
		
		System.out.println("Please Enter the product name to delete from cart: ");
		String name = sc.next();
		controller.removeProductFromCart(name);
		//sc.close();
	}

	public void clearCartView(){
		
		System.out.println("Are you sure to clear your cart?");
		System.out.println("1. Confirm");
		System.out.println("2. No");
		System.out.print("Please enter your choice: ");
		int choice = -1;
		choice = sc.nextInt();
		switch(choice){
			case 1:
				controller.clearCart();
				break;
			case 2:
				finishLine();
				shoppingView();
				break;
			default:
				resultLine();
				System.out.println("Invalid choice!");
				finishLine();
				shoppingView();
		}
		//sc.close();
	}

	public void placeOrderView(){
		
		System.out.println("Are you sure to place your order?");
		System.out.println("1. Confirm");
		System.out.println("2. No");
		System.out.print("Please enter your choice: ");
		int choice = -1;
		choice = sc.nextInt();
		switch(choice){
			case 1:
				processToOrderView();
				break;
			case 2:
				finishLine();
				shoppingView();
				break;
			default:
				System.out.println("Invalid choice!");
				finishLine();
				shoppingView();
		}
		//sc.close();
	}

	private void processToOrderView() {
		try{
			controller.placeOrder();
		}catch (Exception e){
			System.out.println("Place order fail");
		}

	}

	public void showCartView() {
		ArrayList<CartItem> items = new ArrayList<CartItem>();
		items = controller.getCart();
		
		resultLine();
		if(items.size()>0) {
			System.out.printf("%-10s|%-10s|%-10s|%-10s|%n", "Name","Quantity","Price","Subtotal");
			for(int i=0;i<items.size();i++) {
				System.out.printf("%-10s|%-10s|$%-9s|$%-9s|%n", 
				items.get(i).getProduct().getName(), 
				Integer.toString(items.get(i).getQuantity()), 
				Double.toString(items.get(i).getProduct().getPrice()),
				Double.toString(items.get(i).getSubtotal()));
			}
				
			System.out.println("Total Price: $" + controller.getTotalPrice());
			
		} else {
			System.out.println("Your cart is empty!");
		}
		finishLine();
		shoppingView();
	}
	
	public void addToCartView() {
		
		System.out.print("Please Enter the product name: ");
		String name = sc.next();
		System.out.print("Please Enter the quantity: ");
		int qty = sc.nextInt();
		controller.addProductToCart(name, qty);
		
	}

	public void resultLine(){
		String line = new String(new char[48]).replace('\0', '-');
		System.out.println(line);
		System.out.println("Result:");
	}

	public void finishLine(){
		String line = new String(new char[48]).replace('\0', '-');
		System.out.println(line);
		
	}

	public void success(){
		resultLine();
		System.out.println("Success!");
		finishLine();
	}

	public void itemNotFound(){
		resultLine();
		System.out.println("Cannot find this product in your cart");
		finishLine();
		shoppingView();
	}
	

	
	
	public void outOfStock() {
		resultLine();
		System.out.println("This product is out of stock!");
		finishLine();
	}
	
	public void productNotFound() {
		resultLine();
		System.out.println("Cannot find this product!");
		finishLine();
	}
	
	
}
