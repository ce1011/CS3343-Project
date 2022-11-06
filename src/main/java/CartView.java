package main;

import java.util.ArrayList;
import java.util.Scanner;

public class CartView {
	private CartController controller;
	
	public CartView(CartController controller) {
		this.controller = controller;
	}
	
	public void shoppingView() {
		System.out.println("Shopping Now");
		System.out.println("1. Show All Available Products");
		System.out.println("2. Select Products");
		System.out.println("3. Exit");
		System.out.print("Please enter your choice: ");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		scanner.close();
		switch(choice) {
			case 1: //show product
				break;
			case 2: //add product to cart
				break;
			case 3: //exit
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice!");
				shoppingView();
		}
	}
	
	public void showProductView() {
		ArrayList<Product> products = new ArrayList<Product>();
		products = controller.getProducts();
		Scanner scanner = new Scanner(System.in);
		
		int recordPerPage = 5;
		int currentPage = 1;
		int totalPages = (int)Math.ceil(products.size() / 5);	
		int minOfPage = 0;
		int maxOfPage = 5;

		
		do {

			System.out.println("Name  |     Desc     |  Price  | Stock |");

			for(int i=minOfPage;i<maxOfPage;i++) {
				System.out.printf("%s  | %s |  %f  | %d |%n", products.get(i).getName(),products.get(i).getDescription()
						,products.get(i).getPrice(),products.get(i).getInStockQuantity());
			}
			minOfPage+=5;
			maxOfPage+=5;
			currentPage++;
			if (currentPage == totalPages && products.size() % 5 != 0) {
				maxOfPage = minOfPage + (products.size() % 5);
			}
			System.out.println("Input 1 to next page");
		}while(scanner.nextInt()==1);
		
		shoppingView();
	}
	
	public void addToCartView() {
		
	}
	
	public void outOfStock() {
		System.out.println("This product is out of stock!");
		addToCartView();
	}
	
	public void productNotFound() {
		System.out.println("Cannot find this product!");
		addToCartView();
	}
	
	

}
