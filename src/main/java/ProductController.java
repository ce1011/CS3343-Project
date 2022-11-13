import java.util.ArrayList;

public class ProductController {
    private ProductView view;
    private ProductService productService;

    public ProductController() {
        this.view = new ProductView(this);
        this.productService = ProductService.getInstance();
    }

    public void displayProduct(String name, double minPrice, double maxPrice, int skip, int limit, ProductSortType sort) {
        int currentPage = (skip/limit) + 1;
        int itemPerPage = limit;
        try{
           ArrayList<Product> productList = productService.searchProduct(name, minPrice, maxPrice, skip, limit, sort);
           ArrayList<Product> allProductMatchCriteria = productService.searchProduct(name, minPrice, maxPrice, 0, 99999999, sort);
              int totalPage = (allProductMatchCriteria.size()/limit) + 1;

              view.displayProductList(productList, currentPage, totalPage, itemPerPage, name, minPrice, maxPrice, sort);
        }catch (IndexOutOfBoundsException e){

        }
    }


    public void addTempProduct() {
        productService.addTempProduct();
    }
}
