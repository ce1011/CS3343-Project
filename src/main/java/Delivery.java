public class Delivery{

    private String orderID;
    private Sting deliveryID;
    private String zone;
    private String address;
    private double deliveryFee;
    
    public Delivery(String deliveryID, String orderID, String zone, String address, double deliveryFee){ 
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.zone = zone;
        this.address = address;
        this.deliveryFee = deliveryFee;
    }

    


}