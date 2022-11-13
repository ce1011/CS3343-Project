import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class OrderService {
	private OrderBST centralOrderList;
	private static Date serviceTimeStamp;
	private static OrderService orderService = new OrderService();
	
	
	private OrderService(){
		centralOrderList = new OrderBST();
	}
	
	public static OrderService getOrderServiceInstance() {
		return orderService;
	}

	//order list related method
	
	//need modify with validation
	public boolean placeOrder(Order order) { 
		centralOrderList.insertOrder(order);
		return false;
	}
	
	public Order searchOrder(String ID) {
		return centralOrderList.searchOrderByTransactionID(ID);
	}
	
	public ArrayList<Order> searchOrder(Customer customer){
		return new ArrayList<Order>();
		//return centralOrderList.searchOrderByCustomerID(customer.getCustomerID());
	}
	
	/*
	 * public ArrayList<Order> retrieveCustomerOrderList(Customer customer) {
	 * ArrayList<Order> tempOrderList = new ArrayList<Order>();
	 * 
	 * for (Order order: centralOrderList) {
	 * if(order.getCustomerInfo().getCustomerID().equals(customer.getCustomerID()))
	 * { tempOrderList.add(order); } }
	 * 
	 * return tempOrderList; }
	 */
	
	
	//TransactionID is 6 digit start from 1XXXXX
	public String assignTransactionID() {
		return String.valueOf(orderService.getOrderListNumber()+100000+1) ;
	}
	
	public int getOrderListNumber() {
		return centralOrderList.getOrderBSTSize();	
	}
	
	
	//order service date and time method
	public static String getCurrentTimestamp() {
		serviceTimeStamp = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		return ft.format(serviceTimeStamp);
		
	}
	
	/*
	 * public Order searchOrder(String transactionID) {
	 * 
	 * for(Order order: centralOrderList) {
	 * if(order.getTransactionID().equals(transactionID)) {
	 * 
	 * } } return null; }
	 */
}
