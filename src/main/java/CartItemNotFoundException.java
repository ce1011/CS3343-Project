public class CartItemNotFoundException extends Exception {
    public CartItemNotFoundException(String name) {
        super("Product " + name + " not found from cart");
    }
}
