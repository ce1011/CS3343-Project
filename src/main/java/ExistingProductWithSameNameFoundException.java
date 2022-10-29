public class ExistingProductWithSameNameFoundException extends Exception {
    public ExistingProductWithSameNameFoundException(String name) {
        super("Product with name " + name + " already exists");
    }
}
