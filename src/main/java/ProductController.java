import java.util.ArrayList;

public class ProductController {
    private ProductView view;
    private ProductService productService;


    public ProductController() {
        this.view = new ProductView(this);
        this.productService = ProductService.getInstance();
    }

    public void displayAdminProductList(String name, double minPrice, double maxPrice, ProductSortType sort) {

           ArrayList<Product> allProductMatchCriteria = productService.searchProduct(name, minPrice, maxPrice, sort);

           view.displayAdminProductList(allProductMatchCriteria, name, minPrice, maxPrice, sort);

    }

    public void displayCustomerProductList(String name, double minPrice, double maxPrice, ProductSortType sort) {

        ArrayList<Product> allProductMatchCriteria = productService.searchProduct(name, minPrice, maxPrice, sort);

        view.displayCustomerProductList(allProductMatchCriteria, name, minPrice, maxPrice, sort);

    }

    public void addProduct(String name, double price, String description, int inStockQuantity, double weight) throws ExistingProductWithSameNameFoundException {
        Product product = new Product(name, price, description, inStockQuantity, new ProductState_Launch(), weight);
        try{
            productService.createProduct(product);

        }catch (ExistingProductWithSameNameFoundException exception){
            throw new ExistingProductWithSameNameFoundException(name);
        }
    }

    public void updateProduct(int index, Product product) throws ProductNotFoundException, ProductIsDeletedException {
        productService.updateProduct(index, product);
    }

    public void addProductView(){
        view.addProductView();
    }

    public void filterView(){
        view.filter();
    }
    public void customerFilterView(){
        view.customerFilter();
    }

}
