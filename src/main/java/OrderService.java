import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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

	public boolean placeOrder(Order order) { 
		centralOrderList.add(order);
		return false;
	}

	public ArrayList<Order> searchOrder(OrderSortType sort){
		ArrayList<Order> result = (ArrayList<Order>) centralOrderList.clone();

		result.sort(sort);

		List<Order> skippedOrderList = result.stream().toList();
		return new ArrayList<Order>(skippedOrderList);
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
