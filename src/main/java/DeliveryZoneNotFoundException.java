public class DeliveryZoneNotFoundException extends Exception {
    public DeliveryZoneNotFoundException(String zone){
        super("Zone: "+zone+" is not found.");
    }
}
