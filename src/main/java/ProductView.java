import java.util.ArrayList;
import java.util.Scanner;

public class ProductView {
    private ProductController controller;
    public ProductView(ProductController controller){
        this.controller = controller;
    }

    public void displayProductList(ArrayList<Product> productList, int currentPage, int totalPage, int itemPerPage, String name, double minPrice, double maxPrice, ProductSortType sort){
       System.out.format("%15s|%8s|%40s\n", "Name", "Price", "Description");
         for (Product product : productList){
              System.out.format("%15s|%8s|%40s\n", product.getName(), product.getPrice(), product.getDescription());
         }

        System.out.println("Page " + currentPage + " of " + totalPage);

        if (currentPage > 1){
            System.out.println("(p) Previous");
        }

        if (currentPage < totalPage){
            System.out.println("(n) Next");
        }

        System.out.println("(b) Back");

        System.out.println("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();
        scanner.close();

        switch(choice){
            case "p":
                if (currentPage > 1){
                    controller.displayProduct(name, minPrice, maxPrice, (currentPage - 2) * itemPerPage, itemPerPage, sort);
                }
                break;
            case "n":
                if (currentPage < totalPage){
                    controller.displayProduct(name, minPrice, maxPrice, currentPage * itemPerPage, itemPerPage, sort);
                }
                break;
            case "b":
                break;
            default:
                System.out.println("Invalid choice!");
                displayProductList(productList, currentPage, totalPage, itemPerPage, name, minPrice, maxPrice, sort);
        }




    }

}
