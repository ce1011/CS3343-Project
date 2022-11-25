import java.util.ArrayList;

public class Order {
	private String transactionID;
	private User user;
	private ArrayList<Product> productList;
	private Coupon couponUsed;
	private Delivery deliveryDetails;
	private double totalPrice;
	private OrderState status;
	private String remarks;
	private String orderDate;
	private ArrayList<Order> orderList;

	Order(ArrayList<Product> productList, Coupon couponUsed, Delivery deliveryDetails,
		  double totalPrice, String remarks) {
		this.setUser(Store.getInstance().getCurrentUser());
		this.setTransactionID(OrderService.getOrderServiceInstance().assignTransactionID());
		this.setProductList(productList);
		this.setCouponUsed(couponUsed);
		this.setDeliveryDetails(deliveryDetails);
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String retrieveProductName() {
		StringBuilder productName = new StringBuilder();
		for(Product p: productList){
			productName.append(" ").append(p.getName());
		}
		return productName.toString();
	}
}
