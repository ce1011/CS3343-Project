import java.time.*;

public class Delivery{

    private String deliveryID;
    private String orderID;
    private String zone;
    private String address;
    private double deliveryFee;
    private LocalDateTime createdDate;
    private LocalDateTime estDeliveryDate;
    private DeliveryState deliveryState;
    
    public Delivery(String deliveryID, String orderID, String zone, String address, double deliveryFee){ 
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.zone = zone;
        this.address = address;
        this.deliveryFee = deliveryFee;
        this.createdDate = LocalDateTime.now();
        if(zone.equals("Hong Kong") || zone.equals("Kowloon")|| zone.equals("New Territories")){
            this.estDeliveryDate = LocalDateTime.now().plusDays(2);
        }
        else{
            this.estDeliveryDate = LocalDateTime.now().plusDays(3);
        }
        this.deliveryState = new DeliveryState_Pending();
    }

    public void setDeliveryState(String deliveryState){
        if(deliveryState.equals("Pending")){
            this.deliveryState = new DeliveryState_Pending();
        }
        else if(deliveryState.equals("Processing")){
            this.deliveryState = new DeliveryState_Processing();
        }
        else if(deliveryState.equals("Dispatching")){
            this.deliveryState = new DeliveryState_Dispatching();
        }
        else if(deliveryState.equals("Delivered")){
            this.deliveryState = new DeliveryState_Delivered();
        }
    }

    public DeliveryState getDeliveryState(){
        return this.deliveryState;
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

    public LocalDateTime getEstDeliveryDate(){
        return this.estDeliveryDate;
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

    public void setEstDeliveryDate(LocalDateTime newDate){
        this.estDeliveryDate = newDate;
    }

}