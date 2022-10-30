import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
//test
public class DeliveryService{

    private static DeliveryService instance = DeliveryService.getInstance();
    private static ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
    private static ArrayList<String> deliveryZone = new ArrayList<String>();
    private double deliveryFirstKG_price = 25, deliveryAfterFirstKG_price = 10;
    private int deliveryID = 10001;
    private static DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static DeliveryService getInstance() {
        if (instance == null) {
            deliveryZone.add("Hong Kong");
            deliveryZone.add("Kowloon");
            deliveryZone.add("New Territories");
            deliveryZone.add("Islands District");
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
            if(deliveryZone.get(i).equals(zone)){
                    deliveryZone.remove(i);
                    break;
            }
        }
    }

    public void createDelivery(String orderID, String zone, String address, double weight){
        for (String dZone: deliveryZone){
            if (dZone.equals(zone)){
                Delivery d = new Delivery(Integer.toString(deliveryID), orderID, zone, address, calculateDeliveryPrice(weight,zone));
                deliveryList.add(d);
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

    public void updateDelivery(String deliveryID, String orderID, String zone, String address, double weight, String deliveryState){
        if(getDelivery(deliveryID) != null){
            getDelivery(deliveryID).setOrderID(orderID);
            getDelivery(deliveryID).setZone(zone);
            getDelivery(deliveryID).setAddress(address);
            getDelivery(deliveryID).setDeliveryFee(calculateDeliveryPrice(weight,zone));
            getDelivery(deliveryID).setDeliveryState(deliveryState);
        }
    }

    public void deleteDelivery(String deliveryID){
        if(getDelivery(deliveryID) != null){
            deliveryList.remove(getDelivery(deliveryID));
        }
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

    public void printDeliveryDetails(String deliveryID){
        System.out.println("Delivery ID: "+ getDelivery(deliveryID).getDeliveryID());
        System.out.println("Order ID: "+ getDelivery(deliveryID).getOrderID());
        System.out.println("Zone: "+getDelivery(deliveryID).getZone());
        System.out.println("Address: "+getDelivery(deliveryID).getAddress());
        System.out.println("Delivery Fee: "+getDelivery(deliveryID).getDeliveryFee());
        System.out.println("Estimated Delivery Date: "+getDelivery(deliveryID).getEstDeliveryDate());
        System.out.println("Created Date: "+simpleDateFormat.format(getDelivery(deliveryID).getCreatedDate()));
        System.out.println("Delivery Status: "+getDelivery(deliveryID).getDeliveryState()+"%n");
    }

    public void listAllDelivery(){
        for (Delivery d: deliveryList){
            System.out.println("Delivery ID: "+ d.getDeliveryID());
            System.out.println("Order ID: "+ d.getOrderID());
            System.out.println("Zone: "+ d.getZone());
            System.out.println("Address: "+ d.getAddress());
            System.out.println("Delivery Fee: "+ d.getDeliveryFee());
            System.out.println("Estimated Delivery Date: "+ d.getEstDeliveryDate());
            System.out.println("Created Date: "+simpleDateFormat.format(d.getCreatedDate()));
            System.out.println("Delivery Status: "+d.getDeliveryState()+"%n");
        }
    }
}