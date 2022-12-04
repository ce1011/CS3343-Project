import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {
    private ProductController controller;
    private CartController cartController;

    private final Scanner sc = new Scanner(System.in);

    public ProductView(ProductController controller) {
        this.controller = controller;
        this.cartController = new CartController();
    }
    public void customerFilter(){
        String name;
        double minPrice;
        double maxPrice;
        ProductSortType sort;
        System.out.println("Enter product name: ");
        name = sc.nextLine();
        System.out.println("Enter min price: ");
        minPrice = sc.nextDouble();
        System.out.println("Enter max price: ");

        maxPrice = sc.nextDouble();
        System.out.println("Enter sort type: ");
        System.out.println("1. Sort by name in ascending");
        System.out.println("2. Sort by name in descending");
        System.out.println("3. Sort by price in ascending");
        System.out.println("4. Sort by price in descending");
        System.out.println("5. Sort by create date in ascending");
        System.out.println("6. Sort by create date in descending");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                sort = new ProductSortByNameAsc();
                break;
            case 2:
                sort = new ProductSortByNameDesc();
                break;
            case 3:
                sort = new ProductSortByPriceAsc();
                break;
            case 4:
                sort = new ProductSortByPriceDesc();
                break;
            case 5:
                sort = new ProductSortByCreateDateAsc();
                break;
            case 6:
                sort = new ProductSortByCreateDateDesc();
                break;
            default:
                sort = new ProductSortByCreateDateAsc();
                break;
        }
        controller.displayCustomerProductList(name, minPrice, maxPrice, sort);
    }

    public void displayCustomerProductList(ArrayList<Product> productList, String name, double minPrice, double maxPrice, ProductSortType sort) {
        System.out.format("%4s|%15s|%8s|%40s|%10s\n", "No.", "Name", "Price", "Description", "Status");
        int i = 1;
        for (Product product : productList) {
            if(!(product.getState() instanceof ProductState_Deleted)){
                System.out.format("%4s|%15s|%8s|%40s|%10s\n", i, product.getName(), product.getPrice(), product.getDescription(), product.getState().toString());

            }
            i++;
        }

        System.out.println("(No) Add To Cart");
        System.out.println("(b) Back");

        System.out.println("Please enter your choice: ");
        String choice = sc.nextLine();

        switch (choice) {
            case "b":
                break;
            default:
                if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= productList.size()) {

                    System.out.println("How many do you want to buy?");
                    int quantity = sc.nextInt();
                    cartController.addProductToCart(productList.get(Integer.parseInt(choice) - 1).getName(), quantity);

                    controller.displayCustomerProductList(name, minPrice, maxPrice, sort);

                } else {
                    System.out.println("Invalid choice!");
                    displayCustomerProductList(productList, name, minPrice, maxPrice, sort);
                }

        }


    }


    public void filter(){
        String name;
        double minPrice;
        double maxPrice;
        ProductSortType sort;
        System.out.println("Enter product name: ");
        name = sc.nextLine();
        System.out.println("Enter min price: ");
        minPrice = sc.nextDouble();
        System.out.println("Enter max price: ");

        maxPrice = sc.nextDouble();
        System.out.println("Enter sort type: ");
        System.out.println("1. Sort by name in ascending");
        System.out.println("2. Sort by name in descending");
        System.out.println("3. Sort by price in ascending");
        System.out.println("4. Sort by price in descending");
        System.out.println("5. Sort by create date in ascending");
        System.out.println("6. Sort by create date in descending");
        int choice = sc.nextInt();
sc.nextLine();
        switch (choice){
            case 1:
                sort = new ProductSortByNameAsc();
                break;
            case 2:
                sort = new ProductSortByNameDesc();
                break;
            case 3:
                sort = new ProductSortByPriceAsc();
                break;
            case 4:
                sort = new ProductSortByPriceDesc();
                break;
            case 5:
                sort = new ProductSortByCreateDateAsc();
                break;
            case 6:
                sort = new ProductSortByCreateDateDesc();
                break;
            default:
                sort = new ProductSortByCreateDateAsc();
                break;
        }
        controller.displayAdminProductList(name, minPrice, maxPrice, sort);
    }

    public void displayAdminProductList(ArrayList<Product> productList, String name, double minPrice, double maxPrice, ProductSortType sort) {
        System.out.format("%4s|%15s|%8s|%40s|%10s\n", "No.", "Name", "Price", "Description", "Status");
        int i = 1;
        for (Product product : productList) {
            if(!(product.getState() instanceof ProductState_Deleted)){
                System.out.format("%4s|%15s|%8s|%40s|%10s\n", i, product.getName(), product.getPrice(), product.getDescription(), product.getState().toString());

            }
            i++;
        }

        System.out.println("(No) Edit Product");
        System.out.println("(+) Add Product");
        System.out.println("(b) Back");

        System.out.println("Please enter your choice: ");
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "+":
                controller.addProductView();
                controller.displayAdminProductList(name, minPrice, maxPrice, sort);
            case "b":
                break;
            default:
                if (Integer.parseInt(choice) > 0 && Integer.parseInt(choice) <= productList.size()) {
                    try {
                        updateProduct(Integer.parseInt(choice) - 1, productList.get(Integer.parseInt(choice) - 1));
                        controller.displayAdminProductList(name, minPrice, maxPrice, sort);
                    } catch (ProductIsDeletedException e) {
                        System.out.println("Product is deleted");
                    }

                } else {
                    System.out.println("Invalid choice!");
                    displayAdminProductList(productList, name, minPrice, maxPrice, sort);
                }

        }


    }

    public void updateProduct(int index, Product product) throws ProductIsDeletedException {
        System.out.println("Update Product");
        System.out.println("Please enter updated product name: ");
        product.setName(sc.nextLine().trim());
        System.out.println("Please enter updated product price: ");
        product.setPrice(sc.nextDouble());
        sc.nextLine();
        System.out.println("Please enter updated product description: ");
        product.setDescription(sc.nextLine().trim());
        System.out.println("Please enter updated product weight: ");
        product.setWeight(sc.nextDouble());
        System.out.println("Please enter updated product quantity: ");
        product.setInStockQuantity(sc.nextInt());
        System.out.println("Please enter updated product status: ");
        System.out.println("1. Launched");
        System.out.println("2. Discontinued");
        System.out.println("3. Delete");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                product.setState(new ProductState_Launch());
                break;
            case 2:
                product.setState(new ProductState_Discontinued());
                break;
            case 3:
                product.setState(new ProductState_Deleted());
                break;
            default:
                System.out.println("Invalid choice!");
                updateProduct(index, product);
        }
        try {
            controller.updateProduct(index, product);
        } catch (Exception e) {

        }


    }

    public void addProductView() {
        String name;
        double price;
        String description;
        int inStockQuantity;
        double weight;

        System.out.println("Please enter the product name: ");
          name = sc.nextLine().trim();
        System.out.println("Please enter the product price: ");
        price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Please enter the product description: ");
        description = sc.nextLine();
        System.out.println("Please enter the product in stock quantity: ");
        inStockQuantity = sc.nextInt();
        System.out.println("Please enter the product weight: ");
        weight = sc.nextDouble();

        try {
            controller.addProduct(name, price, description, inStockQuantity, weight);
            System.out.println("Product added successfully!");

        } catch (ExistingProductWithSameNameFoundException e) {
            System.out.println("Product with same name already exists!");
        }

    }


}
