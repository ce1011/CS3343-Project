import java.util.ArrayList;

public class Order {
	private String transactionID;
	private User user;
	private ArrayList<CartItem> productList;
	private Coupon couponUsed;
	private Delivery deliveryDetails;
	private double totalPrice;
	private OrderState status;
	private String remarks;
	private String orderDate;
	private ArrayList<Order> orderList;

	Order(ArrayList<CartItem> productList, Coupon couponUsed,
		  double totalPrice, String remarks){
		this.setUser(Store.getInstance().getCurrentUser());
		this.setTransactionID(OrderService.getOrderServiceInstance().assignTransactionID());
		this.setProductList(productList);
		this.setCouponUsed(couponUsed);
		this.setTotalPrice(totalPrice);
		this.setStatus(new OrderState_Pending());
		this.setRemarks(remarks);
		this.setOrderDate(OrderService.getCurrentTimestamp());
		orderList = new ArrayList<Order>();
		orderList.add(this);
	}

	//getter and setter
	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public ArrayList<CartItem> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<CartItem> productList) {
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

	public String getTotalPrice() {
		return Double.toString(totalPrice);
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderState getStatus() {
		return status;
	}

	public void setStatus(OrderState status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public double getTotalPriceDouble(){
		return totalPrice;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getOrderDateYYYYMMDD(){
		return orderDate.substring(0,10);
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String retrieveProductName() {
		StringBuilder productName = new StringBuilder();
		for(CartItem p: productList){
			productName.append(" ").append(p.getProduct().getName());
		}
		return productName.toString();
	}
}
