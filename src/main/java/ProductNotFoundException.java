public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String name) {
        super(name + " is not found.");
    }
}
