import java.util.ArrayList;

public class OrderBST {
	// define Node class
	class Node {

		Order order;
		int key;
		Node left, right;

		public Node(Order order) {
			this.order = order;
			this.key = getOrderNodeKey(order);
			left = right = null;
		}
	}

	Node root;
	int numOfOrder;

	// class constructor
	public OrderBST() {
		root = null;
		numOfOrder = 0;
	}

	// insert new order
	// add insert validation
	public void insertOrder(Order order) {
		root = insertProcess(root, order);
		numOfOrder++;
	}
	
	//123
	private OrderBST.Node insertProcess(OrderBST.Node root, Order order) {
		// tree is empty
		if (root == null) {
			root = new Node(order);
			return root;
		}

		// tree is not empty, traverse the the orderBST
		if (getOrderNodeKey(order) < root.key) { // insert to left subtree
			root.left = insertProcess(root.left, order);
		} else if (getOrderNodeKey(order) > root.key) { // insert to right subtree
			root.right = insertProcess(root.right, order);
		}

		return root;
	}
	
	

	// search order 
	// search order by transactionID
	public Order searchOrderByTransactionID(String transactionID_String) {
		int transactionID_int = Integer.parseInt(transactionID_String);
		root = searchProcess(root, transactionID_int);
		
		if(root!=null) {
			return root.order;
		}else {
			return null;
		}
	}

	private OrderBST.Node searchProcess(OrderBST.Node root, int transactionID_int) {
		//output root if root is null or the first node is the target node
		if (root==null || root.key == transactionID_int) {
			return root;
		}
		if(transactionID_int < root.key) {
			return searchProcess(root.left, transactionID_int);
		}else {
			return searchProcess(root.right, transactionID_int);
		}
	}
	
	//search order by customerID
	public ArrayList<Order> searchOrderByCustomerID(String customerID) {
		ArrayList<Order> tempList = new ArrayList<Order>();
		
		return tempList;
	}
	
	

	// get order transactionID in int type
	private int getOrderNodeKey(Order order) {
		return Integer.parseInt(order.getTransactionID());
	}
	
	public int getOrderBSTSize() {
		return numOfOrder;
	}

}
