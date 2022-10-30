import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance = ProductService.getInstance();
    private static ArrayList<Product> products = new ArrayList<Product>();

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void createProduct(Product product) throws ExistingProductWithSameNameFoundException {
        try{
            Product founded = this.getProductByName(product.getName());
            throw new ExistingProductWithSameNameFoundException(product.getName());
        }catch (ProductNotFoundException e){
            products.add(product);
            return;
        }
    }

    public void removeProduct(String productName) throws ProductIsDeletedException, ProductNotFoundException {
        Product product = this.getProductByName(productName);

        if (product.getState() instanceof ProductState_Deleted) {
            throw new ProductIsDeletedException();
        }

        product.setState(new ProductState_Deleted());

    }

    public Product getProduct(int index) throws ProductNotFoundException {

        Product product =  products.get(index);

        if(product.getState().toString().equals("Deleted")){
            String message = "Product with index " + index ;
            throw new ProductNotFoundException(message);
        }

        return product;
    }

    public Product getProductByName(String name) throws ProductNotFoundException {

            for (Product product : products) {
                if (product.getName().equals(name) && (product.getState() instanceof ProductState_Launch || product.getState() instanceof ProductState_Discontinued)) {
                    return product;
                }
            }
            throw new ProductNotFoundException(name);

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> searchProduct(String name, double minPrice, double maxPrice, int skip, int limit, ProductSortType sort) {
        ArrayList<Product> result = new ArrayList<Product>();
        products.forEach((p) -> {
            if (p.getName().equals(name) && p.getPrice() >= maxPrice && p.getPrice() <= minPrice && p.getState() instanceof ProductState_Launch) {
                result.add(p);
            }
        });

        result.sort(sort);


        List<Product> skippedProductList = result.subList(skip, limit);
        return new ArrayList<Product>(skippedProductList);
    }

    public int getProductsSize(){
        return products.size();
    }

}
