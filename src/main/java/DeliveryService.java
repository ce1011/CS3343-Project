import java.io.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class DeliveryService{

    private static DeliveryService instance = DeliveryService.getInstance();
    private static ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
    private static ArrayList<String> deliveryZone = new ArrayList<String>();
    private static double deliveryFirstKG_price, deliveryAfterFirstKG_price;
    private int deliveryID = 10001;

    public static DeliveryService getInstance() {
        if (instance == null) {
            deliveryZone.add("Hong Kong");
            deliveryZone.add("Kowloon");
            deliveryZone.add("New Territories");
            deliveryZone.add("Island District");
            deliveryFirstKG_price = 25;
            deliveryAfterFirstKG_price = 10;
            instance = new DeliveryService();
        }
        return instance;
    }

    public void updateDeliveryPrice(double deliveryFirstKG_price, double deliveryAfterFirstKG_price){
        this.deliveryFirstKG_price = deliveryFirstKG_price;
        this.deliveryAfterFirstKG_price = deliveryAfterFirstKG_price;
    }

    public void removeDeliveryZone(String zone){
        for (int i=0;i<deliveryZone.size();i++){
            if(deliveryZone.get(i) == zone){
                    deliveryZone.remove(i);
                    break;
            }
        }
    }

    public void createDelivery(String orderID, String zone, String address, double weight){
        for (String dZone: deliveryZone){
            if (dZone == zone){
                Delivery d = new Delivery(Integer.toString(deliveryID), orderID, zone, address, calculateDeliveryPrice(weight,zone));
                deliveryID++;
                break;
            }
        }
    }

    public Delivery getDelivery(String deliveryID){
        for (Delivery d: deliveryList){
            if(deliveryID == d.getDeliveryID()){
                return d;
            }
        }
        return null;
    }

    public void updateDelivery(String deliveryID, String orderID, String zone, String address, double weight){
        if(getDelivery(deliveryID) != null){
            getDelivery(deliveryID).setOrderID(orderID);
            getDelivery(deliveryID).setZone(zone);
            getDelivery(deliveryID).setAddress(address);
            getDelivery(deliveryID).setDeliveryFee(calculateDeliveryPrice(weight,zone));
        }
    }

    public void deleteDelivery(String deliveryID){
        if(getDelivery(deliveryID) != null){
            deliveryList.remove(getDelivery(deliveryID));
        }
    }

    public double calculateDeliveryPrice(double weight, String zone){
        if(zone == "Hong Kong"){
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price + 10;
        }
        else if(zone == "Kowloon"){
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price;
        }
        else if(zone == "New Territories"){
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price + 30;
        }
        else{
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price + 60;
        }
    }

    public void printDeliveryDetails(String deliveryID){
        System.out.println("Delivery ID   Order ID    Zone                 Address                                  Delivery Fee\n");
        System.out.printf("%13s %11s %20s %40s %12d", getDelivery(deliveryID).getDeliveryID(), getDelivery(deliveryID).getOrderID(),
        getDelivery(deliveryID).getZone(), getDelivery(deliveryID).getAddress(), getDelivery(deliveryID).getDeliveryFee());
    }




}