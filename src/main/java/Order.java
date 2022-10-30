import java.util.ArrayList;

public class Order {
	private String transactionID;
	private Customer customerInfo;
	private ArrayList<Product> productList;
	private Coupon couponUsed;
	private Delivery deliveryDetails;
	private double totalPrice;
	private OrderStatus status;
	private String remarks;
	private String orderDate;

	Order(Customer customerInfo, ArrayList<Product> productList, Coupon couponUsed, Delivery deliveryDetails,
			double totalPrice, String remarks) {
		this.setTransactionID(OrderService.getOrderServiceInstance().assignTransactionID());
		this.setCustomerInfo(customerInfo);
		this.setProductList(productList);
		this.setCouponUsed(couponUsed);
		this.setDeliveryDetails(deliveryDetails);
		this.setTotalPrice(totalPrice);
		this.setStatus(new OrderStatus());
		this.setRemarks(remarks);
		this.setOrderDate(OrderService.getCurrentTimestamp());
		
	}

	//getter and setter
	public String getTransactionID() {
		return transactionID;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
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
}
