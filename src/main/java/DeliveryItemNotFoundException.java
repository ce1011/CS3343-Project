public class    DeliveryItemNotFoundException extends Exception {
    public DeliveryItemNotFoundException(String deliveryID) {
        super("Delivery Item: " + deliveryID + " is not found.");
    }
}
