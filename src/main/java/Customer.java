package main;

public class Customer implements Role{
	
	private static Customer instance = new Customer();
	public static Customer getInstance() {return instance;}
	
	private Customer() {}

	@Override
	public String getRole(User user) {
		// TODO Auto-generated method stub
		return "Customer";
	}


}
