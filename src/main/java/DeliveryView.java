import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryView {
    private DeliveryController controller;

    public DeliveryView(DeliveryController controller){
        this.controller = controller;
        
    }

    public void entryView(){
        System.out.println("Delivery Management Page");
        System.out.println("1. Modify Information of Delivery Items");
        System.out.println("2. Display Delivery Information by Delivery ID");
        System.out.println("3. Edit the price of Delivery");
        System.out.println("4. Delete Delivery Item");
        System.out.println("5. View All Delivery Items");
        System.out.println("6. Add Available Delivery Zone");
        System.out.println("7. Remove Existing Delivery Zone");
        System.out.println("8. Return");
        System.out.print("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        //scanner.close();
        switch (choice){
            case 1:
                modifyDeliveryInfoView();
                break;
            case 2:
                displayDeliveryInfoView();
                break;
            case 3:
                editDeliverySystemPriceView();
                break;
            case 4:
                deleteDeliveryView();
                break;
            case 5:
                viewAllDeliveryView();
                break;
            case 6:
                addDeliveryZoneView();
                break;
            case 7:
                removeDeliveryZoneView();
            break;
            case 8:
                HomeController hc = new HomeController();
                hc.showAdminHome();
                break;
            default:
                System.out.println("Invalid choice!");
                entryView();
        }
    }

    //1
    public void modifyDeliveryInfoView(){
        System.out.println("Please input Delivery ID: ");
        Scanner scanner = new Scanner(System.in);
        String deliveryID = scanner.next();
        System.out.println("Please input new Order ID of Delivery Item: ");
        String orderID = scanner.next();
        System.out.println("Please input new Zone of Delivery Item: ");
        String zone = scanner.next();
        System.out.println("Please input new Address of Delivery Item: ");
        String address = scanner.next();
        System.out.println("Please input new Weight of Delivery Item: ");
        double weight = scanner.nextDouble();
        //scanner.close();
        controller.editDeliveryInfo(deliveryID, orderID, zone, address, weight, deliveryID);
        this.entryView();
    }
    //2
    public void displayDeliveryInfoView(){
        System.out.println("Please input Delivery ID: ");
        Scanner scanner = new Scanner(System.in);
        String deliveryID = scanner.next();
        //scanner.close();
        System.out.format("%10s|%10s|%20s|%50s|%6s\n", "Delivery ID", "Order ID", "Zone", "Address", "Delivery Fee");
        if(controller.findDelivery(deliveryID) != null){
            System.out.format("%10s %10s %20s %50s %6d\n", controller.findDelivery(deliveryID).getOrderID(), controller.findDelivery(deliveryID).getOrderID(), 
            controller.findDelivery(deliveryID).getZone(), controller.findDelivery(deliveryID).getAddress(), controller.findDelivery(deliveryID).getDeliveryFee());
        }
        this.entryView();
    }
    //3
    public void editDeliverySystemPriceView(){
        System.out.println("Please input Delivery Price of First KG: ");
        Scanner scanner = new Scanner(System.in);
        double deliveryFirstKG_price = scanner.nextDouble();
        System.out.println("Please input Delivery Price after First KG: ");
        double deliveryAfterFirstKG_price = scanner.nextDouble();
        //scanner.close();
        controller.editDeliverySystemPrice(deliveryFirstKG_price, deliveryAfterFirstKG_price);
        this.entryView();
    }
    //4
    public void deleteDeliveryView(){
        System.out.println("Please input Delivery ID of the item that you want to delete: ");
        Scanner scanner = new Scanner(System.in);
        String deliveryID = scanner.next();
        controller.deleteDelivery(deliveryID);
        //scanner.close();
        this.entryView();
    }
    //5
    public void viewAllDeliveryView(){
        ArrayList<Delivery> dList = controller.getDeliveryList();
        System.out.format("%10s|%10s|%20s|%50s|%6s\n", "Delivery ID", "Order ID", "Zone", "Address", "Delivery Fee");
        for(Delivery d:dList){
            System.out.format("%10s %10s %20s %50s %f\n", d.getOrderID(), d.getOrderID(), d.getZone(), d.getAddress(), d.getDeliveryFee());
        }
        this.entryView();
    }
    //6
    public void addDeliveryZoneView(){
        System.out.println("Please input the new delivery zone that you want to add: ");
        Scanner scanner = new Scanner(System.in);
        String zone = scanner.next();
        controller.addDeliveryZone(zone);
        //scanner.close();
        this.entryView();
    }
    //7
    public void removeDeliveryZoneView(){
        System.out.println("Please input the new delivery zone that you want to remove: ");
        Scanner scanner = new Scanner(System.in);
        String zone = scanner.next();
        controller.deleteDeliveryZone(zone);
        //scanner.close();
        this.entryView();
    }

    public void printSuccess(){
        System.out.println("Success !");
    }

    public void printTypeMismatch(){
        System.out.println("Type Mismatched !");
    }

}
