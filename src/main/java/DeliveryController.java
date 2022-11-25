import java.util.ArrayList;

public class DeliveryController {
    private DeliveryView view;
    private DeliveryService model;

    public DeliveryController(){
        model = DeliveryService.getInstance();
        view = new DeliveryView(this);
    }

    public void entryDeliveryView(){
        view.entryView();
    }

    public void editDeliveryInfo(String deliveryID, String orderID, String zone, String address, double weight, String deliveryState){
        try{
            model.updateDelivery(deliveryID, orderID, zone, address, weight, deliveryState);
            view.printSuccess();
        }
        catch(DeliveryItemNotFoundException e){
            System.out.println(e.toString());
        }
        catch(DeliveryStateNotFoundException e){
            System.out.println(e.toString());
        }
        catch(DeliveryZoneNotFoundException e){
            System.out.println(e.toString());
        }
    }

    public void editDeliverySystemPrice(double deliveryFirstKG_price, double deliveryAfterFirstKG_price){
        try{
            model.updateDeliveryPrice(deliveryFirstKG_price, deliveryAfterFirstKG_price);
            view.printSuccess();
        }
        catch(Exception e){
            view.printTypeMismatch();
        }
    }

    public Delivery findDelivery(String deliveryID){
        try{
            return model.getDelivery(deliveryID);
        }
        catch(DeliveryItemNotFoundException e){
            System.out.println(e.toString());
            return null;
        }
    }

    public void createDelivery(String orderID, String zone, String address, double weight){
        try{
            model.createDelivery(orderID, zone, address, weight);
            view.printSuccess();
        }
        catch(DeliveryZoneNotFoundException e){
            System.out.println(e.toString());
        }
    }

    public void addDeliveryZone(String zone){
        try{
            model.addDeliveryZone(zone);
            view.printSuccess();
        }
        catch(Exception e){
            view.printTypeMismatch();
        }
    }

    public void deleteDeliveryZone(String zone){
        try{
            model.removeDeliveryZone(zone);
            view.printSuccess();
        }
        catch(DeliveryZoneNotFoundException e){
            System.out.println(e.toString());
        }
    }

    public void deleteDelivery(String deliveryID){
        try{
            model.deleteDelivery(deliveryID);
            view.printSuccess();
        }
        catch(DeliveryItemNotFoundException e){
            System.out.println(e.toString());
        }
    }

    public ArrayList<Delivery> getDeliveryList(){
        return model.getDeliveryList();
    }

}
