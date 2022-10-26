import java.time.*;

public class Delivery{

    private String deliveryID;
    private String orderID;
    private String zone;
    private String address;
    private double deliveryFee;
    private LocalDateTime createdDate;
    private LocalDateTime estimatedDeliveryDate;
    
    public Delivery(String deliveryID, String orderID, String zone, String address, double deliveryFee){ 
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.zone = zone;
        this.address = address;
        this.deliveryFee = deliveryFee;
        this.createdDate = LocalDateTime.now();
        if(zone=="Hong Kong" || zone=="Kowloon" || zone == "New Territories"){
            this.estimatedDeliveryDate = LocalDateTime.now().plusDays(2);
        }
        else{
            this.estimatedDeliveryDate = LocalDateTime.now().plusDays(3);
        }
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

    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    public double deliveryFee(){
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