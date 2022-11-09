public class DeliveryController {
    private DeliveryView view;
    private DeliveryService model;

    public DeliveryController(){
        view = new DeliveryView();
        model = new DeliveryService();

    }

    public void editDeliveryInfo(String deliveryID, String orderID, String zone, String address, double weight, String deliveryState){
        try{
            model.updateDelivery(deliveryID, orderID, zone, address, weight, deliveryState);
        }
        catch(DeliveryItemNotFoundException e){

        }
        catch(DeliveryStateNotFoundException e){

        }
        catch(DeliveryZoneNotFoundException e){

        }
    }

    public void editDeliverySystemPrice(double deliveryFirstKG_price, double deliveryAfterFirstKG_price){
        try{
            model.updateDeliveryPrice(deliveryFirstKG_price, deliveryAfterFirstKG_price);
        }
        catch(Exception e){

        }
    }

    public void findDelivery(String deliveryID){
        try{
            model.getDelivery(deliveryID);
        }
        catch(DeliveryItemNotFoundException e){

        }
    }

    public void createDelivery(String orderID, String zone, String address, double weight){
        try{
            model.createDelivery(orderID, zone, address, weight);
        }
        catch(DeliveryZoneNotFoundException e){

        }
    }



    public void deleteDeliveryZone(String zone){
        try{
            model.removeDeliveryZone(zone);
        }
        catch(DeliveryZoneNotFoundException e){

        }
    }

    public void deleteDelivery(String deliveryID){
        try{
            model.deleteDelivery(deliveryID);
        }
        catch(DeliveryItemNotFoundException e){

        }
    }


    
}
