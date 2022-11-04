import java.time.*;

public class Delivery{

    private String deliveryID;
    private String orderID;
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

    public String getDeliveryID(){
        return this.deliveryID;
    }

    public String getOrderID(){
        return this.orderID;
    }

    public String getZone(){
        return this.zone;
    }

    public String getAddress(){
        return this.address;
    }

    public double getDeliveryFee(){
        return this.deliveryFee;
    }

    public void setOrderID(String orderID){
        this.orderID = orderID;
    }
 
    public void setZone(String zone){
        this.zone = zone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setDeliveryFee(double deliveryFee){
        this.deliveryFee = deliveryFee;
    }

}