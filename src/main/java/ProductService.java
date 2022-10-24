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

    public void createProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.forEach((p) -> {
            if (p.getName().equals(product.getName())) {
                p.setState(new Deleted());
            }
        });
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> searchProduct(String name, double price, int skip, int limit, ProductSortType sort) {
        ArrayList<Product> result = new ArrayList<Product>();
        products.forEach((p) -> {
            if (p.getName().equals(name) && p.getPrice() == price) {
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
