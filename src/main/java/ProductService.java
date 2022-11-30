import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance = ProductService.getInstance();
    private static ArrayList<Product> products = new ArrayList<Product>();

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }


    public void addTempProduct()  {
        for(int i = 0; i< 100; i++) {
            Product product = new Product("Product " + Integer.toString(i),i , "Description " + i, 1000, new ProductState_Deleted(), i);
            products.add(product);
        }
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

        product.setState(new ProductState_Deleted());

    }

    public Product getProduct(int index) throws ProductNotFoundException, IndexOutOfBoundsException {

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

    public ArrayList<Product> searchProduct(String name, double minPrice, double maxPrice, ProductSortType sort) {
        ArrayList<Product> result = new ArrayList<Product>();
        products.forEach((p) -> {
            if (p.getName().contains(name) && p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                result.add(p);
            }
        });

        result.sort(sort);


        List<Product> skippedProductList = result.stream().toList();
        return new ArrayList<Product>(skippedProductList);
    }

    public void updateProduct(int index,Product product) throws ProductNotFoundException, ProductIsDeletedException {
        Product productToUpdate = this.getProduct(index);
        if(product.getState() instanceof ProductState_Deleted){
            throw new ProductIsDeletedException();
        }else{
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setInStockQuantity(product.getInStockQuantity());
            productToUpdate.setWeight(product.getWeight());
            productToUpdate.setState(product.getState());
        }

    }

    public int getProductsSize(){
        return products.size();
    }

    public static void resetProductService(){
        products = new ArrayList<Product>();
    }

}
