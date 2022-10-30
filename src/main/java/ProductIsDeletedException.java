public class ProductIsDeletedException extends Exception{
    public ProductIsDeletedException() {
        super("The product is deleted.");
    }
}
