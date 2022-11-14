import java.util.ArrayList;

public class Order {
	private String transactionID;
	private Customer customerInfo;
	private ArrayList<Product> productList;
	private Coupon couponUsed;
	private Delivery deliveryDetails;
	private double totalPrice;

	private OrderState state;
	private String remarks;
	private String orderDate;
	private ArrayList<Order> orderList;

	Order(Customer customerInfo, ArrayList<Product> productList, Coupon couponUsed, Delivery deliveryDetails,
			double totalPrice, String remarks) {
		this.setTransactionID(OrderService.getOrderServiceInstance().assignTransactionID());
		this.setCustomerInfo(customerInfo);
		this.setProductList(productList);
		this.setCouponUsed(couponUsed);
		this.setDeliveryDetails(deliveryDetails);
		this.setTotalPrice(totalPrice);
		this.setState(new OrderState_Pending());
		this.setRemarks(remarks);
		this.setOrderDate(OrderService.getCurrentTimestamp());
		orderList = new ArrayList<Order>();
		orderList.add(this);
	}

	//getter and setter
	public String getTransactionID() {
		return transactionID;
	}

	public int getTransactionID_numerical(){
		return Integer.parseInt(transactionID);
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public Coupon getCouponUsed() {
		return couponUsed;
	}

	public void setCouponUsed(Coupon couponUsed) {
		this.couponUsed = couponUsed;
	}

	public Delivery getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(Delivery deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
