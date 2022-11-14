import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class OrderService {
	private ArrayList<Order> centralOrderList;
	private static Date serviceTimeStamp;
	private static OrderService orderService = new OrderService();


	private OrderService(){
		centralOrderList = new ArrayList<Order>();
	}

	public static OrderService getOrderServiceInstance() {
		return orderService;
	}

	//order list related method
	//need modify with validation
	public void placeOrder(Order order) {
		centralOrderList.add(order);

	}

//	public void removeOrder(String transactionID) throws OrderNotFoundException {
//		Order order = this.getOrderByTransactionID(transactionID);
//		order.setOrderState(new OrderState_Deleted());
//	}

	public ArrayList<Order> searchOrder(Customer customer){
		return new ArrayList<Order>();
		//return centralOrderList.searchOrderByCustomerID(customer.getCustomerID());
	}

	//TransactionID is 6 digit start from 1XXXXX
	public String assignTransactionID() {
		return String.valueOf(orderService.getOrderListNumber()+100000+1) ;
	}

	public int getOrderListNumber() {
		return centralOrderList.size();
	}

	//order service date and time method
	public static String getCurrentTimestamp() {
		serviceTimeStamp = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		return ft.format(serviceTimeStamp);

	}

}
