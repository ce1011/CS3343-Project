import java.util.ArrayList;
import java.util.List;
//import java.time.format.DateTimeFormatter;

public class DeliveryService{

    private static DeliveryService instance = DeliveryService.getInstance();
    private ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
    private ArrayList<String> deliveryZone = new ArrayList<String>(List.of("Hong Kong", "Kowloon","New Territories","Islands District"));
    private double deliveryFirstKG_price = 25, deliveryAfterFirstKG_price = 10;
    private int deliveryID = 10001;
    //private static DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DeliveryService getInstance() {
        if (instance == null) {
        	instance = new DeliveryService();
        }
        return instance;
    }

    public void updateDeliveryPrice(double deliveryFirstKG_price, double deliveryAfterFirstKG_price){
        this.deliveryFirstKG_price = deliveryFirstKG_price;
        this.deliveryAfterFirstKG_price = deliveryAfterFirstKG_price;
    }
    
    public double getDeliveryFirstKG_price() {
    	return deliveryFirstKG_price;
    }
    
    public double getDeliveryAfterFirstKG_price() {
    	return deliveryAfterFirstKG_price;
    }
    
    public ArrayList<String> getDeliveryZoneList(){
    	return deliveryZone;
    }

    public void removeDeliveryZone(String zone) throws DeliveryZoneNotFoundException{
    	boolean zoneFound = false;
        for (int i=0;i<deliveryZone.size();i++){
            if(deliveryZone.get(i).equals(zone)){
                    deliveryZone.remove(i);
                    zoneFound = true;
                    break;
            }
        }
        if(!zoneFound) {
            throw new DeliveryZoneNotFoundException(zone);
        }
    }

    public Delivery createDelivery(String orderID, String zone, String address, double weight) throws DeliveryZoneNotFoundException{
        for (String dZone: deliveryZone){
            if (dZone.equals(zone)){
                Delivery d = new Delivery(Integer.toString(this.deliveryID), orderID, zone, address, calculateDeliveryPrice(weight,zone));
                deliveryList.add(d);
                this.deliveryID++;
                return d;
            }
        } 
        throw new DeliveryZoneNotFoundException(zone);
    }

    public Delivery getDelivery(String deliveryID) throws DeliveryItemNotFoundException{
        for (Delivery d: deliveryList){
            if(deliveryID.equals(d.getDeliveryID())){
                return d;
            }
        }
        throw new DeliveryItemNotFoundException(deliveryID);
    }

    public void updateDelivery(String deliveryID, String orderID, String zone, String address, double weight)
    throws DeliveryItemNotFoundException, DeliveryZoneNotFoundException{
        boolean zoneExist = false;
        for(String dZone:deliveryZone){
            if(dZone.equals(zone)){
                zoneExist = true;
            }
        }
        
        if(!zoneExist){
            throw new DeliveryZoneNotFoundException(zone);
        }
        getDelivery(deliveryID).setOrderID(orderID);
        getDelivery(deliveryID).setZone(zone);
        getDelivery(deliveryID).setAddress(address);
        getDelivery(deliveryID).setDeliveryFee(calculateDeliveryPrice(weight,zone));
    }
    public void deleteDelivery(String deliveryID) throws DeliveryItemNotFoundException{
            deliveryList.remove(getDelivery(deliveryID));
    }

    public double calculateDeliveryPrice(double weight, String zone){
        if(zone.equals("Hong Kong")){
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price + 10;
        }
        else if(zone.equals("Kowloon")){
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price;
        }
        else if(zone.equals("New Territories")){
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price + 30;
        }
        else{
            return deliveryFirstKG_price + Math.ceil(weight-1) * deliveryAfterFirstKG_price + 60;
        }
    }

    public ArrayList<Delivery> getDeliveryList(){
        return this.deliveryList;
    }

    public void addDeliveryZone(String zone){
        this.deliveryZone.add(zone);
    }

}