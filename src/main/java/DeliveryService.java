import java.util.ArrayList;


public class DeliveryService{

    private static DeliveryService instance = DeliveryService.getInstance();
    private static ArrayList<Delivery> = new ArrayList<Delivery>();
    private ArrayList<String> deliveryZone = new ArrayList<String>();
    private double deliveryFirstKG_price, deliveryAfterFirstKG_price;
    private int deliveryID = 1;

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

    public void removeDeliveryZone(String zone){
        for (int i=0;i<deliveryZone.size();i++){
            if(deliveryZone.get(i) == zone){
                    deliveryZone.remove(i);
                    break;
            }
        }
    }

    public void createDelivery(String orderID, String zone, String address, double weight){
        for (String zone: deliveryZone){
            if (this.zone == zone){
                Delivery d = new Delivery(deliveryID.toString(), orderID, zone, address, calculateDeliveryPrice(weight,zone));
                deliveryID++;
                break;
            }
        }
    }

    public Delivery getDelivery(String deliveryID){


        return null;
    }

    public void updateDelivery(String deliveryID, String zone, String address, double weight){

    }

    public void deleteDelivery(String deliveryID){
        
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


}